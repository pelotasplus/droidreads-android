package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 23/03/15.
 */
public class Cta {
    @Element
    String id;

    @Element
    String cta_title;

    @Element
    String cta_text;

    @Element
    String background_color;

    @Element
    String text_color;

    @Element
    String link;

    @Element
    String large_image_url;

    @Element
    String image_url;

    @Element
    String small_image_url;

    @Override
    public String toString() {
        return "Cta{" +
                "id='" + id + '\'' +
                ", cta_title='" + cta_title + '\'' +
                ", cta_text='" + cta_text + '\'' +
                ", background_color='" + background_color + '\'' +
                ", text_color='" + text_color + '\'' +
                ", link='" + link + '\'' +
                ", large_image_url='" + large_image_url + '\'' +
                ", image_url='" + image_url + '\'' +
                ", small_image_url='" + small_image_url + '\'' +
                '}';
    }
}
