package pl.pelotasplus.droidreads.screens.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import oauth.signpost.OAuth;
import pl.pelotasplus.droidreads.BuildConfig;
import pl.pelotasplus.droidreads.MainActivityInterface;
import pl.pelotasplus.droidreads.PreferencesStore;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.ToolbarInterface;
import pl.pelotasplus.droidreads.api.model.User;
import pl.pelotasplus.droidreads.dagger.Injector;
import pl.pelotasplus.droidreads.screens.mainmenu.MainMenuItem;
import pl.pelotasplus.droidreads.services.OAuthAccessTokenRequest;
import pl.pelotasplus.droidreads.services.OAuthRequestTokenRequest;
import pl.pelotasplus.droidreads.services.OAuthSpiceService;

public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();

    @InjectView(R.id.webView)
    WebView webView;

    @InjectView(R.id.progressContainer)
    View progressContainer;

    @InjectView(R.id.loginButton)
    Button loginButton;

    @Inject
    User currentUser;

    @Inject
    PreferencesStore preferencesStore;

    MainActivityInterface mainActivityInterface;
    ToolbarInterface toolbarInterface;

    boolean callbackProcessed = false;

    private SpiceManager spiceManager = new SpiceManager(OAuthSpiceService.class);

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.getInstance().inject(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.inject(this, v);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.clearCache(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                processOnPageStarted(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressContainer.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setVisibility(View.GONE);
                progressContainer.setVisibility(View.VISIBLE);

                processOnLoginButtonClicked();
            }
        });

        return v;
    }

    @Override
    public void onStop() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();

        toolbarInterface.hideToolbar();
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mainActivityInterface = (MainActivityInterface) activity;
        toolbarInterface = (ToolbarInterface) activity;
    }

    /* logic goes here */

    private void processOnLoginButtonClicked() {
        OAuthRequestTokenRequest requestTokenRequest = new OAuthRequestTokenRequest();
        spiceManager.execute(requestTokenRequest, new OAuthRequestTokenListener());
    }

    private synchronized void processOnPageStarted(String url) {
        if (!url.startsWith(BuildConfig.GOODREADS_CALLBACK_URL)) {
            return;
        }

        if (callbackProcessed) {
            Log.d(TAG, "callback already processed; ignoring");
            return;
        }

        callbackProcessed = true;

        Uri uri = Uri.parse(url);

        final String oauth_token = uri.getQueryParameter(OAuth.OAUTH_TOKEN);
        final String authorize = uri.getQueryParameter("authorize");

        if (authorize.equals("1")) {
            OAuthAccessTokenRequest accessTokenRequest = new OAuthAccessTokenRequest(oauth_token);
            spiceManager.execute(accessTokenRequest, new OAuthAccessTokenListener());
        } else {
            Toast.makeText(getActivity(), "Authorization error", Toast.LENGTH_LONG).show();
        }
    }

    private class OAuthRequestTokenListener implements RequestListener<String> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            spiceException.printStackTrace();
        }

        @Override
        public void onRequestSuccess(String authorizeUrl) {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse(authorizeUrl));
//            getActivity().startActivity(intent);
            webView.loadUrl(authorizeUrl);
        }
    }


    private class OAuthAccessTokenListener implements RequestListener<User> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            spiceException.printStackTrace();
        }

        @Override
        public void onRequestSuccess(User user) {
            if (user == null) {
                Toast.makeText(getActivity(), "Authorization error: user == null", Toast.LENGTH_LONG).show();
                return;
            }

            preferencesStore.setCurrentUser(user);

            mainActivityInterface.showNavigationDrawer();
            mainActivityInterface.showMenuItem(MainMenuItem.UPDATES);
        }
    }
}