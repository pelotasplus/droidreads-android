package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 23/03/15.
 */
public class NotificationsCounts {
    @Element
    long notifications_unviewed_count;

    @Element
    long messages_unread_count;

    @Element
    long friend_request_count;

    @Override
    public String toString() {
        return "NotificationsCounts{" +
                "notifications_unviewed_count=" + notifications_unviewed_count +
                ", messages_unread_count=" + messages_unread_count +
                ", friend_request_count=" + friend_request_count +
                '}';
    }
}
