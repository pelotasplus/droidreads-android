package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by alek on 30/12/14.
 */
public class Action {
    @Attribute
    String type;

    @Element
    long rating;

    public long getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Action{" +
                "type='" + type + '\'' +
                ", rating=" + rating +
                '}';
    }
}
