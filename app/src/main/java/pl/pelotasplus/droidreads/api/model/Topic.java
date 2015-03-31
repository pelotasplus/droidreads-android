package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

public class Topic {
    @Element
    long actual_views_count;

    @Element(required = false)
    long author_id;

    @Element(required = false)
    long author_user_id;

    @Element(required = false)
    long book_id;

    @Element(required = false)
    String closed_at;

    @Element(required = false)
    long comments_count;

    @Element(required = false)
    long context_id;

    @Element(required = false)
    String context_type;

    @Element(required = false)
    String created_at;

    @Element(required = false)
    long folder_id;

    @Element(required = false)
    long id;

    @Element(required = false)
    String important_at;

    @Element(required = false)
    String last_comment_at;

    @Element(required = false)
    long last_comment_id;

    @Element(required = false)
    boolean question_flag;

    @Element(required = false)
    String title;

    @Element(required = false)
    long topic_views_count;

    @Element(required = false)
    String updated_at;

    @Element(required = false)
    long work_id;

    @Override
    public String toString() {
        return "Topic{" +
                "actual_views_count=" + actual_views_count +
                ", author_id=" + author_id +
                ", author_user_id=" + author_user_id +
                ", book_id=" + book_id +
                ", closed_at='" + closed_at + '\'' +
                ", comments_count=" + comments_count +
                ", context_id=" + context_id +
                ", context_type='" + context_type + '\'' +
                ", created_at='" + created_at + '\'' +
                ", folder_id=" + folder_id +
                ", id=" + id +
                ", important_at='" + important_at + '\'' +
                ", last_comment_at='" + last_comment_at + '\'' +
                ", last_comment_id=" + last_comment_id +
                ", question_flag=" + question_flag +
                ", title='" + title + '\'' +
                ", topic_views_count=" + topic_views_count +
                ", updated_at='" + updated_at + '\'' +
                ", work_id=" + work_id +
                '}';
    }

    public String getTitle() {
        return title;
    }
}
