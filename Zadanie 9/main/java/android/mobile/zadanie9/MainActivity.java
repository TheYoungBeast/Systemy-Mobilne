package android.mobile.zadanie9;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.mobile.zadanie9.databinding.ActivityMainBinding;

import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static final int NEW_BOOK_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_ACTIVITY_REQUEST_CODE = 2;

    private ActivityMainBinding binding;
    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final BookAdapter adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        bookViewModel.findAll().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapter.setBooks(books);
            }
        });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditBookActivity.class);
                startActivityForResult(intent, NEW_BOOK_ACTIVITY_REQUEST_CODE);
            }
        });
    }


    private class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        private Book book;
        private TextView bookTitleTextView;
        private TextView bookAuthorTextView;

        public BookHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.book_list_item, parent, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            bookTitleTextView = itemView.findViewById(R.id.book_title);
            bookAuthorTextView = itemView.findViewById(R.id.book_author);
        }

        public void bind(Book book) {
            bookTitleTextView.setText(book.getTitle());
            bookAuthorTextView.setText(book.getAuthor());
            this.book = book;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, EditBookActivity.class);
            intent.putExtra(EditBookActivity.EXTRA_EDIT_BOOK_TITLE, bookTitleTextView.getText());
            intent.putExtra(EditBookActivity.EXTRA_EDIT_BOOK_AUTHOR, bookAuthorTextView.getText());
            intent.putExtra(EditBookActivity.EXTRA_EDIT_BOOK_ID, book.getId());
            startActivityForResult(intent, EDIT_BOOK_ACTIVITY_REQUEST_CODE);
        }

        @Override
        public boolean onLongClick(View v) {
            bookViewModel.delete(book);

            Snackbar.make(findViewById(R.id.main_layout),getString(R.string.book_deleted),
                    Snackbar.LENGTH_LONG).show();

            return true;
        }
    }

    private class BookAdapter extends RecyclerView.Adapter<BookHolder>
    {
        private List<Book> books;

        @Override
        public void onBindViewHolder(@NonNull BookHolder holder, int position) {
            if(books != null) {
                Book book = books.get(position);
                holder.bind(book);
            }
            else Log.d("MainActivity", "No books");
        }

        @NonNull
        @Override
        public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookHolder(getLayoutInflater(), parent);
        }

        @Override
        public int getItemCount() {
            if(books != null)
                return books.size();
            else
                return 0;
        }

        void setBooks(List<Book> books) {
            this.books = books;
            notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == NEW_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            Book book = new Book(data.getStringExtra(EditBookActivity.EXTRA_EDIT_BOOK_TITLE),
                    data.getStringExtra(EditBookActivity.EXTRA_EDIT_BOOK_AUTHOR));
            bookViewModel.insert(book);
            Snackbar.make(findViewById(R.id.main_layout),getString(R.string.book_added),
                    Snackbar.LENGTH_LONG).show();
        }
        else if(requestCode == EDIT_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            Book book = new Book(data.getStringExtra(EditBookActivity.EXTRA_EDIT_BOOK_TITLE),
                    data.getStringExtra(EditBookActivity.EXTRA_EDIT_BOOK_AUTHOR));
            int id = data.getIntExtra(EditBookActivity.EXTRA_EDIT_BOOK_ID, -1);
            if(id != -1)
                book.setId(id);
            bookViewModel.update(book);
            Snackbar.make(findViewById(R.id.main_layout),getString(R.string.book_edited),
                    Snackbar.LENGTH_LONG).show();
        }
        else
        {
            Snackbar.make(findViewById(R.id.coordinator_layout),
                    getString(R.string.empty_not_saved),
                    Snackbar.LENGTH_LONG)
                    .show();
        }
    }

}