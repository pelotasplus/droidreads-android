package pl.pelotasplus.droidreads.api.googlebooks.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alek on 19/03/15.
 */
public class VolumeInfo {
    String title;
    List<String> authors = new ArrayList<>();
    String publisher;
    String publishedDate;
    String description;

    /*
    industryIdentifiers: [
    {
        type: "ISBN_10",
                identifier: "0811221504"
    },
    {
        type: "ISBN_13",
                identifier: "9780811221504"
    }
    ],
    readingModes: {
        text: true,
                image: false
    },
    */

    long pageCount;
    long printedPageCount;

    /*
    dimensions: {
        height: "17.80 cm",
                width: "12.70 cm",
                thickness: "1.00 cm"
    },
    */

    String printType;

    List<String> categories = new ArrayList<>();

    float averageRating;

    long ratingsCount;

    String contentVersion;

    ImageLinks imageLinks = new ImageLinks();

    String language;

    String previewLink;

    String infoLink;

    String canonicalVolumeLink;

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", pageCount=" + pageCount +
                ", printedPageCount=" + printedPageCount +
                ", printType='" + printType + '\'' +
                ", categories=" + categories +
                ", averageRating=" + averageRating +
                ", ratingsCount=" + ratingsCount +
                ", contentVersion='" + contentVersion + '\'' +
                ", imageLinks=" + imageLinks +
                ", language='" + language + '\'' +
                ", previewLink='" + previewLink + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", canonicalVolumeLink='" + canonicalVolumeLink + '\'' +
                '}';
    }
}
