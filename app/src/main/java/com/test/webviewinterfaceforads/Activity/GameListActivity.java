package com.test.webviewinterfaceforads.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.test.webviewinterfaceforads.Adapter.GameListAdapter;
import com.test.webviewinterfaceforads.Model.GameItemModel;
import com.test.webviewinterfaceforads.R;

import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity {
    ListView listGames;
    GameListAdapter adapterGameList;
    EditText etxtGameUrl;
    Button btnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerView = inflater.inflate(R.layout.header_of_game_list, null);
        etxtGameUrl = headerView.findViewById(R.id.etxtGameUrl);
        btnPlay = headerView.findViewById(R.id.btnPlay);

        listGames = findViewById(R.id.listGames);
        listGames.addHeaderView(headerView);

        listGames.setOnItemClickListener(listGames_itemClick());
        btnPlay.setOnClickListener(btnPlay_Click());
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

    View.OnClickListener btnPlay_Click(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etxtGameUrl.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Url Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("gameUrl", etxtGameUrl.getText().toString());
                startActivity(intent);
            }
        };
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
