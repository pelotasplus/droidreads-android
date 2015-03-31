package pl.pelotasplus.droidreads.spans;

import android.text.TextPaint;

class StaticColourSpan extends TextColourSpan {
    private final int colour;

    public StaticColourSpan(int colour) {
        super();
        this.colour = colour;
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setColor(colour);
    }
}