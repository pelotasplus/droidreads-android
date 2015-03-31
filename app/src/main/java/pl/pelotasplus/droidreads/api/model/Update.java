package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

import pl.pelotasplus.droidreads.DroidreadsApplication;

/**
 * Created by alek on 30/12/14.
 */
public class Update {
    @Attribute(name = "type")
    String updateType;

    @Element
    String action_text;

    @Element
    String link;

    @Element
    String image_url;

    @Element
    Actor actor;

    @Element(required = false)
    Action action;

    @Element
    Date updated_at;

    @Element(required = false)
    String body;

    @Element(name = "object", required = false)
    ObjectWrapper objectWrapper;

    public ObjectWrapper getObjectWrapper() {
        return objectWrapper;
    }

    public UpdateType getUpdateType() {
        return UpdateType.fromString(updateType);
    }

    public String getUpdateTypeAsStr() {
        return updateType;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public String getUpdatedAtAgo() {
        return DroidreadsApplication.dateToAgo(getUpdatedAt());
    }

    public Action getAction() {
        return action;
    }

    public String getActionText() {
        return action_text;
    }

    public Actor getActor() {
        return actor;
    }

    public String getImageUrl() {
        return image_url;
    }

    @Override
    public String toString() {
        return "Update{" +
                "updateType='" + updateType + '\'' +
                ", action_text='" + action_text + '\'' +
                ", link='" + link + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
