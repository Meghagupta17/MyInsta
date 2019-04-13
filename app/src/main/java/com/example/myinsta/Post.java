package com.example.myinsta;

public class Post {
    public String hashtag;
    public String userNickName;
    public String postText;
    public String image;


    public Post(String hashtag, String userNickName, String postText, String image) {
        this.hashtag = hashtag;
        this.userNickName = userNickName;
        this.postText = postText;
        this.image = image;
    }

    public Post() {

    }
}
