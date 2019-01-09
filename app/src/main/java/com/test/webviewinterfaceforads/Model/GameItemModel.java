package com.test.webviewinterfaceforads.Model;

public class GameItemModel {
    private String title;
    private String gameUrl;
    private int imageId;

    public GameItemModel(String title, String gameUrl, int imageId){
        this.title = title;
        this.gameUrl = gameUrl;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public int getImageId() {
        return imageId;
    }
}
