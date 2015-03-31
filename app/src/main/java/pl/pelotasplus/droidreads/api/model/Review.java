package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alek on 30/12/14.
 */
public class Review {
    @Element(name = "book-id", required = false)
    long bookId;

    @Element(name = "comments-count", required = false)
    long commentsCount;

    @Element(name = "created-at", required = false)
    String createdAt;

    @Element(required = false)
    String created_at;

    @Element(name = "hidden-flag", required = false)
    boolean hiddenFlag;

    @Element(required = false)
    boolean hidden_flag;

    @Element
    long id;

    @Element(name = "language-code", required = false)
    long languageCode;

    @Element(required = false)
    long language_code;

    @Element(name = "last-comment-at", required = false)
    String lastCommentAt;

    @Element(required = false)
    String last_comment_at;

    @Element(name = "last-revision-at", required = false)
    String lastRevisionAt;

    @Element(required = false)
    String last_revision_at;

    @Element(name = "non-friends-rating-count", required = false)
    long nonFriendsRatingCount;

    @Element(required = false)
    long non_friends_rating_count;

    @Element(required = false)
    String notes;

    @Element
    long rating;

    @Element(name = "ratings-count", required = false)
    long ratingsCount;

    @Element(required = false)
    long ratings_count;

    @Element(name = "ratings-sum", required = false)
    long ratingsSum;

    @Element(required = false)
    long ratings_sum;

    @Element(name = "read-at", required = false)
    String readAt;

    @Element(name = "read-count", required = false)
    String readCount;

    @Element(name = "read-status", required = false)
    String readStatus;

    @Element(required = false)
    String read_status;

    @Element(required = false)
    String recommendation;

    @Element(required = false)
    long recommender_user_id1;

    @Element(name = "recommender-user-id1", required = false)
    long recommenderUserId;

    @Element(required = false)
    String recommender_user_name1;

    @Element(name = "recommender-user-name1", required = false)
    String recommenderUserName;

    @Element(required = false)
    String review;

    @Element(name = "sell-flag", required = false)
    boolean sellFlag;

    @Element(required = false)
    boolean sell_flag;

    @Element(name = "spoiler-flag", required = false)
    boolean spoilerFlag;

    @Element(name = "started-at", required = false)
    String startedAt;

    @Element(name = "updated-at", required = false)
    String updatedAt;

    @Element(required = false)
    String updated_at;

    @Element(name = "user-id", required = false)
    long userId;

    @Element(required = false)
    long user_id;

    @Element(required = false)
    long votes;

    @Element(required = false)
    User user;

    @Element(required = false)
    long weight;

    @Element(name = "work-id", required = false)
    long workId;

    @Element(required = false)
    long work_id;

    @Element(required = false)
    Book book;

    @Element(required = false)
    long book_id;

    @Element(required = false)
    boolean spoiler_flag;

    @Element(required = false)
    String spoilers_state;

    @ElementList(required = false)
    List<Shelve> shelves = new ArrayList<>();

    @Element(required = false)
    String recommended_for;

    @Element(required = false)
    String recommended_by;

    @Element(required = false)
    String started_at;

    @Element(required = false)
    String read_at;

    @Element(required = false)
    String date_added;

    @Element(required = false)
    String date_updated;

    @Element(required = false)
    long read_count;

    @Element(required = false)
    String body;

    @Element(required = false)
    long comments_count;

    @Element(required = false)
    String url;

    @Element(required = false)
    String link;

    @Override
    public String toString() {
        return "Review{" +
                "bookId=" + bookId +
                ", commentsCount=" + commentsCount +
                ", createdAt='" + createdAt + '\'' +
                ", hiddenFlag=" + hiddenFlag +
                ", id=" + id +
                ", languageCode=" + languageCode +
                ", lastCommentAt='" + lastCommentAt + '\'' +
                ", lastRevisionAt='" + lastRevisionAt + '\'' +
                ", nonFriendsRatingCount=" + nonFriendsRatingCount +
                ", notes='" + notes + '\'' +
                ", rating=" + rating +
                ", ratingsCount=" + ratingsCount +
                ", ratingsSum=" + ratingsSum +
                ", readAt='" + readAt + '\'' +
                ", readCount='" + readCount + '\'' +
                ", readStatus='" + readStatus + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", recommenderUserId=" + recommenderUserId +
                ", recommenderUserName='" + recommenderUserName + '\'' +
                ", review='" + review + '\'' +
                ", sellFlag=" + sellFlag +
                ", spoilerFlag=" + spoilerFlag +
                ", startedAt='" + startedAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", userId=" + userId +
                ", weight=" + weight +
                ", workId=" + workId +
                ", book=" + book +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public long getRating() {
        return rating;
    }
}
