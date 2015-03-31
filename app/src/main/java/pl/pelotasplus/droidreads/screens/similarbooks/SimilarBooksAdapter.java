package pl.pelotasplus.droidreads.screens.similarbooks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Book;

/**
 * Created by alek on 20/03/15.
 */
public class SimilarBooksAdapter extends RecyclerView.Adapter<SimilarBooksViewHolder> {
    private static final String TAG = SimilarBooksAdapter.class.getSimpleName();

    List<Book> similarBooks = new ArrayList<>();

    private int bookLimit = -1;

    public SimilarBooksAdapter(int bookLimit) {
        this.bookLimit = bookLimit;
    }

    public SimilarBooksAdapter() {
    }

    public void setSimilarBooks(List<Book> similarBooks) {
        this.similarBooks = similarBooks;
    }

    @Override
    public SimilarBooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_or_book_square, parent, false);
        return new SimilarBooksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SimilarBooksViewHolder holder, int position) {
        Book book = similarBooks.get(position);
        holder.setBook(book);
    }

    @Override
    public int getItemCount() {
        if (bookLimit == -1) {
            return similarBooks.size();
        } else {
            return Math.min(bookLimit, similarBooks.size());
        }
    }
}