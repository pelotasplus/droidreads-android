package pl.pelotasplus.droidreads.api.oauth;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import oauth.signpost.AbstractOAuthProvider;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.http.HttpResponse;

public class OkHttpOAuthProvider extends AbstractOAuthProvider {
    private static final String TAG = OkHttpOAuthProvider.class.getSimpleName();

    public OkHttpOAuthProvider(String requestTokenEndpointUrl, String accessTokenEndpointUrl, String authorizationWebsiteUrl) {
        super(requestTokenEndpointUrl, accessTokenEndpointUrl, authorizationWebsiteUrl);
    }

    @Override
    protected HttpRequest createRequest(String endpointUrl) throws Exception {
        Request request = new Request.Builder()
                .url(endpointUrl)
                .build();

        return new OkHttpRequestAdapter(request);

        /*
        HttpURLConnection connection = (HttpURLConnection) new URL(endpointUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setAllowUserInteraction(false);
        connection.setRequestProperty("Content-Length", "0");
        return new HttpURLConnectionRequestAdapter(connection);

        return new OkHttpRequestAdapter(request);
        */
    }

    @Override
    protected HttpResponse sendRequest(HttpRequest request) throws Exception {
        Request okRequest = ((OkHttpRequestAdapter) request).unwrap();

        Log.d(TAG, "request=" + okRequest);

        OkHttpClient okClient = new OkHttpClient();
        Response response = okClient.newCall(okRequest).execute();

        Log.d(TAG, "response=" + response);

        return new OkHttpResponseAdapter(response);
    }

//    class LoggingInterceptor implements Interceptor {
//        @Override public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//
//            long t1 = System.nanoTime();
//            logger.info(String.format("Sending request %s on %s%n%s",
//                    request.url(), chain.connection(), request.headers()));
//
//            Response response = chain.proceed(request);
//
//            long t2 = System.nanoTime();
//            logger.info(String.format("Received response for %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//
//            return response;
//        }
//    }
}
