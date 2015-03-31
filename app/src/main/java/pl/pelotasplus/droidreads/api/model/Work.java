package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

import java.text.NumberFormat;

/**
 * Created by alek on 15/02/15.
 */
public class Work {
    @Element
    long best_book_id;

    @Element
    long books_count;

    @Element(required = false)
    long default_chaptering_book_id;

    @Element(required = false)
    String default_description_language_code;

    @Element(required = false)
    long desc_user_id;

    @Element
    long id;

    @Element(required = false)
    String media_type;

    @Element(required = false)
    long original_language_id;

    @Element(required = false)
    long original_publication_day;

    @Element(required = false)
    long original_publication_month;

    @Element
    long original_publication_year;

    @Element(required = false)
    String original_title;

    @Element
    String rating_dist;

    @Element
    long ratings_count;

    @Element
    long ratings_sum;

    @Element
    long reviews_count;

    @Element
    long text_reviews_count;

    public long getRatingsCount() {
        return ratings_count;
    }

    public long getReviewsCount() {
        return reviews_count;
    }

    public String getRatingsCountFormatted() {
        return NumberFormat.getInstance().format(ratings_count);
    }
}
