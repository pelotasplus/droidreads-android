package pl.pelotasplus.droidreads

import android.content.Context
import android.widget.Toast

public class KtTest {
    public fun toast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}