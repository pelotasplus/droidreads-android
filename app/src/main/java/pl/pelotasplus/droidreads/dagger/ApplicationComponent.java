package pl.pelotasplus.droidreads.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import pl.pelotasplus.droidreads.MainActivity;
import pl.pelotasplus.droidreads.PreferencesStore;
import pl.pelotasplus.droidreads.api.model.User;
import pl.pelotasplus.droidreads.screens.bookdetails.BookDetailsLoader;
import pl.pelotasplus.droidreads.screens.login.LoginFragment;
import pl.pelotasplus.droidreads.screens.mainmenu.MainMenuFragment;
import pl.pelotasplus.droidreads.screens.notifications.NotificationsLoader;
import pl.pelotasplus.droidreads.screens.updates.UpdatesListEntryViewHolder;
import pl.pelotasplus.droidreads.services.OAuthAccessTokenRequest;
import pl.pelotasplus.droidreads.services.OAuthRequestTokenRequest;
import pl.pelotasplus.droidreads.screens.updates.UpdatesLoader;

@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {
    void inject(Application application);

    void inject(MainActivity mainActivity);

    void inject(OAuthRequestTokenRequest request);

    void inject(OAuthAccessTokenRequest request);

    void inject(UpdatesLoader loader);

    void inject(NotificationsLoader loader);

    void inject(User user);

    void inject(MainMenuFragment fragment);

    void inject(LoginFragment fragment);

    void inject(PreferencesStore preferencesStore);

    void inject(BookDetailsLoader loader);

    void inject(UpdatesListEntryViewHolder holder);
}
