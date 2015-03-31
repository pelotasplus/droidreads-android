package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.pelotasplus.droidreads.DroidreadsApplication;

/**
 * Created by alek on 10/01/15.
 */
public class Notification {
    @Element
    long id;

    @ElementList
    List<User> actors = new ArrayList<>();

    @Element(name = "new")
    boolean flag_new;

    @Element(required = false)
    Date created_at;

    @Element
    NotificationBody body;

    @Element
    String url;

    @Element
    String resource_type;

    @Element
    String group_resource_type;

    @Element
    GroupResource group_resource;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", actors=" + actors +
                ", flag_new=" + flag_new +
                ", created_at='" + created_at + '\'' +
                ", body='" + body + '\'' +
                ", url='" + url + '\'' +
                ", resource_type='" + resource_type + '\'' +
                ", group_resource_type='" + group_resource_type + '\'' +
                ", group_resource=" + group_resource +
                '}';
    }

    public NotificationBody getBody() {
        return body;
    }

    public ResourceType getResourceType() {
        return ResourceType.fromString(resource_type);
    }

    public List<User> getActors() {
        return actors;
    }

    public GroupResource getGroupResource() {
        return group_resource;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public String getCreatedAtAgo() {
        return DroidreadsApplication.dateToAgo(getCreatedAt());
    }
}
