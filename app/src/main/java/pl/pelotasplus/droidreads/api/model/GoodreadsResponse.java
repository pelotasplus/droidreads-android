package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 28/12/14.
 */
public class GoodreadsResponse {
    @Element(name = "Request")
    Request request;

    @Override
    public String toString() {
        return "GoodreadsResponse{" +
                "request=" + request +
                '}';
    }
}
