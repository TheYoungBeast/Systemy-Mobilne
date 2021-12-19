package android.mobile.zadanie10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class BookDetailsActivity extends AppCompatActivity {
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        if(!getIntent().hasExtra(MainActivity.EXTRA_BOOK))
            return;

        final String gsonString = getIntent().getStringExtra(MainActivity.EXTRA_BOOK);
        Book book = new Gson().fromJson(gsonString, Book.class);

        if(book == null) {
            finish();
            return;
        }

        TextView detailsTitle = findViewById(R.id.details_title);
        TextView detailsAuthors = findViewById(R.id.details_author);
        ImageView imgCoverFull = findViewById(R.id.img_cover_full);

        detailsTitle.setText(book.getTitle());

        if(book.getAuthors() != null)
            detailsAuthors.setText(TextUtils.join(", ", book.getAuthors()));

        if(book.getCover() != null) {
            Picasso.with(this)
                    .load(IMAGE_URL_BASE + book.getCover() + "-L.jpg")
                    .placeholder(R.drawable.ic_book_24).into(imgCoverFull);
        }
        else imgCoverFull.setImageResource(R.drawable.ic_book_24);
    }
}