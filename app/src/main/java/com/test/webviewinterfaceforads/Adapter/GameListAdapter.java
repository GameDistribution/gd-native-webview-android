package com.test.webviewinterfaceforads.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.webviewinterfaceforads.Activity.GameListActivity;
import com.test.webviewinterfaceforads.Model.GameItemModel;
import com.test.webviewinterfaceforads.R;

import java.util.ArrayList;

public class GameListAdapter extends BaseAdapter {
    private GameListActivity activity;
    public ArrayList<GameItemModel> allItems;
    public GameListAdapter(GameListActivity activity, ArrayList<GameItemModel> allItems) {
        this.activity = activity;
        this.allItems = allItems;
    }

    @Override
    public int getCount() {
        return allItems.size();
    }

    @Override
    public GameItemModel getItem(int i) {
        return allItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GameItemViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_of_game_list, null);
            holder = new GameItemViewHolder();
            holder.imgGame = convertView.findViewById(R.id.imgGame);
            holder.txtGameName = convertView.findViewById(R.id.txtGameName);

            convertView.setTag(holder);
        } else {
            holder = (GameItemViewHolder) convertView.getTag();
        }

        GameItemModel item = getItem(position);
        holder.imgGame.setImageResource(item.getImageId());
        holder.txtGameName.setText(item.getTitle());
        return convertView;
    }

    public class GameItemViewHolder{
        public ImageView imgGame;
        public TextView txtGameName;
    }
}
