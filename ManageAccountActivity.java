package com.cassidy.agromarket.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.ManageUserAdapter;
import com.cassidy.agromarket.models.User;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageAccountActivity extends AppCompatActivity {

    private List<User> userList;
    private DatabaseHelper databaseHelper;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        ManageUserAdapter manageUserAdapter = new ManageUserAdapter(this, userList);
        recyclerView.setAdapter(manageUserAdapter);

        databaseHelper = new DatabaseHelper(this);
        List<User> usersFromDatabase = databaseHelper.getAllSellers();

        if (usersFromDatabase != null && !usersFromDatabase.isEmpty()) {
            userList.addAll(usersFromDatabase);
            manageUserAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(ManageAccountActivity.this, "No user found in the database", Toast.LENGTH_LONG).show();
        }

        // Set item click listener
        manageUserAdapter.setOnItemClickListener(new ManageUserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click event
                User selectedUser = userList.get(position);
                // Do something with the clicked user
                // For example, you can start a new activity to show the details of the user
                // Intent intent = new Intent(ManageAccountActivity.this, UserDetailsActivity.class);
                // intent.putExtra("selectedUser", selectedUser);
                // startActivity(intent);
            }

            @Override
            public void onRemoveButtonClick(int position) {
                // Handle remove button click event
                if (position != RecyclerView.NO_POSITION) {
                    // Retrieve the User object to get the UserId
                    User removedUser = userList.get(position);
                    int userId = removedUser.getId();

                    // Remove the item from the list
                    userList.remove(position);
                    // Notify the adapter about the item removal
                    manageUserAdapter.notifyItemRemoved(position);
                    manageUserAdapter.notifyItemRangeChanged(position, userList.size());

                    // Remove the user from the database using the userId
                    databaseHelper.deleteUser(userId);
                }
            }
        });
    }
}
