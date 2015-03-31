package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 30/12/14.
 */
public class ObjectWrapper {
    @Element(required = false, name = "read_status")
    ReadStatus readStatus;

    @Element(required = false)
    Book book;

    @Element(required = false, name = "user_status")
    UserStatus userStatus;

    public Book getBook() {
        return book;
    }

    public ReadStatus getReadStatus() {
        return readStatus;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }
}
