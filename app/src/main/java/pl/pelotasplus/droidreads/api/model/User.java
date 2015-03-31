package pl.pelotasplus.droidreads.api.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

import pl.pelotasplus.droidreads.dagger.Injector;

public class User {
    @Attribute(required = false, name = "id")
    long id_attribute = -1;

    @Element(required = false, name = "id")
    long id_element = -1;

    @Element
    String name;

    @Element
    String link;

    @Element(required = false)
    String location;

    @Element(required = false)
    String image_url;

    @Element(required = false)
    String small_image_url;

    @Element(required = false)
    String user_name;

    @Element(required = false)
    boolean is_following;

    @Element(required = false)
    String about;

    @Element(required = false)
    String age;

    @Element(required = false)
    String gender;

    @Element(required = false)
    String joined;

    @Element(required = false)
    String last_active;

    @Element(required = false)
    String website;

    @Element(required = false)
    String interests;

    @Element(required = false)
    String favorite_books;

    @ElementList(required = false)
    List<Author> favorite_authors = new ArrayList<>();

    @Element(required = false)
    boolean friend;

    @Element(required = false)
    String friend_status;

    @Element(required = false)
    String updates_rss_url;

    @Element(required = false)
    String reviews_rss_url;

    @Element(required = false)
    long friends_count;

    @Element(required = false)
    long groups_count;

    @Element(required = false)
    long reviews_count;

    @ElementList(required = false)
    List<UserShelf> user_shelves = new ArrayList<>();

    @ElementList(required = false)
    List<Update> updates = new ArrayList<>();

    @Element(required = false)
    String user_statuses;

    public long getId() {
        if (id_attribute != -1) {
            return id_attribute;
        } else if (id_element != -1) {
            return id_element;
        } else {
            return -1;
        }
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getSmall_image_url() {
        return small_image_url;
    }

    public User() {
        Injector.getInstance().inject(this);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    @Override
    public String toString() {
        return "User{" +
                "id_attribute=" + id_attribute +
                ", id_element=" + id_element +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", location='" + location + '\'' +
                ", image_url='" + image_url + '\'' +
                ", small_image_url='" + small_image_url + '\'' +
                '}';
    }
}
