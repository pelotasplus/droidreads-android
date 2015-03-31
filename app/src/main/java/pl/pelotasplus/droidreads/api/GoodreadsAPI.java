package pl.pelotasplus.droidreads.api;

import pl.pelotasplus.droidreads.api.model.AuthUserResponse;
import pl.pelotasplus.droidreads.api.model.BookShowResponse;
import pl.pelotasplus.droidreads.api.model.NewsFeedResponse;
import pl.pelotasplus.droidreads.api.model.NotificationsResponse;
import pl.pelotasplus.droidreads.api.model.UpdatesFriendsResponse;
import pl.pelotasplus.droidreads.api.model.UserShowResponse;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GoodreadsAPI {
    @GET("/api/auth_user")
    AuthUserResponse auth_user();

    @GET("/api/v3/updates/newsfeed.xml")
    NewsFeedResponse newsfeed();

    // http://www.goodreads.com/api/v3/updates/newsfeed.xml?key=dcX1YH52UMjs6lznO2MuSg&format=xml&max
    // _updates=10&nonsocial_only=false&skip_cache=true&_nc=1

    // http://www.goodreads.com/api/v3/updates/newsfeed.xml?key=dcX1YH52UMjs6lznO2MuSg&format=xml&max
    //_updates=10&max_updated_at=1426968995&nonsocial_only=false&skip_cache=true&_nc=1

    // http://www.goodreads.com/api/v3/updates/newsfeed.xml?key=dcX1YH52UMjs6lznO2MuSg&format=xml&max
    // _updates=10&max_updated_at=1426148324&nonsocial_only=false&skip_cache=true&_nc=1

    // http://www.goodreads.com/api/current_user_data.xml?key=dcX1YH52UMjs6lznO2MuSg
    // &ad_favorite_genres=true&facebook_cog_settings=true&_nc=1

    @GET("/updates/friends.xml")
    UpdatesFriendsResponse updates_friends();

    @GET("/notifications.xml")
    NotificationsResponse notifications();

    @GET("/user/show/{id}.xml")
    UserShowResponse user_show(@Path("id") long id);

    @GET("/book/show/{id}?format=xml")
    BookShowResponse book_show(@Path("id") long id);
}