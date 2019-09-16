package com.example.android.fridger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button logInButton, ingredientButton, recipeButton, createIngButton, createRecButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInButton = (Button) findViewById(R.id.logButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logInIntent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(logInIntent);
            }
        });

        ingredientButton = (Button) findViewById(R.id.ingButton);
        ingredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingredientIntent = new Intent(MainActivity.this, IngredientsActivity.class);
                startActivity(ingredientIntent);
            }
        });

        recipeButton = (Button) findViewById(R.id.recButton);
        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipeIntent = new Intent(MainActivity.this, RecipeActivity.class);
                startActivity(recipeIntent);
            }
        });

        createIngButton = (Button) findViewById(R.id.createIngButton);
        createIngButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIngredientIntent = new Intent(MainActivity.this, CreateIngredients.class);
                startActivity(createIngredientIntent);
            }
        });

        createRecButton=(Button) findViewById(R.id.createRecButton);
        createRecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createRecipeIntent = new Intent(MainActivity.this, CreateRecipes.class);
                startActivity(createRecipeIntent);
            }
        });

    }
}
