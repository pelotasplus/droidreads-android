package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

public class UserStatus {
    @Element(required = false)
    String body;

    @Element
    long book_id;

    @Element(required = false)
    long chapter;

    @Element
    long comments_count;

    @Element
    String created_at;

    @Element
    long id;

    @Element(required = false)
    String last_comment_at;

    @Element(required = false)
    String note_updated_at;

    @Element(required = false)
    String note_uri;

    @Element(required = false)
    long page;

    @Element
    long percent;

    @Element
    long ratings_count;

    @Element
    String updated_at;

    @Element
    long user_id;

    @Element
    long work_id;

    @Element
    long review_id;

    @Element
    Book book;

    public long getPercent() {
        return percent;
    }

    public Book getBook() {
        return book;
    }

    public long getPage() {
        return page;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "body='" + body + '\'' +
                ", book_id=" + book_id +
                ", chapter=" + chapter +
                ", comments_count=" + comments_count +
                ", created_at='" + created_at + '\'' +
                ", id=" + id +
                ", last_comment_at='" + last_comment_at + '\'' +
                ", note_updated_at='" + note_updated_at + '\'' +
                ", note_uri='" + note_uri + '\'' +
                ", page=" + page +
                ", percent=" + percent +
                ", ratings_count=" + ratings_count +
                ", updated_at='" + updated_at + '\'' +
                ", user_id=" + user_id +
                ", work_id=" + work_id +
                ", review_id=" + review_id +
                ", book=" + book +
                '}';
    }
}
