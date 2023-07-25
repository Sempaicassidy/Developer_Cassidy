package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.cassidy.agromarket.R;
//import com.mancj.materialsearchbar.MaterialSearchBar;
//import com.mancj.materialsearchbar.OnSearchActionListener;

public class MarketsActivity extends AppCompatActivity implements View.OnClickListener {
    //private MaterialSearchBar searchBar;

    private CardView card_view1, card_view2, card_view3, card_view4, card_view5, card_view6,
            card_view7, card_view8, card_view9, card_view10, card_view11, card_view12, card_view13,
            card_view14, card_view15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markets);
        //grid1 card declaration
        card_view1 = findViewById(R.id.card1);
        card_view2 = findViewById(R.id.card2);
        card_view3 = findViewById(R.id.card3);
        card_view4 = findViewById(R.id.card4);
        card_view5 = findViewById(R.id.card5);
        card_view6 = findViewById(R.id.card6);
        card_view7 = findViewById(R.id.card7);
        card_view8 = findViewById(R.id.card8);
        card_view9 = findViewById(R.id.card9);
        card_view10 = findViewById(R.id.card10);
        card_view11 = findViewById(R.id.card11);
        card_view12 = findViewById(R.id.card12);
        card_view13 = findViewById(R.id.card13);
        card_view14 = findViewById(R.id.card14);
        card_view15 = findViewById(R.id.card15);

        //grid1 card
        card_view1.setOnClickListener(this);
        card_view2.setOnClickListener(this);
        card_view3.setOnClickListener(this);
        card_view4.setOnClickListener(this);
        card_view5.setOnClickListener(this);
        card_view6.setOnClickListener(this);
        card_view7.setOnClickListener(this);
        card_view8.setOnClickListener(this);
        card_view9.setOnClickListener(this);
        card_view10.setOnClickListener(this);
        card_view11.setOnClickListener(this);
        card_view12.setOnClickListener(this);
        card_view13.setOnClickListener(this);
        card_view14.setOnClickListener(this);
        card_view15.setOnClickListener(this);

        // Initialize the MaterialSearchBar
       // searchBar = findViewById(R.id.searchBar);

        // Set the OnSearchActionListener to handle search actions
       /* searchBar.setOnSearchActionListener(new OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                // Called when the search bar's state changes (opened or closed)
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                // Called when the user presses the search button on the keyboard
                performSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                // Called when one of the search bar buttons (e.g., NavIcon, ClearIcon) is clicked
                switch (buttonCode) {
                    case MaterialSearchBar.BUTTON_NAVIGATION:
                        // Handle the navigation button click
                        break;
                    case MaterialSearchBar.BUTTON_SPEECH:
                        // Handle the speech button click
                        break;
                    case MaterialSearchBar.BUTTON_BACK:
                        // Handle the back button click
                        break;
                }
            }
        });*/
    }

    /*private void performSearch(String query) {
        // Implement your search logic here
        // For example, you can use this query to search your products
        // and display the results in a RecyclerView or ListView.
        // Here, I'm just displaying a toast message as an example:

        Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.card1) {
            intent = new Intent(MarketsActivity.this, GrainsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card2) {
            intent = new Intent(MarketsActivity.this, PulsesActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card3) {
            intent = new Intent(MarketsActivity.this, TurbersActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card4) {
            intent = new Intent(MarketsActivity.this, FruitsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card5) {
            intent = new Intent(MarketsActivity.this, VegetablesActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card6) {
            intent = new Intent(MarketsActivity.this, LegumesActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card7) {
            intent = new Intent(MarketsActivity.this, OilSeedsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card8) {
            intent = new Intent(MarketsActivity.this, SpicesActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card9) {
            intent = new Intent(MarketsActivity.this, HerbsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card10) {
            intent = new Intent(MarketsActivity.this, NutsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card11) {
            intent = new Intent(MarketsActivity.this, TeaActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card12) {
            intent = new Intent(MarketsActivity.this, CofeeActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card13) {
            intent = new Intent(MarketsActivity.this, HoneyActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card14) {
            intent = new Intent(MarketsActivity.this, SugarCanActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card15) {
            intent = new Intent(MarketsActivity.this, LiveStocksActivity.class);
            startActivity(intent);
        }
    }
}
