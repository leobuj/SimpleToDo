package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnLongClickListener {
        void onItemLongClicked(int position);

    }

    List<String> items;

    OnLongClickListener longClickListener;
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {

        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inide View Holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        //Grab Item at the position
        String item = items.get(position);
        //Bind the item into the specified viewholder
        holder.bind(item);
    }

    // Tells the RV how many items are on the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represent each row of list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update the view inside inside of the viewholder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener which item was longpressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
