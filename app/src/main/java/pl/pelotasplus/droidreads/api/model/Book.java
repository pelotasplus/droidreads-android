package pl.pelotasplus.droidreads.api.model;

import com.google.gson.Gson;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alek on 30/12/14.
 */
public class Book {
    @Element(required = false)
    String asin;

    @Element(required = false)
    long asin_updater_user_id;

    @Element(required = false)
    long author_id;

    @Element(required = false)
    long author_id_updater_user_id;

    @Element
    long id;

    @Element(required = false)
    String isbn;

    @Element(required = false)
    String isbn13;

    @Element(required = false)
    long image_updater_user_id;

    @Element(required = false)
    long isbn13_updater_user_id;

    @Element(required = false)
    long isbn_updater_user_id;

    @Element(required = false)
    String image_url;

    @Element(required = false)
    String small_image_url;

    @Element(required = false)
    String language_code;

    @Element(required = false)
    long language_updater_user_id;

    @Element(required = false)
    long num_pages;

    @Element(required = false)
    long num_pages_updater_user_id;

    @Element(required = false)
    String author_role;

    @Element(required = false)
    long author_role_updater_user_id;

    @Element(required = false)
    long book_authors_count;

    @Element(required = false)
    boolean is_ebook;

    @Element(required = false)
    String created_at;

    @Element(required = false)
    Work work;

    @Element(required = false)
    String description;

    @Element(required = false)
    String description_language_code;

    @Element(required = false)
    long description_updater_user_id;

    @Element(required = false)
    String edition_information;

    @Element(required = false)
    long edition_information_updater_user_id;

    @Element(required = false)
    String format;

    @Element(required = false)
    long format_updater_user_id;

    @Element(required = false)
    String image_uploaded_at;

    @Element(required = false)
    long publication_date_updater_user_id;

    @Element(required = false)
    long publication_day;

    @Element(required = false)
    String link;

    @ElementList(required = false)
    List<Author> authors = new ArrayList<>();

    @Element(required = false)
    long publication_month;

    @Element(required = false)
    long publication_year;

    @Element(required = false)
    String publisher;

    @Element(required = false)
    String publisher_language_code;

    @Element(required = false)
    long publisher_updater_user_id;

    @Element(required = false)
    long ratings_count;

    @Element(required = false)
    long ratings_sum;

    @Element(required = false)
    float average_rating;

    @Element(required = false)
    long reviews_count;

    @Element(required = false)
    String s3_image_at;

    @ElementList(required = false)
    List<Review> friend_reviews = new ArrayList<>();

    @Element(required = false)
    String reviews_widget;

    @ElementList(required = false)
    List<Shelve> popular_shelves = new ArrayList<>();

    @ElementList(required = false)
    List<BookLink> book_links = new ArrayList<>();

    @ElementList(required = false)
    List<SeriesWork> series_works = new ArrayList<>();

    @ElementList(required = false)
    List<Book> similar_books = new ArrayList<>();

    @Element(required = false)
    String sort_by_title;

    @Element(required = false)
    String source_url;

    @Element(required = false)
    long text_reviews_count;

    @Element
    String title;

    @Element(required = false)
    String title_language_code;

    @Element(required = false)
    long title_updater_user_id;

    @Element(required = false)
    String updated_at;

    @Element(required = false)
    String url;

    @Element(required = false)
    long url_updater_user_id;

    @Element(required = false)
    long work_id;

    @Element(required = false)
    Author author;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Book fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Book.class);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getSmallImageUrl() {
        return small_image_url;
    }

    public String getOpenLibraryCoverImageUrl() {
        return String.format(
                "http://covers.openlibrary.org/b/isbn/%s-L.jpg",
                isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "asin='" + asin + '\'' +
                ", author=" + author +
                ", id=" + id +
                ", isbn='" + isbn + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", image_url='" + image_url + '\'' +
                ", small_image_url='" + small_image_url + '\'' +
                ", num_pages=" + num_pages +
                ", ratings_count=" + ratings_count +
                ", ratings_sum=" + ratings_sum +
                ", average_rating=" + average_rating +
                ", reviews_count=" + reviews_count +
                ", s3_image_at='" + s3_image_at + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Author getAuthor() {
        if (author != null) {
            return author;
        }

        if (authors.size() == 0) {
            return null;
        }
        return authors.get(0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Review> getFriendReviews() {
        return friend_reviews;
    }

    public long getReviewsCount() {
        return reviews_count;
    }

    public float getAverageRating() {
        return average_rating;
    }

    public Work getWork() {
        return work;
    }

    public String getFormat() {
        return format;
    }

    public long getNumPages() {
        return num_pages;
    }

    public Shelve getPopularShelve() {
        for (Shelve shelve: popular_shelves) {
            if (shelve.name.equals("to-read")) {
                continue;
            } else if (shelve.name.equals("currently-reading")) {
                continue;
            }

            return shelve;
        }

        return null;
    }

    public List<Book> getSimilarBooks() {
        return similar_books;
    }

    public String getPublisher() {
        return publisher;
    }

    public long getPublicationYear() {
        return publication_year;
    }

    public String getLink() {
        return link;
    }
}
