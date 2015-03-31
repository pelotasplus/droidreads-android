package pl.pelotasplus.droidreads;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by alek on 21/03/15.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int colsCount;

    public SpacesItemDecoration(int colsCount, int space) {
        this.space = space;
        this.colsCount = colsCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;

        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildPosition(view) < colsCount) {
//            outRect.top = space;
//        }

        // Right margin only for last element in the row
//        if (parent.getChildPosition(view) % colsCount == (colsCount - 1)) {
//            outRect.right = space;
//        }
    }
}
