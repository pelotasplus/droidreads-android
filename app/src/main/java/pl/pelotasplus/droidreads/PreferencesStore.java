package pl.pelotasplus.droidreads;

import android.content.SharedPreferences;

import javax.inject.Inject;

import pl.pelotasplus.droidreads.api.model.User;
import pl.pelotasplus.droidreads.dagger.Injector;

public class PreferencesStore {
    @Inject
    SharedPreferences sharedPreferences;

    private static final String CURRENT_USER_PREF_NAME = "CURRENT_USER";

    public PreferencesStore() {
        Injector.getInstance().inject(this);
    }

    public User getCurrentUser() {
        String userJson = sharedPreferences.getString(CURRENT_USER_PREF_NAME, null);
        if (userJson == null) {
            return null;
        }

        return User.fromJson(userJson);
    }

    public boolean setCurrentUser(User currentUser) {
        String userJson = null;
        if (currentUser != null) {
            userJson = currentUser.toJson();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CURRENT_USER_PREF_NAME, userJson);
        return editor.commit();
    }

    private static final String API_TOKEN = "API_TOKEN";
    private static final String API_TOKEN_SECRET = "API_TOKEN_SECRET";

    public boolean setApiTokens(String token, String tokenSecret) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(API_TOKEN, token);
        editor.putString(API_TOKEN_SECRET, tokenSecret);
        return editor.commit();
    }

    public String getApiToken() {
        return sharedPreferences.getString(API_TOKEN, null);
    }

    public String getApiTokenSecret() {
        return sharedPreferences.getString(API_TOKEN_SECRET, null);
    }
}
