package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

public class Challenge {
    @Element
    String challenge_type;

    @Element
    long completed_count;

    @Element(required = false)
    String completion_description;

    @Element
    String created_at;

    @Element(required = false)
    long creator_user_id;

    @Element
    String description;

    @Element
    String end_date;

    @Element(required = false)
    long group_id;

    @Element
    long id;

    @Element
    String image_uploaded_at;

    @Element(required = false)
    String s3_image_at;

    @Element
    String start_date;

    @Element
    String title;

    @Element
    String updated_at;

    @Element
    long user_challenges_count;

    @Override
    public String toString() {
        return "Challenge{" +
                "challenge_type='" + challenge_type + '\'' +
                ", completed_count=" + completed_count +
                ", completion_description='" + completion_description + '\'' +
                ", created_at='" + created_at + '\'' +
                ", creator_user_id=" + creator_user_id +
                ", description='" + description + '\'' +
                ", end_date='" + end_date + '\'' +
                ", group_id=" + group_id +
                ", id=" + id +
                ", image_uploaded_at='" + image_uploaded_at + '\'' +
                ", s3_image_at='" + s3_image_at + '\'' +
                ", start_date='" + start_date + '\'' +
                ", title='" + title + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", user_challenges_count=" + user_challenges_count +
                '}';
    }

    public String getDescription() {
        return description;
    }
}
