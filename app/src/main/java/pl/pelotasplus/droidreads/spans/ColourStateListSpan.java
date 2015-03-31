package pl.pelotasplus.droidreads.spans;

import android.content.res.ColorStateList;
import android.text.TextPaint;

class ColourStateListSpan extends TextColourSpan {
    private final ColorStateList colorStateList;

    public ColourStateListSpan(ColorStateList colorStateList) {
        super();
        this.colorStateList = colorStateList;
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setColor(colorStateList.getColorForState(tp.drawableState, 0));
    }
}