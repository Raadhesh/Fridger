package com.example.android.fridger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateIngredients extends AppCompatActivity {

    IngDatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;
    private ArrayList<Ingredients> mIngredients = new ArrayList<Ingredients>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ingredients);
        editText = (EditText) findViewById(R.id.editTextIngName);
        btnAdd = (Button) findViewById(R.id.btnAddIng);
        btnViewData = (Button) findViewById(R.id.btnViewIng);
        mDatabaseHelper = new IngDatabaseHelper(this);

//        iniIngredients();
//        for(int i = 0; i < mIngredients.size(); i++){
//            AddData(mIngredients.get(i).getIngredientsName());
//        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateIngredients.this, IngListActivity.class);
                startActivity(intent);
            }
        });

    }


    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    private void iniIngredients(){
//        Log.d(TAG, "iniIngredients: preparing ingredients.");

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
}
