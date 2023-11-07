package com.example.tripmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        TourDatabase tourDatabase = new TourDatabase(this);
        tourDatabase.addNewTourItem();

        ArrayList<String> categoryArrayList = new ArrayList<>();
        categoryArrayList.add("In City");
        categoryArrayList.add("Out of City");
        categoryArrayList.add("3 Days Tour");
        categoryArrayList.add("7 Days Tour");
        categoryArrayList.add("14 Days Tour");
        categoryArrayList.add("21 Days Tour");
        categoryArrayList.add("Month Tour");

        Toast.makeText(this, "Data Added to Database", Toast.LENGTH_SHORT).show();

        RecyclerView categories_Recycler_View = findViewById(R.id.categories_recycler_view);
        categories_Recycler_View.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categories_Recycler_View.setAdapter(new CategoryAdapter(this, categoryArrayList));

        RecyclerView listed_items_recycler_view = findViewById(R.id.listed_items_recycler_view);
        listed_items_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Tour_item> tour_itemArrayList = new ArrayList<>();
        tour_itemArrayList = tourDatabase.getAllTasks();
        listed_items_recycler_view.setAdapter(new ListedItemsAdapter(this, tour_itemArrayList));
    }
}