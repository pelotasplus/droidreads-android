package pl.pelotasplus.droidreads.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.joanzapata.android.iconify.Iconify;

import pl.pelotasplus.droidreads.R;

/**
 * Created by alek on 22/03/15.
 */
public class RatingStar extends TextView {
    public RatingStar(Context context) {
        super(context);
        init();
    }

    public RatingStar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RatingStar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatingStar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (!isInEditMode())
            Iconify.addIcons(this);
        else
            this.setText(this.getText());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(Iconify.compute(text), type);
    }
}
