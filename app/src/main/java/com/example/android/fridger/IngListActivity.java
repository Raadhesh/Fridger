package com.example.android.fridger;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class IngListActivity extends AppCompatActivity {

    private static final String TAG = "IngListActivity";

    IngDatabaseHelper mDatabaseHelper;

    private ListView mListView;

    private ArrayList<Ingredients> mIngredients = new ArrayList<>();

    //variables
    String intentString = "";
    int[] ingNumbers = new int[100];
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ing_list);


        mDatabaseHelper = new IngDatabaseHelper(this);

        populateListView();

    }


    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            mIngredients.add(new Ingredients(data.getString(1)));
        }
        initRecyclerViewIngredients();
    }


    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(IngListActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    private void initRecyclerViewIngredients() {
        Log.d(TAG, "initRecyclerViewIngredients: init recyclerView.");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_ingredients);
        RecyclerIngCreAdapter adapter = new RecyclerIngCreAdapter(IngListActivity.this, mIngredients, mDatabaseHelper);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(IngListActivity.this));

    }
}
