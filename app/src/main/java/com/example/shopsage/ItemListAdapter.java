package com.example.shopsage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemListAdapter
        extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button infoButton;
        public Button deleteButton;
        public CheckBox checkButton;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.list_name);
            infoButton = (Button) itemView.findViewById(R.id.info_button);
            deleteButton = (Button) itemView.findViewById(R.id.delete_button);
            checkButton = (CheckBox) itemView.findViewById(R.id.check_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(getAdapterPosition());
                }
            });
            infoButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context.getApplicationContext(), ItemInfoActivity.class);
                   intent.putExtra("MyItem", list.items.get(getAdapterPosition()));
                   intent.putExtra("position", getAdapterPosition());
                   ((Activity)context).startActivityForResult(intent, 4);
               }
            });

        }
    }
        private List list;
        private Context context;

        public ItemListAdapter(List list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public ItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.item_list, parent, false);

            // Return a new holder instance
            ItemListAdapter.ViewHolder viewHolder = new ItemListAdapter.ViewHolder(contactView);
            return viewHolder;
        }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ItemListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final Item item = list.items.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(item.getName());
        Button infoButton = viewHolder.infoButton;
        Button deleteButton = viewHolder.deleteButton;
        final CheckBox checkButton = viewHolder.checkButton;
        infoButton.setText("Info");
        deleteButton.setText("Delete");
        checkButton.setChecked(item.isPurchased());
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.changePurchaseStatus();
                checkButton.setChecked(item.isPurchased());
            }
        });
    }

    public int getItemCount() {
        return list.items.size();
    }

    public void delete(int position) {
        list.items.remove(position);
        notifyItemRemoved(position);
    }

    public void add(String name, String price, String location, String category) {
        list.items.add(getItemCount(), new Item(name, price, location, category));
        notifyItemInserted(getItemCount());
    }

    public void update(String name, String price, String location, String category, int position) {
        list.items.remove(position);
        list.items.add(position, new Item(name, price, location, category));
        notifyItemChanged(position);
    }
    public List getList() {
            return this.list;
    }
}
