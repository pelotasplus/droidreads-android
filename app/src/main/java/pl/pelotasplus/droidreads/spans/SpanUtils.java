package pl.pelotasplus.droidreads.spans;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alek on 30/03/15.
 */
public final class SpanUtils {
    private SpanUtils() {
    }

    private static CharSequence createSpannable(Context context, int stringId, Pattern pattern, CharacterStyle... styles) {
        String string = context.getString(stringId);
        return createSpannable(string, pattern, styles);
    }

    private static CharSequence createSpannable(Context context, String string, Pattern pattern, CharacterStyle... styles) {
        return createSpannable(string, pattern, styles);
    }

    private static CharSequence createSpannable(CharSequence source, Pattern pattern, CharacterStyle... styles) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(source);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            applyStylesToSpannable(spannableStringBuilder, start, end, styles);
        }
        return spannableStringBuilder;
    }

    private static SpannableStringBuilder applyStylesToSpannable(SpannableStringBuilder source, int start, int end, CharacterStyle... styles) {
        for (CharacterStyle style : styles) {
            source.setSpan(CharacterStyle.wrap(style), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return source;
    }

//    public static void formatUsingSpans(TextView textView, Context context, int stringId, String stringPattern, CharacterStyle... styles) {
//        Pattern pattern = Pattern.compile(stringPattern);
//        CharSequence text = SpanUtils.createSpannable(context, stringId, pattern, styles);
//        textView.setText(text);
//    }

//    public static void formatUsingSpans(TextView textView, Context context, int stringId, Pattern pattern, CharacterStyle... styles) {
//        CharSequence text = SpanUtils.createSpannable(context, stringId, pattern, styles);
//        textView.setText(text);
//    }

    public static void formatUsingSpans(TextView textView, String string, Pattern pattern, CharacterStyle... styles) {
        CharSequence text = SpanUtils.createSpannable(string, pattern, styles);
        textView.setText(text);
    }

    public static void formatUsingSpans(TextView textView, String string, String stringPattern, CharacterStyle... styles) {
        Pattern pattern = Pattern.compile(stringPattern);
        CharSequence text = SpanUtils.createSpannable(string, pattern, styles);
        textView.setText(text);
    }
}