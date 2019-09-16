package com.example.android.fridger;

import java.util.Comparator;

public class Recipes implements Comparable{
    String imageUrl;
    String name;
    int[] ingredients = new int[20];
    String foodURL;
    int likeCount;
    int match;
    boolean likeCheck = false;

    public boolean isLikeCheck() {
        return likeCheck;
    }

    public void setLikeCheck(boolean likeCheck) {
        this.likeCheck = likeCheck;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public Recipes(String imageUrl, String name, int[] ingredients, String foodURL, int likeCount) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.ingredients = ingredients;
        this.foodURL = foodURL;
        this.likeCount = likeCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public int[] getIngredients() {
        return ingredients;
    }

    public String getFoodURL() {
        return foodURL;
    }

    //Comparing the match
    @Override
    public int compareTo(Object o) {
        int comparematch=((Recipes)o).getMatch();
        /* For Ascending order*/
//        return this.studentage-compareage;

        /* For Descending order do like this */
        return comparematch-this.match;
    }
}


