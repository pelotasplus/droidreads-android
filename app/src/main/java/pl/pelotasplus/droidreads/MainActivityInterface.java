package pl.pelotasplus.droidreads;

import com.github.ksoichiro.android.observablescrollview.Scrollable;

import pl.pelotasplus.droidreads.api.model.Book;
import pl.pelotasplus.droidreads.screens.mainmenu.MainMenuItem;

public interface MainActivityInterface {
    public void showMenuItem(MainMenuItem mainMenuItem);

    public void doLogout();

    public void showNavigationDrawer();

    public void doUnauthorized();

    public void setToolbarTitle(int title);

    public void showBookDetails(Book book);

    public void setScrollable(Scrollable scrollable);
}
