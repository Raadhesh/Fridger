package com.example.android.fridger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        Intent intent = getIntent();
        final int[] trueList = intent.getIntArrayExtra("ingredientNumbers");
        String outputMessage = intent.getStringExtra("textMessage");

        //String Message display code
        TextView outputString = (TextView) findViewById(R.id.selectTextViewOutput);
        outputString.setText("Working String Message");
        outputString.setText(outputMessage);

        //Ingredient Numbers Display Code
        String outputTextViewNumbers = "";
        TextView outputNumbers = (TextView) findViewById(R.id.selectIntTextViewOutput);
        for(int i = 0; i < trueList.length ; i++){
            if(trueList[i]>0){
                outputTextViewNumbers = outputTextViewNumbers.concat(trueList[i]+ ", ");
            }
        }
        outputNumbers.setText("Working Number Message");
        outputNumbers.setText(outputTextViewNumbers);

        Button button = (Button) findViewById(R.id.selectEndButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectRecipe = new Intent(SelectedActivity.this, RecipeActivity.class);
                selectRecipe.putExtra("ingredientList", trueList);
                startActivity(selectRecipe);
            }
        });
    }
}
