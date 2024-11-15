package com.example.elibproj;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        recyclerView = findViewById(R.id.recycler_view);
        myDB = new MyDatabaseHelper(this);
        bookList = new ArrayList<>();

        // Fetch books from the database
        fetchBooks();

        // Set up RecyclerView
        BookAdapter adapter = new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchBooks() {
        // Fetch all books from the database
        bookList = myDB.getAllBooks(); // Assuming you have a method to get all books
    }
}