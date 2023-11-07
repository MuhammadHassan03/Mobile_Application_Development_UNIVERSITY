package com.example.tripmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TourDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TOUR.db";
    private static final String TABLE_NAME = "TOUR";
    private static final String COL_01 = "ID";
    private static final String COL_02 = "Title";
    private static final String COL_03 = "Description";
    private static final String COL_04 = "Image";
    private Context context;

    public TourDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_01 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_02 + " TEXT, " +
                COL_03 + " TEXT, " +
                COL_04 + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Tour_item> getAllTasks(){
        ArrayList<Tour_item> tourItems = new ArrayList<>();
        String Query = "Select * From TOUR";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        while (cursor.moveToNext()){
            String Title = cursor.getString(cursor.getColumnIndex("Title"));
            String Description = cursor.getString(cursor.getColumnIndex("Description"));
            int Image = cursor.getInt(cursor.getColumnIndex("Image"));
            Tour_item tour_item = new Tour_item(Image, Title, Description);
            tourItems.add(tour_item);
        }

        cursor.close();
        return tourItems;
    }

    public void addNewTourItem(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        ArrayList<Tour_item> tour_itemArrayList = new ArrayList<>();
        tour_itemArrayList.add(new Tour_item(R.drawable.tour, "Hello", "Hello")); // Add your items here
        tour_itemArrayList.add(new Tour_item(R.drawable.tour, "Hello", "Hello"));
        tour_itemArrayList.add(new Tour_item(R.drawable.tour, "Hello", "Hello"));
        tour_itemArrayList.add(new Tour_item(R.drawable.tour, "Hello", "Hello"));

        for (Tour_item tour_item : tour_itemArrayList) {
            contentValues.put(COL_02, tour_item.getTitle());
            contentValues.put(COL_03, tour_item.getDescription());
            contentValues.put(COL_04, tour_item.getImage());

            long newRowId = db.insert(TABLE_NAME, null, contentValues);
            if (newRowId != -1) {
                Toast.makeText(context, "Insert Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Insert Dismess", Toast.LENGTH_SHORT).show();
            }

            // Clear the content values for the next iteration
            contentValues.clear();
        }

        db.close();

    }
}
