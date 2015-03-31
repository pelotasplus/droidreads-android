package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by alek on 23/03/15.
 */
public class NewsFeedMeta {
    @Element
    long rate_books_count;

    @Element
    boolean fb_connected;

    @Element
    long friends_count;

    @Element
    long min_ratings_for_recs;

    @ElementList
    List<Cta> ctas;
}
