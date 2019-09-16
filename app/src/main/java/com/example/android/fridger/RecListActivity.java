package com.example.android.fridger;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class RecListActivity extends AppCompatActivity {

    private static final String TAG = "RecListActivity";

    RecDatabaseHelper mDatabaseHelper;

    private ArrayList<Recipes> mRecipes = new ArrayList<>();

    //variables
    int[] val = new int[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_list);

        mDatabaseHelper = new RecDatabaseHelper(this);

        populateRecipeView();
    }

    private void populateRecipeView() {
        Log.d(TAG, "populateRecipeView: Displaying data in Recipe RecyclerView.");
        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        String ingList = data.getString(5);
        String[] ingListArray = ingList.split("\\\\s*,\\\\s*");
        int j = 0;
        for(int i = 0; i < ingListArray.length;i++){
            int temp = Integer.parseInt(ingListArray[i]);
            if(temp > 0){
                val[j] = temp;
                ++j;
            }
            else{

            }

        }

        //test values

        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            mRecipes.add(new Recipes(data.getString(2), data.getString(1), val, data.getString(3), Integer.parseInt(data.getString(4))));
        }

        initRecyclerViewRecipes();

    }



    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(RecListActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    private void initRecyclerViewRecipes() {
        Log.d(TAG, "initRecyclerViewIngredients: init recyclerView");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_recipes);
        RecyclerRecCreAdapter adapter = new RecyclerRecCreAdapter(RecListActivity.this, mRecipes, mDatabaseHelper);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecListActivity.this));

    }
}
