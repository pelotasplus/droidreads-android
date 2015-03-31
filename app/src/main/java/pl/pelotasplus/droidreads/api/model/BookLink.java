package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 16/02/15.
 */
public class BookLink {
    @Element
    long id;

    @Element
    String name;

    @Element
    String link;
}
