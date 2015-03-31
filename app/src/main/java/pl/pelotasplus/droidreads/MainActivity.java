package pl.pelotasplus.droidreads;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.Scrollable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.api.model.Book;
import pl.pelotasplus.droidreads.api.model.User;
import pl.pelotasplus.droidreads.dagger.Injector;
import pl.pelotasplus.droidreads.screens.bookdetails.BookDetailsActivity;
import pl.pelotasplus.droidreads.screens.login.LoginFragment;
import pl.pelotasplus.droidreads.screens.mainmenu.MainMenuFragment;
import pl.pelotasplus.droidreads.screens.mainmenu.MainMenuItem;
import pl.pelotasplus.droidreads.screens.notifications.NotificationsFragment;
import pl.pelotasplus.droidreads.screens.updates.UpdatesFragment;

public class MainActivity extends ActionBarActivity
        implements MainActivityInterface, ToolbarInterface,
        ObservableScrollViewCallbacks {
    private static final String TAG = MainActivity.class.getSimpleName();

    @InjectView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    PreferencesStore preferencesStore;

    @Inject
    User currentUser;

    FragmentManager fragmentManager;
    ActionBarDrawerToggle drawerToggle;
    Scrollable scrollable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Injector.getInstance().inject(this);

        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.hello_world, R.string.hello_world) {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (drawerView.getId() == R.id.notificationsContainer) {
                    showToolbar();
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (drawerView.getId() == R.id.notificationsContainer) {
                    showToolbar();
                }
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
        drawerLayout.setDrawerListener(drawerToggle);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            if (currentUser.getId() == -1) {
                showMenuItem(MainMenuItem.LOGIN);
            } else {
                showMenuItem(MainMenuItem.UPDATES);
            }
        }

//        new KtTest().toast(this, "Test msg");
    }

    @Override
    public void showMenuItem(MainMenuItem mainMenuItem) {
        drawerLayout.closeDrawers();
        scrollable = null;

        Fragment fragment = null;

        switch (mainMenuItem) {
            case UPDATES:
                fragment = UpdatesFragment.newInstance();
                break;
            case NOTIFICATIONS:
                fragment = NotificationsFragment.newInstance(true /* setScrollable */);
                break;
            case LOGIN:
                fragment = LoginFragment.newInstance();
                break;
        }

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();

            if (!(fragment instanceof LoginFragment)) {
                showToolbar();
                showNavigationDrawer();
            }
        }
    }

    @Override
    public void hideToolbar() {
        moveToolbar(-toolbar.getHeight());
    }

    @Override
    public void showToolbar() {
        moveToolbar(0);
    }

    @Override
    public void doLogout() {
        preferencesStore.setCurrentUser(null);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        showMenuItem(MainMenuItem.LOGIN);
    }

    @Override
    public void showNavigationDrawer() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(
                R.id.mainMenuContainer,
                MainMenuFragment.newInstance(),
                MainMenuFragment.TAG);

        fragmentTransaction.replace(
                R.id.notificationsContainer,
                NotificationsFragment.newInstance(false /* setScrollable */),
                NotificationsFragment.TAG);

        fragmentTransaction.commit();

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void doUnauthorized() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, R.string.unauthorized_api, Toast.LENGTH_LONG).show();
                doLogout();
            }
        });
    }

    @Override
    public void setToolbarTitle(int title) {
        toolbar.setTitle(title);
    }

    @Override
    public void showBookDetails(Book book) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra(BookDetailsActivity.EXTRA_BOOK, book.toJson());
        startActivity(intent);
    }

    private boolean toolbarIsShown() {
        return toolbar.getTranslationY() == 0;
    }

    private boolean toolbarIsHidden() {
        return Math.abs(toolbar.getTranslationY()) == toolbar.getHeight();
    }

    private void moveToolbar(float toTranslationY) {
        if (toolbar.getTranslationY() == toTranslationY) {
            return;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(toolbar.getTranslationY(), toTranslationY).setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float translationY = (float) animation.getAnimatedValue();
                toolbar.setTranslationY(translationY);

                FrameLayout parent = (FrameLayout) findViewById(R.id.container);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) parent.getLayoutParams();
                int margin = (int) (toolbar.getHeight() + translationY);

                lp.setMargins(0, margin, 0, 0);

                parent.setLayoutParams(lp);
            }
        });
        animator.start();
    }

    @Override
    public void setScrollable(Scrollable scrollable) {
        this.scrollable = scrollable;
        this.scrollable.setScrollViewCallbacks(this);
    }

    @Override
    public void onScrollChanged(int i, boolean b, boolean b2) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        if (scrollState == ScrollState.UP) {
            if (toolbarIsShown()) {
                hideToolbar();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (toolbarIsHidden()) {
                showToolbar();
            }
        }
    }
}
