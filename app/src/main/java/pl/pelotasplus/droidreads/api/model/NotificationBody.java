package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 18/01/15.
 */
public class NotificationBody {
    @Element
    String html;

    @Element
    String text;

    @Override
    public String toString() {
        return "NotificationBody{" +
                "html='" + html + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }
}
