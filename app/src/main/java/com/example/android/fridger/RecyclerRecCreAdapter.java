package com.example.android.fridger;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerRecCreAdapter extends RecyclerView.Adapter<RecyclerRecCreAdapter.ViewHolder>{
    private static final String TAG = "RecyclerRecCreAdapter";

    RecDatabaseHelper mDatabaseHelper;
    private ArrayList<Recipes> mRecipes = new ArrayList<>();
    private Context mContext;

    public RecyclerRecCreAdapter(Context context, ArrayList<Recipes> recipes, RecDatabaseHelper databaseHelper) {
        mRecipes = recipes;
        mContext = context;
        mDatabaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.create_recipe_listitem, viewGroup, false);
        RecyclerRecCreAdapter.ViewHolder holder = new RecyclerRecCreAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mRecipes.get(i).getImageUrl())
                .into(viewHolder.image);

        viewHolder.imageName.setText(mRecipes.get(i).getName());
        viewHolder.foodURL.setText(mRecipes.get(i).getFoodURL());
        viewHolder.likeCount.setText(mRecipes.get(i).getLikeCount());
        int[] tempest = mRecipes.get(i).getIngredients();
        final String temper = "";
        for(int l = 0; l < tempest.length; l++){
            if(l == tempest.length-1){

            }
            else {
                temper.concat(Integer.toString(tempest[l]) + ", ");
            }

        }
        viewHolder.ingredients.setText(temper);


        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recName = mRecipes.get(i).getName();
                final String ingredientString = temper;
                Log.d(TAG, "onClick: recipeClickedOn" + recName);
                Toast.makeText(mContext, recName, Toast.LENGTH_SHORT).show();

                //get the id associated with the name.
                Cursor data = mDatabaseHelper.getItemID(recName);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(mContext, EditRecActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",recName);
                    editScreenIntent.putExtra("imageURL", mRecipes.get(i).getImageUrl());
                    editScreenIntent.putExtra("foodURL", mRecipes.get(i).getFoodURL());
                    editScreenIntent.putExtra("likeCount", Integer.toString(mRecipes.get(i).getLikeCount()));
                    editScreenIntent.putExtra("ingredients", ingredientString);
                    mContext.startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return  mRecipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageName;
        TextView foodURL;
        TextView likeCount;
        TextView ingredients;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recipeCreateImage);
            imageName = itemView.findViewById(R.id.recipeCreateName);
            foodURL = itemView.findViewById(R.id.recipeCreateURL);
            likeCount = itemView.findViewById(R.id.recipeCreateLikeCount);
            ingredients = itemView.findViewById(R.id.recipeCreateIngredients);
            parentLayout = itemView.findViewById(R.id.create_rec_parent_layout);
        }
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
