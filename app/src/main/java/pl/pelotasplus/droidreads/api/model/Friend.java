package pl.pelotasplus.droidreads.api.model;

import android.content.Context;
import android.text.format.DateUtils;

import org.simpleframework.xml.Element;

import java.util.Date;

import pl.pelotasplus.droidreads.DroidreadsApplication;

public class Friend {
    @Element(required = false)
    Date created_at;

    @Element(required = false)
    Date friend_approved_at;

    @Element
    long friend_user_id;

    @Element
    long id;

    @Element(required = false)
    String relationship;

    @Element(required = false)
    String story;

    @Element(required = false)
    Date updated_at;

    @Element
    boolean updates_flag;

    @Element(required = false)
    Date user_approved_at;

    @Element
    long user_id;

    @Override
    public String toString() {
        return "Friend{" +
                "created_at='" + created_at + '\'' +
                ", friend_approved_at='" + friend_approved_at + '\'' +
                ", friend_user_id=" + friend_user_id +
                ", id=" + id +
                ", relationship='" + relationship + '\'' +
                ", story='" + story + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", updates_flag=" + updates_flag +
                ", user_approved_at='" + user_approved_at + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public String getCreatedAtAgo() {
        return DroidreadsApplication.dateToAgo(getCreatedAt());
    }
}
