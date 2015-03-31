package pl.pelotasplus.droidreads.api;

import android.util.Log;

import org.simpleframework.xml.transform.Transform;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTransform implements Transform<Date> {
    private static final String TAG = DateTransform.class.getSimpleName();

    private SimpleDateFormat dateFormat, dateFormat2;

    public DateTransform() {
        // 2014-11-02T13:18:57+00:00
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US); //.SSSZ);

        // Thu, 19 Feb 2015 08:14:50 -0800
        this.dateFormat2 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZZ", Locale.US);
    }

    @Override
    public Date read(String value) throws Exception {
        Date date;
        try {
            date = dateFormat.parse(value);
        } catch (java.text.ParseException exc) {
            try {
                date = dateFormat2.parse(value);
            } catch (java.text.ParseException exc2) {
                Log.d(TAG, "ERROR read: value=" + value + " -> null");
                date = null;
            }
        }
        return date;
    }

    @Override
    public String write(Date value) throws Exception {
        return dateFormat.format(value);
    }

}