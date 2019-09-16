package com.example.android.fridger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditRecActivity extends AppCompatActivity {
    private static final String TAG = "EditRecActivity";

    private Button btnSave,btnDelete;
    private EditText itemName, itemImage, itemFood, itemLikes, itemIngredients;


    RecDatabaseHelper mDatabaseHelper;

    private String selectedName, imURL, foURL, likes, ingredientList;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rec);

        btnSave = (Button) findViewById(R.id.ing_btnSave);
        btnDelete = (Button) findViewById(R.id.ing_btnDelete);
        itemName = (EditText) findViewById(R.id.editRecName);
        itemImage = (EditText) findViewById(R.id.editRecImageURL);
        itemFood = (EditText) findViewById(R.id.editRecFoodURL);
        itemLikes = (EditText) findViewById(R.id.editRecLikeCount);
        itemIngredients = (EditText) findViewById(R.id.editRecIngrdients);

        mDatabaseHelper = new RecDatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();
        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value
        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        imURL = receivedIntent.getStringExtra("imageURL");
        foURL = receivedIntent.getStringExtra("foodURL");
        likes = receivedIntent.getStringExtra("likeCount");
        ingredientList = receivedIntent.getStringExtra("ingredients");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodName = itemName.getText().toString();
                String foodImage = itemImage.getText().toString();
                String foodURL = itemFood.getText().toString();
                String foodLikes = itemLikes.getText().toString();
                String foodIngredients = itemIngredients.getText().toString();

                if((!foodName.equals("")) && (!foodImage.equals("")) && (!foodURL.equals("")) && (!foodLikes.equals("")) && (!foodIngredients.equals(""))){
                    mDatabaseHelper.updateName(foodName,selectedID,selectedName,foodImage,foodURL,foodLikes,foodIngredients);
                }else{
                    toastMessage("You must not leave anything blank");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                itemName.setText("");
                toastMessage("removed from database");
            }
        });
    }



    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(EditRecActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
