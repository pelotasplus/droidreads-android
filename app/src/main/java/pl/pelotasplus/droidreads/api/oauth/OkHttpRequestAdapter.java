package pl.pelotasplus.droidreads.api.oauth;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import oauth.signpost.http.HttpRequest;
import okio.Buffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class OkHttpRequestAdapter implements HttpRequest {
    private Request request;

    public OkHttpRequestAdapter(Request request) {
        this.request = request;
    }

    @Override
    public String getMethod() {
        return request.method();
    }

    @Override
    public String getRequestUrl() {
        return request.urlString();
    }

    @Override
    public void setRequestUrl(String url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setHeader(String name, String value) {
        request = request.newBuilder().addHeader(name, value).build();
    }

    @Override
    public String getHeader(String name) {
        return request.header(name);
    }

    @Override
    public Map<String, String> getAllHeaders() {
        Headers origHeaders = request.headers();
        HashMap<String, String> headers = new HashMap<String, String>();
        for (int i = 0, count = origHeaders.size(); i < count; ++i) {
            headers.put(origHeaders.name(i), origHeaders.value(i));
        }
        return headers;
    }

    @Override
    public InputStream getMessagePayload() throws IOException {
        RequestBody body = request.body();
        if (body == null)
            return null;
        Buffer buf = new Buffer();
        body.writeTo(buf);
        return buf.inputStream();
    }

    @Override
    public String getContentType() {
        RequestBody body = request.body();
        if (body == null)
            return null;
        MediaType contentType = body.contentType();
        if (contentType == null)
            return null;
        else
            return contentType.toString();
    }

    @Override
    public Request unwrap() {
        return request;
    }
}