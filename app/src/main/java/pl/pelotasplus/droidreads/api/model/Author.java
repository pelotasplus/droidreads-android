package pl.pelotasplus.droidreads.api.model;

import android.text.TextUtils;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 30/12/14.
 */
public class Author {
    @Element(required = false)
    String about;

    @Element(required = false)
    String author_program_at;

    @Element(required = false)
    long best_book_id;

    @Element(required = false)
    String role;

    @Element(required = false)
    long books_count;

    @Element(required = false)
    String born_at;

    @Element(required = false)
    String country_code;

    @Element(required = false)
    String created_at;

    @Element(required = false)
    String died_at;

    @Element(required = false)
    long fanships_count;

    @Element(required = false)
    String gender;

    @Element(required = false)
    String genre1;

    @Element(required = false)
    String genre2;

    @Element(required = false)
    String genre3;

    @Element(required = false)
    String hometown;

    @Element
    long id;

    @Element(required = false)
    String image_copyright;

    @Element(required = false)
    String image_uploaded_at;

    @Element(required = false)
    String influences;

    @Element
    String name;

    @Element(required = false)
    String name_language_code;

    @Element(required = false)
    String postal_code;

    @Element(required = false)
    String rating_dist;

    @Element(required = false)
    long ratings_count;

    @Element(required = false)
    long ratings_sum;

    @Element(required = false)
    long reviews_count;

    @Element(required = false)
    String s3_image_at;

    @Element(required = false)
    String searched_for_at;

    @Element(required = false)
    long text_reviews_count;

    @Element(required = false)
    String updated_at;

    @Element(required = false)
    long uploader_user_id;

    @Element(required = false)
    String url;

    @Element(required = false)
    long user_id;

    @Element(required = false)
    long works_count;

    @Element(required = false)
    String image_url;

    @Element(required = false)
    String small_image_url;

    @Element(required = false)
    String link;

    @Element(required = false)
    float average_rating;

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        if (!TextUtils.isEmpty(image_url)) {
            image_url = image_url.trim();
        }
        return image_url;
    }

    @Override
    public String toString() {
        return "Author{" +
                "hometown='" + hometown + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", small_image_url='" + small_image_url + '\'' +
                ", link='" + link + '\'' +
                ", average_rating=" + average_rating +
                '}';
    }
}
