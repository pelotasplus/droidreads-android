package pl.pelotasplus.droidreads.dagger;

import android.app.Application;

import pl.pelotasplus.droidreads.MainActivity;
import pl.pelotasplus.droidreads.PreferencesStore;
import pl.pelotasplus.droidreads.api.model.User;
import pl.pelotasplus.droidreads.screens.bookdetails.BookDetailsLoader;
import pl.pelotasplus.droidreads.screens.login.LoginFragment;
import pl.pelotasplus.droidreads.screens.mainmenu.MainMenuFragment;
import pl.pelotasplus.droidreads.screens.notifications.NotificationsLoader;
import pl.pelotasplus.droidreads.screens.updates.UpdatesListEntryViewHolder;
import pl.pelotasplus.droidreads.screens.updates.UpdatesLoader;
import pl.pelotasplus.droidreads.services.OAuthAccessTokenRequest;
import pl.pelotasplus.droidreads.services.OAuthRequestTokenRequest;

public class Injector implements ApplicationComponent {
    private static ApplicationComponent component;
    private static Injector injector;

    public static Injector getInstance() {
        if (injector == null) {
            injector = new Injector();
        }
        return injector;
    }

    public void init(Application application) {
        component = Dagger_ApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
    }

    @Override
    public void inject(Application application) {
        component.inject(application);
    }

    @Override
    public void inject(MainActivity mainActivity) {
        component.inject(mainActivity);
    }

    @Override
    public void inject(OAuthRequestTokenRequest request) {
        component.inject(request);
    }

    @Override
    public void inject(OAuthAccessTokenRequest request) {
        component.inject(request);
    }

    @Override
    public void inject(UpdatesLoader loader) {
        component.inject(loader);
    }

    @Override
    public void inject(NotificationsLoader loader) {
        component.inject(loader);
    }

    @Override
    public void inject(User user) {
        component.inject(user);
    }

    @Override
    public void inject(MainMenuFragment fragment) {
        component.inject(fragment);
    }

    @Override
    public void inject(LoginFragment fragment) {
        component.inject(fragment);
    }

    @Override
    public void inject(PreferencesStore preferencesStore) {
        component.inject(preferencesStore);
    }

    @Override
    public void inject(BookDetailsLoader loader) {
        component.inject(loader);
    }

    @Override
    public void inject(UpdatesListEntryViewHolder holder) {
        component.inject(holder);
    }
}
