package pl.pelotasplus.droidreads.screens.mainmenu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.pelotasplus.droidreads.MainActivityInterface;
import pl.pelotasplus.droidreads.R;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuEntryViewHolder> {
    MainActivityInterface mainActivityInterface;

    public MainMenuAdapter(MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
    }

    @Override
    public int getItemCount() {
        return MainMenuItem.LAST.ordinal() + 1;
    }

    @Override
    public MainMenuEntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_main_menu_item, viewGroup, false);
        return new MainMenuEntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainMenuEntryViewHolder viewHolder, int position) {
        final MainMenuItem mainMenuItem = MainMenuItem.values()[position];

        viewHolder.button.setText(mainMenuItem.labelId);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityInterface.showMenuItem(mainMenuItem);
            }
        });
    }
}