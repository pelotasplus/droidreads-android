package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 23/03/15.
 */
public class NewsFeedResponse extends GoodreadsResponse {
    @Element(name = "notifications_counts")
    NotificationsCounts notificationsCounts;

    @Element(name = "newsfeed_meta")
    NewsFeedMeta newsFeedMeta;
}
