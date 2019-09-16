package com.example.android.fridger;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {

    private static final String TAG = "IngredientsActivity";

    IngDatabaseHelper mDatabaseHelper;
    private ArrayList<Ingredients> mIngredients = new ArrayList<Ingredients>();
    String intentString = "";
    int[] ingNumbers = new int[100];
    int j = 0;
    boolean temperChecker = false;
//    //vars
//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Log.d(TAG, "onCreate: started");

        //Initializing the ingredients.
//        iniIngredients();
        mDatabaseHelper = new IngDatabaseHelper(this);

        populateIngredients();
        initRecyclerViewIngredients();





        Button selectButton = (Button) findViewById(R.id.selectedButton);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < mIngredients.size();i++){
                    if(mIngredients.get(i).getCheck()){
                        for(int k = 0; k < j; k++){
                            if(ingNumbers[k] == mIngredients.get(i).getPos()){
                                temperChecker = true;
                            }
                            else{

                            }
                        }
                        if(temperChecker){
                            temperChecker = false;
                        }
                        else {
                            intentString = intentString.concat(mIngredients.get(i).getIngredientsName() + ", ");
                            ingNumbers[j] = mIngredients.get(i).getPos();
                            ++j;
                        }

                    }
                    else{

//                        intentString = intentString.concat(mIngredients.get(i).getIngredientsName() + "FALSE, \n");
                    }
                }
                //Go to SelectedActivity
                Intent intentSelect = new Intent(IngredientsActivity.this, SelectedActivity.class);
                intentSelect.putExtra("textMessage", intentString);
                int[] testNumbers = {1, 2, 3, 4, 5, 6, 7, 9, 15};
                intentSelect.putExtra("ingredientNumbers", ingNumbers);
                startActivity(intentSelect);
            }
        });

    }

    private void populateIngredients() {
        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            mIngredients.add(new Ingredients(data.getString(1)));
        }
    }


    private void iniIngredients(){
        Log.d(TAG, "iniIngredients: preparing ingredients.");

        mIngredients.add(new Ingredients("Apple"));
        mIngredients.add(new Ingredients("Orange"));
        mIngredients.add(new Ingredients("Grape"));
        mIngredients.add(new Ingredients("Banana"));
        mIngredients.add(new Ingredients("Salt"));
        mIngredients.add(new Ingredients("Pepper"));
        mIngredients.add(new Ingredients("Cinnamon"));
        mIngredients.add(new Ingredients("Sauce"));
        mIngredients.add(new Ingredients("Chocolate"));
        mIngredients.add(new Ingredients("Rice"));
        mIngredients.add(new Ingredients("Alcohol"));
        mIngredients.add(new Ingredients("Pickles"));
        mIngredients.add(new Ingredients("Flour"));
        mIngredients.add(new Ingredients("Wheat"));
        mIngredients.add(new Ingredients("Eggs"));
        mIngredients.add(new Ingredients("Corn"));
        mIngredients.add(new Ingredients("Syrup"));
        mIngredients.add(new Ingredients("Soda"));
        mIngredients.add(new Ingredients("Soy Sauce"));
        mIngredients.add(new Ingredients("Vinegar"));
        mIngredients.add(new Ingredients("Beef"));
        mIngredients.add(new Ingredients("Chicken"));
        mIngredients.add(new Ingredients("Fish"));
        mIngredients.add(new Ingredients("Onion"));
        mIngredients.add(new Ingredients("Sugar"));
        mIngredients.add(new Ingredients("Carrots"));
        mIngredients.add(new Ingredients("Lettuce"));
        mIngredients.add(new Ingredients("Tomatoes"));
        mIngredients.add(new Ingredients("Bread"));




    }

    private void initRecyclerViewIngredients(){
        Log.d(TAG, "initRecyclerViewIngredients: init recyclerView.");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_ingredients);
        RecyclerIngViewAdapter adapter = new RecyclerIngViewAdapter(this, mIngredients);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_share:
                Intent intent = new Intent(IngredientsActivity.this, CreateIngredients.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
