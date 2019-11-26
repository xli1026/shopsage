package com.example.shopsage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
    This is the adapter for the list. It controls the view and

 */

public class ListAdapter extends
        RecyclerView.Adapter<ListAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button infoButton;
        public Button deleteButton;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.list_name);
            infoButton = (Button) itemView.findViewById(R.id.info_button);
            deleteButton = (Button) itemView.findViewById(R.id.delete_button);
            infoButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("MyList", lists.get(getAdapterPosition()));
                    intent.putExtra("position", getAdapterPosition());
                    ((Activity)context).startActivityForResult(intent, 3);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(getAdapterPosition());
                }
            });
        }
    }

    private ArrayList<List> lists;
    private Context context;

    public ListAdapter(ArrayList<List> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder viewHolder, int position) {

        // Get the data model based on position
        List list = lists.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(list.getName());
        Button infoButton = viewHolder.infoButton;
        Button deleteButton = viewHolder.deleteButton;
        infoButton.setText("Info");
        deleteButton.setText("Delete");
    }

    public int getItemCount() {
        return lists.size();
    }

    public void delete(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
    }

    public void add(String name) {
        lists.add(getItemCount(), new List(name));
        notifyItemInserted(getItemCount());
    }

    public void add(List list, int position) {
        lists.remove(position);
        lists.add(position, list);
        notifyItemChanged(position);
    }

}
