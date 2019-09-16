package com.example.android.fridger;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerRecViewAdapter extends RecyclerView.Adapter<RecyclerRecViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerRecViewAdapter";

    private ArrayList<Recipes>mRecipes = new ArrayList<>();
    private Context mContext;

    public RecyclerRecViewAdapter(Context context, ArrayList<Recipes> recipes) {
        mRecipes = recipes;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mRecipes.get(i).getImageUrl())
                .into(viewHolder.image);

        viewHolder.imageName.setText(mRecipes.get(i).getName());

        viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mRecipes.get(i).isLikeCheck()) {
                    Toast.makeText(mContext, mRecipes.get(i).getName() + "Like Button is Enabled", Toast.LENGTH_SHORT).show();
                    mRecipes.get(i).setLikeCheck(true);
                    int tempLikeCount = mRecipes.get(i).getLikeCount() + 1;
                    mRecipes.get(i).setLikeCount(tempLikeCount);
                    viewHolder.likeButton.setImageResource(R.drawable.thumb_on);
                }
                else{
                    Toast.makeText(mContext, mRecipes.get(i).getName() + "Like Button is Disabled", Toast.LENGTH_SHORT).show();
                    mRecipes.get(i).setLikeCheck(false);
                    int tempLikeCount = mRecipes.get(i).getLikeCount() + 1;
                    mRecipes.get(i).setLikeCount(tempLikeCount);
                    viewHolder.likeButton.setImageResource(R.drawable.thumb_off);
                }

            }
        });

        String numberLikes = String.valueOf(mRecipes.get(i).getLikeCount());
        viewHolder.likeCount.setText(numberLikes);

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on:" + mRecipes.get(i).getName());

                Toast.makeText(mContext, mRecipes.get(i).getName(), Toast.LENGTH_SHORT).show();

                Intent intentWeb = new Intent(mContext, WebActivity.class);
                intentWeb.putExtra("foodURL", mRecipes.get(i).getFoodURL());
                intentWeb.putExtra("recName", mRecipes.get(i).getName());
                mContext.startActivity(intentWeb);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imageName;
        ImageView likeButton;
        TextView likeCount;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recipeImage);
            imageName = itemView.findViewById(R.id.recipeName);
            likeButton = itemView.findViewById(R.id.like_button);
            likeCount = itemView.findViewById(R.id.likeCount);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
