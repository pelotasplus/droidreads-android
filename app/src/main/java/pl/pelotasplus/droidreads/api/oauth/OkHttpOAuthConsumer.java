package pl.pelotasplus.droidreads.api.oauth;

import android.util.Log;

import com.squareup.okhttp.Request;
import oauth.signpost.AbstractOAuthConsumer;
import oauth.signpost.http.HttpRequest;

public class OkHttpOAuthConsumer extends AbstractOAuthConsumer {
    public OkHttpOAuthConsumer(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret);
    }

    @Override
    protected HttpRequest wrap(Object o) {
        Log.d("XXX", "object is " + o.getClass().getCanonicalName());
        if (!(o instanceof Request)) {
            throw new IllegalArgumentException(
                    "This consumer expects requests of type "
                            + HttpRequest.class.getCanonicalName());
        } else {
            return new OkHttpRequestAdapter((Request)o);
        }
    }
}