package pl.pelotasplus.droidreads.screens.friendsreview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.SpacesItemDecoration;
import pl.pelotasplus.droidreads.ToolbarInterface;
import pl.pelotasplus.droidreads.api.model.Book;
import pl.pelotasplus.droidreads.screens.similarbooks.SimilarBooksAdapter;

public class FriendsReviewActivity extends FragmentActivity
        implements ObservableScrollViewCallbacks, ToolbarInterface {
    private static final String TAG = FriendsReviewActivity.class.getSimpleName();
    public static final String EXTRA_BOOK = TAG + "/EXTRA_BOOK";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.recyclerView)
    ObservableRecyclerView recyclerView;

    FriendsReviewAdapter adapter;

    private Book book;

    private final int COLS_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String booksJson = getIntent().getStringExtra(EXTRA_BOOK);
        book = Book.fromJson(booksJson);

        setContentView(R.layout.activity_similar_books);

        ButterKnife.inject(this);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendsReviewActivity.this.finish();
            }
        });
        toolbar.setTitle("Friend's reviews");

        RecyclerView.LayoutManager manager = new GridLayoutManager(this, COLS_COUNT);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(COLS_COUNT, 10));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        adapter = new FriendsReviewAdapter();
        adapter.setReviews(book.getFriendReviews());

        recyclerView.setAdapter(adapter);

        recyclerView.setScrollViewCallbacks(this);
    }

    public static void showReviews(Context context, Book book) {
        String booksJson = book.toJson();

        Intent intent = new Intent(context, FriendsReviewActivity.class);
        intent.putExtra(EXTRA_BOOK, booksJson);
        context.startActivity(intent);
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

                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) recyclerView.getLayoutParams();
                int margin = (int) (toolbar.getHeight() + translationY);

                lp.setMargins(0, margin, 0, 0);

                recyclerView.setLayoutParams(lp);
            }
        });
        animator.start();
    }

    private boolean toolbarIsShown() {
        return toolbar.getTranslationY() == 0;
    }

    private boolean toolbarIsHidden() {
        return Math.abs(toolbar.getTranslationY()) == toolbar.getHeight();
    }

    /* ToolbarInterface */

    @Override
    public void hideToolbar() {
        moveToolbar(-toolbar.getHeight());
    }

    @Override
    public void showToolbar() {
        moveToolbar(0);
    }


    /* ObservableScrollViewCallbacks */

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