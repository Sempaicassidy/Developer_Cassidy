package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.cassidy.agromarket.R;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        // Find the app_bar_main layout view
        View app_bar_main = findViewById(R.id.app_bar_main);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CardView cardView1 = app_bar_main.findViewById(R.id.card_view1);
        CardView cardView2 = app_bar_main.findViewById(R.id.card_view2);
       // CardView cardView3 = app_bar_main.findViewById(R.id.card_view3);
        //CardView cardView4 = app_bar_main.findViewById(R.id.card_view4);
        //CardView cardView5 = app_bar_main.findViewById(R.id.card_view5);
        CardView cardView6 = app_bar_main.findViewById(R.id.card_view6);
        //CardView cardView7 = app_bar_main.findViewById(R.id.card7);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboardActivity.this, NewsManagementActivity.class));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboardActivity.this, AnalysisManagementActivity.class));
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboardActivity.this, ProductsManagementActivity.class));
            }
        });

        //cardView4.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
              //  startActivity(new Intent(AdminDashboardActivity.this, LessonsManagementActivity.class));
            //}
        //});

        //cardView5.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
             //   startActivity(new Intent(AdminDashboardActivity.this, PaymentsManagementActivity.class));
            //}
        //});

        //cardView6.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  startActivity(new Intent(AdminDashboardActivity.this, ProductsManagementActivity.class));
           // }
        //});

        //cardView7.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  startActivity(new Intent(AdminDashboardActivity.this, ManageOrdersActivity.class));
            //}
        //});
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // Handle the settings action (if any)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.manage_account) {
            intent = new Intent(AdminDashboardActivity.this, ManageAccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.user_support) {
            intent = new Intent(AdminDashboardActivity.this, UserSupportActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            intent = new Intent(AdminDashboardActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            intent = new Intent(AdminDashboardActivity.this, PaymentsActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
