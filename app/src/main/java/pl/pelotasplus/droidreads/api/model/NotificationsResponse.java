package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public class NotificationsResponse extends GoodreadsResponse {
    @ElementList
    List<Notification> notifications = new ArrayList<Notification>();

    public List<Notification> getNotifications() {
        return notifications;
    }

}
