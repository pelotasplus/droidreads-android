package pl.pelotasplus.droidreads.screens.mainmenu;

import pl.pelotasplus.droidreads.R;

public enum MainMenuItem {
    UPDATES(R.drawable.ic_launcher, R.string.menu_updates),
    NOTIFICATIONS(R.drawable.ic_launcher, R.string.menu_notifications),
    LOGIN(R.drawable.ic_launcher, R.string.menu_logout);

    public static MainMenuItem LAST = NOTIFICATIONS;

    public boolean showActionBar;
    public int labelId;
    public int imageId;

    MainMenuItem(int imageId, int labelId) {
        this(imageId, labelId, true);
    }

    MainMenuItem(int imageId, int labelId, boolean showActionBar) {
        this.labelId = labelId;
        this.imageId = imageId;
        this.showActionBar = showActionBar;
    }

    @Override
    public String toString() {
        return "MainMenuItem{" +
                "showActionBar=" + showActionBar +
                ", labelId=" + labelId +
                ", imageId=" + imageId +
                '}';
    }
}