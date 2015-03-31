package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alek on 30/12/14.
 */
public class UpdatesFriendsResponse extends GoodreadsResponse {
    @ElementList
    List<Update> updates = new ArrayList<>();

    public List<Update> getUpdates() {
        return updates;
    }

    @Override
    public String toString() {
        return "UpdatesFriendsResponse{" +
                "request=" + request +
                ", updates=" + updates +
                '}';
    }
}
