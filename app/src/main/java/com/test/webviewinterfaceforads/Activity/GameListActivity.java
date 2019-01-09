package com.test.webviewinterfaceforads.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.webviewinterfaceforads.Adapter.GameListAdapter;
import com.test.webviewinterfaceforads.Model.GameItemModel;
import com.test.webviewinterfaceforads.R;

import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity {
    ListView listGames;
    GameListAdapter adapterGameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        listGames = findViewById(R.id.listGames);
        listGames.setOnItemClickListener(listGames_itemClick());
        updateUI();
    }
    void updateUI(){
        //TODO remove dummy items
        ArrayList<GameItemModel> allItems = new ArrayList<>();
        allItems.add(
                new GameItemModel(
                        "Ninja Ranmaru",
                        "file:///android_asset/ninja-ranmaru/index.html",
                        R.drawable.ninja_ranmaru));
        allItems.add(
                new GameItemModel(
                        "Ninja Ranmaru",
                        "file:///android_asset/ninja-ranmaru/index.html",
                        R.drawable.ninja_ranmaru));
        allItems.add(
                new GameItemModel(
                        "Ninja Ranmaru",
                        "file:///android_asset/ninja-ranmaru/index.html",
                        R.drawable.ninja_ranmaru));
        allItems.add(
                new GameItemModel(
                        "Ninja Ranmaru",
                        "file:///android_asset/ninja-ranmaru/index.html",
                        R.drawable.ninja_ranmaru));
        allItems.add(
                new GameItemModel(
                        "Ninja Ranmaru",
                        "file:///android_asset/ninja-ranmaru/index.html",
                        R.drawable.ninja_ranmaru));
        allItems.add(
                new GameItemModel(
                        "Ninja Ranmaru",
                        "file:///android_asset/ninja-ranmaru/index.html",
                        R.drawable.ninja_ranmaru));
        allItems.add(
                new GameItemModel(
                        "Ninja Ranmaru",
                        "file:///android_asset/ninja-ranmaru/index.html",
                        R.drawable.ninja_ranmaru));
        allItems.add(
                new GameItemModel(
                        "Ninja Ranmaru",
                        "file:///android_asset/ninja-ranmaru/index.html",
                        R.drawable.ninja_ranmaru));
        adapterGameList = new GameListAdapter(this, allItems);
        listGames.setAdapter(adapterGameList);
    }


    AdapterView.OnItemClickListener listGames_itemClick(){
        return  new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameItemModel item = adapterGameList.getItem(position);
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("gameUrl", item.getGameUrl());
                startActivity(intent);
            }
        };
    }
}
