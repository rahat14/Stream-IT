package com.metacodersbd.watch.models;

public class modelForMovie {

    // model class needs to  hav getter / setter and constructur
    String name , id , category , decription , link  , query , image  ;

    public modelForMovie()
    {

    }

    public modelForMovie(String name, String id, String category, String decription, String link, String query, String image) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.decription = decription;
        this.link = link;
        this.query = query;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
