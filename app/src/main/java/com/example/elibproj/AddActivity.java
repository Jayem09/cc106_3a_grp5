package com.example.elibproj;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import android.content.Intent;

public class AddActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button add_button, upload_file_button;
    TextView file_path_display;
    private static final int PICK_PDF_FILE = 1;
    private Uri selectedFileUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Initialize views
        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);
        upload_file_button = findViewById(R.id.upload_file);
        file_path_display = findViewById(R.id.file_path_display); // TextView to display file path

        // Upload button click handler
        upload_file_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the file picker for PDF
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, PICK_PDF_FILE);
            }
        });

        // Add button click handler
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data from EditTexts
                String title = title_input.getText().toString().trim();
                String author = author_input.getText().toString().trim();
                String pagesStr = pages_input.getText().toString().trim();

                if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int pages = Integer.parseInt(pagesStr);

                // Get file path from URI (if file is selected)
                String filePath = selectedFileUri != null ? getFilePathFromUri(selectedFileUri) : null;

                // Create an instance of the database helper and insert the book
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(title, author, pages, filePath); // Assuming you still want to save book details

                // Save the PDF file in the database
                if (selectedFileUri != null) {
                    String fileName = getFileNameFromUri(selectedFileUri);
                    myDB.addPdfFile(fileName, filePath); // Save PDF details
                }

                // Clear input fields after inserting the book
                title_input.setText("");
                author_input.setText("");
                pages_input.setText("");
                file_path_display.setText("");  // Clear the file path display

                // Launch BookListActivity after adding the book
                Intent intent = new Intent(AddActivity.this, BookListActivity.class);
                startActivity(intent);
                finish(); // Optionally call finish() to close AddActivity
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_PDF_FILE) {
            selectedFileUri = data.getData();  // Get the URI of the selected file
            String fileName = getFileNameFromUri(selectedFileUri);  // Get the file name
            file_path_display.setText(fileName);  // Display file name in the TextView
        }
    }

    private String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            // Query the content resolver to get the file name
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    result = cursor.getString(nameIndex);
                }
            }
        }
        return result != null ? result : uri.getLastPathSegment();
    }

    private String getFilePathFromUri(Uri uri) {
        // Here we just return the path as a string
        return uri.getPath();

    }

}