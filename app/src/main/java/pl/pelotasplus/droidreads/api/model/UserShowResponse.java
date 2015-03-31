package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

public class UserShowResponse extends GoodreadsResponse {
    @Element
    User user;

    @Override
    public String toString() {
        return "UserShowResponse{" +
                "user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }
}
