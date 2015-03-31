package pl.pelotasplus.droidreads.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import pl.pelotasplus.droidreads.BuildConfig;
import pl.pelotasplus.droidreads.PreferencesStore;
import pl.pelotasplus.droidreads.analytics.Analytics;
import pl.pelotasplus.droidreads.api.GoodreadsAPI;
import pl.pelotasplus.droidreads.api.googlebooks.GoogleBooksAPI;
import pl.pelotasplus.droidreads.api.MySimpleXMLConverter;
import pl.pelotasplus.droidreads.api.model.User;
import pl.pelotasplus.droidreads.api.oauth.OkHttpOAuthConsumer;
import pl.pelotasplus.droidreads.api.oauth.OkHttpOAuthProvider;
import pl.pelotasplus.droidreads.api.oauth.RetrofitHttpOAuthConsumer;
import pl.pelotasplus.droidreads.api.oauth.RetrofitSigningOkClient;
import retrofit.RestAdapter;

@Module
public class ApplicationModule {
    private static final String TAG = ApplicationModule.class.getSimpleName();

    private static final String PREFERENCES_NAME = "DroidreadsPreferences";

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public GoodreadsAPI provideGoodreadsAPI(PreferencesStore preferencesStore) {
        RetrofitHttpOAuthConsumer oAuthConsumer = new RetrofitHttpOAuthConsumer(
                BuildConfig.GOODREADS_API_KEY,
                BuildConfig.GOODREADS_API_SECRET
        );

        oAuthConsumer.setTokenWithSecret(
                preferencesStore.getApiToken(),
                preferencesStore.getApiTokenSecret()
        );

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new RetrofitSigningOkClient(oAuthConsumer))
//                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BuildConfig.GOODREADS_API_ENDPOINT)
                .setConverter(new MySimpleXMLConverter())
                .build();

        return restAdapter.create(GoodreadsAPI.class);
    }

    @Provides
    @Singleton
    public GoogleBooksAPI provideGoodBooksAPI(PreferencesStore preferencesStore) {
//        RetrofitHttpOAuthConsumer oAuthConsumer = new RetrofitHttpOAuthConsumer(
//                BuildConfig.GOODREADS_API_KEY,
//                BuildConfig.GOODREADS_API_SECRET
//        );

//        oAuthConsumer.setTokenWithSecret(
//                preferencesStore.getApiToken(),
//                preferencesStore.getApiTokenSecret()
//        );

        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setClient(new RetrofitSigningOkClient(oAuthConsumer))
//                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BuildConfig.GOOGLEBOOKS_API_ENDPOINT)
//                .setConverter(new MySimpleXMLConverter())
                .build();

        return restAdapter.create(GoogleBooksAPI.class);
    }


    @Provides
    @Singleton
    Analytics provideAnalytics() {
        /*
            analytics.send(new HitBuilders.EventBuilder() //
        .setCategory(Analytics.CATEGORY_SHORTCUT) //
        .setAction(Analytics.ACTION_SHORTCUT_ADDED) //
        .build());
         */
        if (BuildConfig.DEBUG) {
            return new Analytics() {
                @Override
                public void send(Map<String, String> params) {
                    Log.d("Analytics", String.valueOf(params));
                }
            };
        }

        GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(application);
        Tracker tracker = googleAnalytics.newTracker(BuildConfig.GOOGLE_ANALYTICS_KEY);
        tracker.setSessionTimeout(300); // ms? s? better be s.
        return new Analytics.GoogleAnalytics(tracker);
    }

    @Provides
    @Singleton
    PreferencesStore providePreferencesStore() {
        return new PreferencesStore();
    }


    @Provides
    User provideCurrentUser(PreferencesStore preferencesStore) {
        User user = preferencesStore.getCurrentUser();
        if (user == null) {
            user = new User();
        }
        return user;
    }

    @Provides
    @Singleton
    OAuthProvider provideOAuthProvider() {
        return new OkHttpOAuthProvider(
                BuildConfig.GOODREADS_API_ENDPOINT + "/oauth/request_token",
                BuildConfig.GOODREADS_API_ENDPOINT + "/oauth/access_token",
                BuildConfig.GOODREADS_API_ENDPOINT + "/oauth/authorize?mobile=1"
        );
    }

    @Provides
    @Singleton
    OAuthConsumer provideOAuthConsumer() {
        return new OkHttpOAuthConsumer(
                BuildConfig.GOODREADS_API_KEY,
                BuildConfig.GOODREADS_API_SECRET
        );
    }
}