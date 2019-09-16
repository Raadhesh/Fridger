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

import java.util.ArrayList;

public class RecyclerIngCreAdapter extends RecyclerView.Adapter<RecyclerIngCreAdapter.ViewHolder> {
    private static final String TAG = "RecyclerIngCreAdapter";

    IngDatabaseHelper mDatabaseHelper;

    private ArrayList<Ingredients> mIngredients = new ArrayList<>();
    private Context mContext;

    public RecyclerIngCreAdapter(Context context, ArrayList<Ingredients> ingredients, IngDatabaseHelper databaseHelper) {
        mIngredients = ingredients;
        mContext = context;
        mDatabaseHelper = databaseHelper;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.create_ingredient_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");
//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImages.get(i))
//                .into(viewHolder.image);
//
//        viewHolder.imageName.setText(mImageNames.get(i));

        viewHolder.ingText.setText(mIngredients.get(i).getIngredientsName());

        //set position to be such that it starts from 1.
        mIngredients.get(i).setPos(i+1);
        viewHolder.ingStatus.setText("Position: " + Integer.toString(mIngredients.get(i).getPos()));
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingName = mIngredients.get(i).getIngredientsName();
                Log.d(TAG, "onClick: clicked on" + ingName);
                Toast.makeText(mContext, ingName, Toast.LENGTH_SHORT).show();

                //get the id associated with the name.
                Cursor data = mDatabaseHelper.getItemID(ingName);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(mContext, EditIngActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",ingName);
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
        return mIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //        CircleImageView image;
//        TextView imageName;
        TextView ingText;
        RelativeLayout parentLayout;
        TextView ingStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.image);
//            imageName = itemView.findViewById(R.id.image_name);
            ingText = itemView.findViewById(R.id.ingCreateText);
            parentLayout = itemView.findViewById(R.id.ingredient_parent_layout);
            ingStatus = itemView.findViewById(R.id.posIngTextView);
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
