package pl.pelotasplus.droidreads.screens.mainmenu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.R;

public class MainMenuEntryViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.button)
    public Button button;

    public MainMenuEntryViewHolder(View v) {
        super(v);

        ButterKnife.inject(this, v);
    }
}