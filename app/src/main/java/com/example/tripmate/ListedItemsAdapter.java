package com.example.tripmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListedItemsAdapter extends RecyclerView.Adapter<ListedItemsAdapter.ViewHolder> {

    Context context;

    public ListedItemsAdapter(Context context, ArrayList<Tour_item> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    ArrayList<Tour_item> arrayList;

    @NonNull
    @Override
    public ListedItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListedItemsAdapter.ViewHolder holder, int position) {
        holder.button2.setText("Book Now");
        holder.imageView2.setImageResource(arrayList.get(position).getImage());
        holder.textView3.setText(arrayList.get(position).getTitle());
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_Tour.class);
//                intent.putExtra("Position", position);
//                intent.putExtra("ArrayList", arrayList);
                Toast.makeText(context, "Opened Suucess", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView2;
        TextView textView3;
        Button button2;
        public ViewHolder( View itemView) {
            super(itemView);
            imageView2 = itemView.findViewById(R.id.imageView2);
            textView3 = itemView.findViewById(R.id.textView3);
            button2 = itemView.findViewById(R.id.button2);
        }
    }
}
