package com.example.android.fridger;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerIngViewAdapter extends RecyclerView.Adapter<RecyclerIngViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerIngViewAdapter";


    //    private ArrayList<String> mIngredientNames = new ArrayList<>();
//    private ArrayList<Boolean> mIngredientCheck = new ArrayList<>();
    private ArrayList<Ingredients> mIngredients = new ArrayList<>();
    private Context mContext;

    public RecyclerIngViewAdapter(Context context, ArrayList<Ingredients> ingredients) {
        mIngredients = ingredients;
        mContext = context;
    }


    //It is recycling the view into the positions it should be put in
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredient_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");
//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImages.get(i))
//                .into(viewHolder.image);
//
//        viewHolder.imageName.setText(mImageNames.get(i));

        viewHolder.checkBox.setText(mIngredients.get(i).getIngredientsName());
        viewHolder.checkBox.setOnCheckedChangeListener(null);
        viewHolder.checkBox.setChecked(mIngredients.get(i).getCheck());

        //set position to be such that it starts from 1.


        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on" + mIngredients.get(i).getIngredientsName());
                Toast.makeText(mContext, mIngredients.get(i).getIngredientsName(), Toast.LENGTH_SHORT).show();

                //instructions dependent on whether checkbox is checked or not.
                if(mIngredients.get(i).getCheck()){
                    mIngredients.get(i).setCheck(false);
                    viewHolder.checkBox.setChecked(false);
                    mIngredients.get(i).setPos(0);
                }
                else{
                    mIngredients.get(i).setCheck(true);
                    viewHolder.checkBox.setChecked(true);
                    mIngredients.get(i).setPos(i+1);
                }
            }
        });

        //code to show the position and status of checkboxes
        String message = "";
        if(mIngredients.get(i).getCheck()){
            message = "TRUE";
        }
        else{
            message = "FALSE";
        }

//        viewHolder.ingStatus.setText(mIngredients.get(i).getPos() + ")  " + message + ", Size : " + getItemCount());
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

//        CircleImageView image;
//        TextView imageName;
        CheckBox checkBox;
        RelativeLayout parentLayout;
//        TextView ingStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.image);
//            imageName = itemView.findViewById(R.id.image_name);
            checkBox = itemView.findViewById(R.id.cbox);
            parentLayout = itemView.findViewById(R.id.ingredient_parent_layout);
//            ingStatus = itemView.findViewById(R.id.posTextView);
        }
    }
}
