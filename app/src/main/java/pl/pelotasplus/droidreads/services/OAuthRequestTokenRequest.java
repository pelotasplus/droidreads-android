package pl.pelotasplus.droidreads.services;

import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import pl.pelotasplus.droidreads.BuildConfig;
import pl.pelotasplus.droidreads.api.GoodreadsAPI;
import pl.pelotasplus.droidreads.dagger.Injector;

public class OAuthRequestTokenRequest extends SpiceRequest<String> {
    private static final String TAG = OAuthRequestTokenRequest.class.getSimpleName();

    @Inject
    OAuthProvider oAuthProvider;

    @Inject
    OAuthConsumer oAuthConsumer;

    public OAuthRequestTokenRequest() {
        super(String.class);

        Injector.getInstance().inject(this);
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        return getRequestToken();
    }

    public String getRequestToken() {
        String authUrl = null;

        try {
            authUrl = oAuthProvider.retrieveRequestToken(
                    oAuthConsumer,
                    BuildConfig.GOODREADS_CALLBACK_URL,
                    "mobile=1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authUrl;
    }
}
