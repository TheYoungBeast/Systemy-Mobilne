package android.mobile.zadanie9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity
{

    public static final String EXTRA_EDIT_BOOK_TITLE = "pb.edu.pl.EDIT_BOOK_TITLE";
    public static final String EXTRA_EDIT_BOOK_AUTHOR = "pb.edu.pl.EDIT_BOOK_AUTHOR";
    public static final String EXTRA_EDIT_BOOK_ID = "pb.edu.pl.EDIT_BOOK_ID";

    private EditText editTitleEditText;
    private EditText editAuthorEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        editTitleEditText=findViewById(R.id.edit_book_title);
        editAuthorEditText=findViewById(R.id.edit_book_author);

        if(getIntent().hasExtra(EXTRA_EDIT_BOOK_TITLE) && getIntent().hasExtra(EXTRA_EDIT_BOOK_AUTHOR))
        {
            editTitleEditText.setText(getIntent().getStringExtra(EXTRA_EDIT_BOOK_TITLE));
            editAuthorEditText.setText(getIntent().getStringExtra(EXTRA_EDIT_BOOK_AUTHOR));
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(editTitleEditText.getText()) || TextUtils.isEmpty(editAuthorEditText.getText()))
                    setResult(RESULT_CANCELED, replyIntent);
                else
                {
                    String title = editTitleEditText.getText().toString();
                    replyIntent.putExtra(EXTRA_EDIT_BOOK_TITLE,title);
                    String author = editAuthorEditText.getText().toString();
                    replyIntent.putExtra(EXTRA_EDIT_BOOK_AUTHOR,author);
                    if(getIntent().hasExtra(EXTRA_EDIT_BOOK_ID))
                        replyIntent.putExtra(EXTRA_EDIT_BOOK_ID, getIntent().getIntExtra(EXTRA_EDIT_BOOK_ID, -1));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }


}