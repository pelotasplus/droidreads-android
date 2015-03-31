package pl.pelotasplus.droidreads.api.model;

/**
 * Created by alek on 28/12/14.
 */
public class Request {
    boolean authentication;
    String key;
    String method;

    @Override
    public String toString() {
        return "Request{" +
                "authentication=" + authentication +
                ", key='" + key + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
