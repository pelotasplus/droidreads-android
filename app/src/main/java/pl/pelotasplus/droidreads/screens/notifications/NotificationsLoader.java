package pl.pelotasplus.droidreads.screens.notifications;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.pelotasplus.droidreads.api.GoodreadsAPI;
import pl.pelotasplus.droidreads.api.model.NewsFeedResponse;
import pl.pelotasplus.droidreads.api.model.Notification;
import pl.pelotasplus.droidreads.api.model.NotificationsResponse;
import pl.pelotasplus.droidreads.dagger.Injector;
import retrofit.RetrofitError;

public class NotificationsLoader extends AsyncTaskLoader<List<Notification>> {
    private static final String TAG = NotificationsLoader.class.getSimpleName();

    List<Notification> loaderResult = null;

    @Inject
    GoodreadsAPI goodreadsAPI;

    public NotificationsLoader(Context context) {
        super(context);

        Injector.getInstance().inject(this);
    }

    @Override
    public List<Notification> loadInBackground() {
        List<Notification> updates = new ArrayList<Notification>();

        try {
//            NewsFeedResponse newsFeedResponse = goodreadsAPI.newsfeed();

            NotificationsResponse response = goodreadsAPI.notifications();
            updates = response.getNotifications();
        } catch (RetrofitError error) {
            error.printStackTrace();
        }

        return updates;
    }

    @Override
    public void deliverResult(List<Notification> result) {
        loaderResult = result;

        if (isStarted())
            super.deliverResult(result);
    }

    @Override
    protected void onStartLoading() {
        if (loaderResult != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(loaderResult);
        }

        if (takeContentChanged() || loaderResult == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();

        loaderResult = null;
    }
}