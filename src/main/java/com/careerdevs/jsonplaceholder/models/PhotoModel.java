package com.careerdevs.jsonplaceholder.models;

public class PhotoModel {

//            "albumId": 1,
//            "id": 1,
//            "title": "accusamus beatae ad facilis cum similique qui sunt",
//            "url": "https://via.placeholder.com/600/92c952",
//            "thumbnailUrl": "https://via.placeholder.com/150/92c952"

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
