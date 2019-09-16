package com.example.android.fridger;

public class Ingredients {
    private String ingredientsName;
    private Boolean check = false;
    private int pos;

    public Ingredients(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public Boolean getCheck() {
        return check;
    }

    public int getPos() {
        return pos;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
