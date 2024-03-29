package com.example.android.fridger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RecDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "RecDatabaseHelper";
    private static final String TABLE_NAME = "ingredient_table";
    private static final String COL0 = "ID";
    private static final String COL1 = "name";
    private static final String COL2 = "imageURL";
    private static final String COL3 = "foodURL";
    private static final String COL4 = "likeCount";
    private static final String COL5 = "ingredientList";
    public RecDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 +" TEXT, " + COL2 +" TEXT, " + COL3 +" TEXT, " + COL4 +" TEXT, " + COL5 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData (String itemName, String imageURL, String foodURL, String likeCount, String ingredientList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, itemName);
        contentValues.put(COL2, imageURL);
        contentValues.put(COL3, foodURL);
        contentValues.put(COL4, likeCount);
        contentValues.put(COL5, ingredientList);

        Log.d(TAG, "addData: Adding " + itemName + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName, String imageURL, String foodURL, String likeCount, String ingredientList){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET "
                + COL1 + " = '" + newName +", "
                + COL2 + " = " + imageURL + ", "
                + COL3 + " = '" + foodURL +", "
                + COL4 + " = '" + likeCount +", "
                + COL5 + " = '" + ingredientList +", "
                +"' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }


    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
}
