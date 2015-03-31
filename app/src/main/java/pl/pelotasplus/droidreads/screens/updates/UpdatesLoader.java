package pl.pelotasplus.droidreads.screens.updates;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.pelotasplus.droidreads.MainActivityInterface;
import pl.pelotasplus.droidreads.api.GoodreadsAPI;
import pl.pelotasplus.droidreads.api.model.Update;
import pl.pelotasplus.droidreads.api.model.UpdatesFriendsResponse;
import pl.pelotasplus.droidreads.dagger.Injector;
import retrofit.RetrofitError;

public class UpdatesLoader extends AsyncTaskLoader<List<Update>> {
    private static final String TAG = UpdatesLoader.class.getSimpleName();

    List<Update> loaderResult = null;

    @Inject
    GoodreadsAPI goodreadsAPI;

    MainActivityInterface mainActivityInterface;

    public UpdatesLoader(Context context) {
        super(context);

        Injector.getInstance().inject(this);

        mainActivityInterface = (MainActivityInterface) context;
    }

    @Override
    public List<Update> loadInBackground() {
        List<Update> updates = new ArrayList<Update>();

        try {
            UpdatesFriendsResponse response = goodreadsAPI.updates_friends();
            if (response != null) {
                updates = response.getUpdates();
            }
        } catch (RetrofitError error) {
            handleApiError(error);
        }

        return updates;
    }

    private void handleApiError(RetrofitError error) {
        error.printStackTrace();

        if (error.getKind() == RetrofitError.Kind.HTTP) {
            if (error.getResponse().getStatus() == 401) {
                mainActivityInterface.doUnauthorized();
            }
        }
    }

    @Override
    public void deliverResult(List<Update> result) {
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