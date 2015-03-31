package pl.pelotasplus.droidreads.services;

import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;

import dagger.Lazy;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import pl.pelotasplus.droidreads.PreferencesStore;
import pl.pelotasplus.droidreads.api.GoodreadsAPI;
import pl.pelotasplus.droidreads.api.model.AuthUserResponse;
import pl.pelotasplus.droidreads.api.model.User;
import pl.pelotasplus.droidreads.api.model.UserShowResponse;
import pl.pelotasplus.droidreads.dagger.Injector;
import retrofit.RetrofitError;

public class OAuthAccessTokenRequest extends SpiceRequest<User> {
    private static final String TAG = OAuthAccessTokenRequest.class.getSimpleName();

    private final String oauthToken;

    @Inject
    Lazy<GoodreadsAPI> goodreadsAPI;

    @Inject
    OAuthProvider oAuthProvider;

    @Inject
    OAuthConsumer oAuthConsumer;

    @Inject
    PreferencesStore preferencesStore;

    public OAuthAccessTokenRequest(String oauthToken) {
        super(User.class);

        Injector.getInstance().inject(this);

        this.oauthToken = oauthToken;
    }

    @Override
    public User loadDataFromNetwork() throws Exception {
        return getAccessToken(oauthToken);
    }

    private User getAccessToken(String oauth_token) {
        try {
            oAuthProvider.retrieveAccessToken(oAuthConsumer, oauth_token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        preferencesStore.setApiTokens(
                oAuthConsumer.getToken(),
                oAuthConsumer.getTokenSecret()
        );

        try {
            AuthUserResponse authUserResponse = goodreadsAPI.get().auth_user();
            User registeredUser = authUserResponse.getUser();

            UserShowResponse userShowResponse = goodreadsAPI.get().user_show(registeredUser.getId());
            return userShowResponse.getUser();
        } catch (RetrofitError retrofitError) {
            retrofitError.printStackTrace();
            return null;
        }
    }
}
