package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 15/02/15.
 */
public class BookShowResponse extends GoodreadsResponse {
    @Element(required = false)
    Book book;

    @Override
    public String toString() {
        return "BookShowResponse{" +
                "book=" + book +
                '}';
    }

    public Book getBook() {
        return book;
    }
}
