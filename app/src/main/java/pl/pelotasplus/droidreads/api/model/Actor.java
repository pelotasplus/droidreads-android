package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by alek on 30/12/14.
 */
public class Actor {
    @Element
    long id;

    @Element
    String name;

    @Element
    String image_url;

    @Element
    String link;

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return image_url;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
