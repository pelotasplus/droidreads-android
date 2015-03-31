package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

public class AuthUserResponse extends GoodreadsResponse {
    @Element(name = "user")
    User user;

    @Override
    public String toString() {
        return "AuthUserResponse{" +
                "request=" + request +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }
}
