package pl.pelotasplus.droidreads.spans;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.style.CharacterStyle;

public abstract class TextColourSpan extends CharacterStyle {
    public static TextColourSpan newInstance(Context context, int resourceId) {
        Resources resources = context.getResources();
        ColorStateList colorStateList = resources.getColorStateList(resourceId);
        if (colorStateList != null) {
            return new ColourStateListSpan(colorStateList);
        }
        int colour = resources.getColor(resourceId);
        if (colour >= 0) {
            return new StaticColourSpan(colour);
        }
        return null;
    }
}