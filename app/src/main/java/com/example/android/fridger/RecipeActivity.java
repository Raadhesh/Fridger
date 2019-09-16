package com.example.android.fridger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class RecipeActivity extends AppCompatActivity {

    private static final String TAG = "RecipeActivity";

    //vars
    private ArrayList<Recipes> mDispRecipes = new ArrayList<>();
    private ArrayList<Recipes> mSortRecipes = new ArrayList<>();
    private ArrayList<Recipes> mRecipes = new ArrayList<>();
    boolean displayDecider = false;  //True if TrueList is null meaning display all Recipes without sorting.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Log.d(TAG, "onCreate: started.");

        initializeRecipes();
        mSortRecipes = mRecipes;


        Intent intentRecipe = getIntent();
        int[] trueList = intentRecipe.getIntArrayExtra("ingredientList");
        if(trueList == null){
            displayDecider = true;
        }
        else {
            displayDecider = false;
            //set trueList hit for each recipe
            for(int i = 0; i<mSortRecipes.size();i++){
                int[] temp = mSortRecipes.get(i).getIngredients();
                int counter = 0;

                for(int m = 0; m<temp.length;m++){
                    for(int n = 0; n < trueList.length; n++){
                        if(temp[m] == trueList[n]){
                            ++counter;
                        }
                        else{}
                    }
                }
                mSortRecipes.get(i).setMatch(counter);
            }

//            //sorting the recipes in descending order of matches(hits)
//            for(int i = 0; i < mSortRecipes.size(); i++){
//                for(int j = i; j < mSortRecipes.size(); j++ ){
//                    if(mSortRecipes.get(i).getMatch() > mSortRecipes.get(i).getMatch()){
//                        Recipes temp;
//                        temp = mSortRecipes.get(i);
//                        mSortRecipes.set(j, mSortRecipes.get(i));
//                        mSortRecipes.set(i, temp);
//                    }
//                }
//            }

            Collections.sort(mSortRecipes);

            //display only those Recipes containing those ingredients
            int tempCount = 0;

            for(int i = 0; i < mSortRecipes.size(); i++){
                if(mSortRecipes.get(i).getMatch() <= trueList.length){
                    if(mSortRecipes.get(i).getMatch() > 0){
                        mDispRecipes.add(mSortRecipes.get(i));
                    }
                    else {

                    }
                }
                else {

                }
            }
        }



        if(displayDecider){
            initRecyclerView(mRecipes);
        }
        else {
            initRecyclerView(mDispRecipes);
        }


    }

    private void initializeRecipes(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

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

    private void initRecyclerView(ArrayList<Recipes> recipeVal){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view_recipe);
        RecyclerRecViewAdapter adapter = new RecyclerRecViewAdapter(this, recipeVal);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
