package pl.pelotasplus.droidreads;

import android.app.Application;
import android.content.Context;
import android.text.format.DateUtils;

import java.util.Date;

import pl.pelotasplus.droidreads.dagger.Injector;

public class DroidreadsApplication extends Application {
    static Context sContext;

    public DroidreadsApplication() {
        sContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Injector.getInstance().init(this);
    }

    public static String dateToAgo(Date date) {
        if (date == null) {
            return "(unknown)";
        }

        return DateUtils.getRelativeDateTimeString(
                sContext,
                date.getTime(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.YEAR_IN_MILLIS,
                0
        ).toString();
    }
}
