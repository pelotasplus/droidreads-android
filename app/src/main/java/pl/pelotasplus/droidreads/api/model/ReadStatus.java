package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 30/12/14.
 */
public class ReadStatus {
    @Element
    long id;

    @Element(required = false)
    String old_status;

    @Element
    long review_id;

    @Element
    String status;

    @Element
    String updated_at;

    @Element
    long user_id;

    @Element(required = false)
    Review review;

    @Override
    public String toString() {
        return "ReadStatus{" +
                "id=" + id +
                ", old_status='" + old_status + '\'' +
                ", review_id=" + review_id +
                ", status='" + status + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public Review getReview() {
        return review;
    }
}
