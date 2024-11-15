package com.example.elibproj;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    CustomAdapter customAdapter;
    ArrayList<String> book_id, book_title, book_author, book_pages, book_file_path; // Added file path list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        recyclerView = findViewById(R.id.recyclerView);
        myDB = new MyDatabaseHelper(this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        book_file_path = new ArrayList<>(); // Initialize the file path list

        loadBooks();
    }

    private void loadBooks() {
        Cursor cursor = myDB.getAllBooks();
        if (cursor.getCount() == 0) {
            // Handle empty state
        } else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0)); // Assuming the first column is ID
                book_title.add(cursor.getString(1)); // Assuming the second column is Title
                book_author.add(cursor.getString(2)); // Assuming the third column is Author
                book_pages.add(cursor.getString(3)); // Assuming the fourth column is Pages
                book_file_path.add(cursor.getString(4)); // Assuming the fifth column is File Path
            }
            cursor.close();
        }

        // Now create the adapter with all required parameters
        customAdapter = new CustomAdapter(this, this, book_id, book_title, book_author, book_pages, book_file_path);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}