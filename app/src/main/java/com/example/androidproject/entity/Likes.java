package com.example.androidproject.entity;

public class Likes {

    int _postId;

    public Likes(int _postId) {
        this._postId = _postId;
    }

    public int get_postId() {
        return _postId;
    }

    public void set_postId(int _postId) {
        this._postId = _postId;
    }
}
