package com.example.android.fridger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateRecipes extends AppCompatActivity {
    private static final String TAG = "CreateRecipes";
    RecDatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText name, imageURL, foodURL, likeCount, ingredients;
    private ArrayList<Recipes> mRecipes = new ArrayList<Recipes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipes);

        name = (EditText) findViewById(R.id.editTextRecName);
        imageURL = (EditText)findViewById(R.id.editTextRecImageURL);
        foodURL = (EditText)findViewById(R.id.editTextRecFoodURL);
        likeCount = (EditText)findViewById(R.id.editTextRecLikeCount);
        ingredients = (EditText)findViewById(R.id.editTextRecIngrdients);

        btnAdd = (Button) findViewById(R.id.btnAddRec);
        btnViewData = (Button) findViewById(R.id.btnViewRec);
        mDatabaseHelper = new RecDatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String picURL = imageURL.getText().toString();
                String fooURL = foodURL.getText().toString();
                String likes = likeCount.getText().toString();
                String ingList = ingredients.getText().toString();
                if ((newName.length() != 0) && (picURL.length() != 0) && (fooURL.length() != 0) && (likes.length() != 0) && (ingList.length()!=0)) {
                    AddData(newName, picURL, fooURL, likes, ingList);
                    name.setText("");
                    imageURL.setText("");
                    foodURL.setText("");
                    likeCount.setText("");
                    ingredients.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateRecipes.this, RecListActivity.class);
                startActivity(intent);
            }
        });

    }



    public void AddData(String newName, String imageURL, String foodURL, String likeCount, String ingredientList) {
        boolean insertData = mDatabaseHelper.addData(newName, imageURL, foodURL, likeCount, ingredientList);

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


    private void initializeRecipes(){
        Log.d(TAG, "initializeRecipes: ");
        mRecipes.add(new Recipes(
                "https://azcdn.rewardme.in/legacyghhblob/Production/IN/Assets/Modules/Editorial/Recipe/Images/authentic-eid-al-fitr-chicken-biryani-2-size-3.jpg",
                "Chicken Briyani",
                new int[]{22, 10, 5, 12, 6, 28},
                "https://www.rewardme.in/home/cooking/recipe/authentic-eid-al-fitr-chicken-biryani",
                60
        ));

        mRecipes.add(new Recipes(
                "https://www.seriouseats.com/assets_c/2015/07/20150702-sous-vide-hamburger-anova-16-thumb-1500xauto-424812.jpg",
                "Chicken Burger",
                new int[]{29, 22, 27, 8},
                "https://www.allrecipes.com/recipe/218509/fantastic-chicken-burgers/?internalSource=staff%20pick&referringId=16362&referringContentType=Recipe%20Hub",
                33
        ));

        mRecipes.add(new Recipes(
                "https://www.seriouseats.com/images/2016/01/20160206-fried-rice-food-lab-68.jpg",
                "Fried Rice",
                new int[]{5,6,7,10,15},
                "https://www.allrecipes.com/recipe/79543/fried-rice-restaurant-style/",
                25
        ));

        mRecipes.add(new Recipes(
                "https://www.cookwithkushi.com/wp-content/uploads/2016/05/IMG_9740_.jpg",
                "Palak Paneer",
                new int[]{1,2,3},
                "https://www.allrecipes.com/recipe/20312/absolutely-perfect-palak-paneer/",
                11
        ));

        mRecipes.add(new Recipes(
                "http://www.tangylife.com/blog/wp-content/uploads/2015/02/chicken-shawarma-wrap.jpg",
                "Shawarma",
                new int[]{4,5,6},
                "https://www.thespruceeats.com/make-shawarma-at-home-2356016",
                15
        ));

        mRecipes.add(new Recipes(
                "https://www.the-girl-who-ate-everything.com/wp-content/uploads/2016/05/banana-pudding-13-732x1024.jpg",
                "Banana Pudding",
                new int[]{4,5,6,7,8,9,11},
                "https://www.allrecipes.com/recipe/22749/the-best-banana-pudding/?internalSource=hub%20recipe&referringId=363&referringContentType=Recipe%20Hub&clickId=cardslot%2012",
                16
        ));

        mRecipes.add(new Recipes(
                "https://img.taste.com.au/5qlr1PkR/taste/2016/11/spaghetti-bolognese-106560-1.jpeg",
                "Spaghetti bolognese",
                new int[]{13,15,17},
                "https://www.recipetineats.com/spaghetti-bolognese/",
                9
        ));

        mRecipes.add(new Recipes(
                "http://www.blog.sagmart.com/wp-content/uploads/2015/11/Punjabi-Lassi.jpg",
                "Lassi",
                new int[]{1,11,22},
                "https://www.allrecipes.com/recipe/20435/indian-lassi/",
                37
        ));

        mRecipes.add(new Recipes(
                "http://www.maggwire.com/wp-content/uploads/2016/07/kunafa-recipe.jpg",
                "Kanafeh",
                new int[]{2,4,8,16},
                "https://www.allrecipes.com/recipe/215590/kanafa/",
                32
        ));

        mRecipes.add(new Recipes(
                "http://i.huffpost.com/gen/2072900/images/o-PIZZA-HUT-facebook.jpg",
                "Pizza",
                new int[]{12,24,5,17},
                "https://www.allrecipes.com/recipe/82007/chicago-style-pan-pizza/?internalSource=streams&referringId=250&referringContentType=Recipe%20Hub&clickId=st_trending_b",
                55
        ));


//

    }
}
