package com.cassidy.agromarket.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.utilities.DatabaseHelper;

public class NewsManagementActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_PDF = 1;
    private Uri selectedPdfUri;
    private DatabaseHelper databaseHelper;
    private EditText titleEditText;
    private EditText dateEditText;
    private EditText descriptionEditText;
    private int PICK_PDF_REQUEST = 1;
    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_management);

        titleEditText = findViewById(R.id.Title);
        dateEditText = findViewById(R.id.date);
        descriptionEditText = findViewById(R.id.newsDescription);
        Button addNewsPdfButton = findViewById(R.id.document);
        Button postNewsButton = findViewById(R.id.post);

        databaseHelper = new DatabaseHelper(this);
        addNewsPdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
            }
        });

        // Set the click listener for "Post News" button
        postNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewsToDatabase();
            }
        });
        // Set the contentResolver
        setContentResolver(getContentResolver());
    }

    // Method to open the file picker to select a PDF file
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the file picker request was successful and a file was selected
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedPdfUri = data.getData();
            // TODO: You can display the selected PDF file name to the user if needed
        }
    }

    private void saveNewsToDatabase() {
        // Get the news data from the EditText fields
        String title = titleEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        // Validate the input fields
        if (title.isEmpty() || date.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the news data to the database
        long newRowId = databaseHelper.insertNewsData(title, date, description);

        if (newRowId != -1) {
            Toast.makeText(this, "News saved successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error saving news.", Toast.LENGTH_SHORT).show();
        }

        // Save the PDF file to the database
        if (selectedPdfUri != null) {
            // Get the PDF file name from the URI
            String pdfFileName = getFileNameFromUri(selectedPdfUri);

            boolean isPdfSaved = databaseHelper.insertNews(newRowId, pdfFileName, selectedPdfUri);

            if (isPdfSaved) {
                // PDF file saved successfully
                Toast.makeText(this, "PDF file saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                // Failed to save the PDF file
                Toast.makeText(this, "Error saving PDF file.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        if (uri.getScheme().equals("content")) {
            // For content URIs, query the MediaStore to get the file name
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (nameIndex != -1) {
                    fileName = cursor.getString(nameIndex);
                }
                cursor.close();
            }
        } else if (uri.getScheme().equals("file")) {
            // For file URIs, simply get the last segment of the path as the file name
            fileName = uri.getLastPathSegment();
        }
        return fileName;
    }
    // Getter method for contentResolver
    public ContentResolver getContentResolver() {
        return contentResolver;
    }

    // Setter method for contentResolver
    public void setContentResolver(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }
}
