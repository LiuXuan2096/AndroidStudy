package com.liuxuan2096.databindingdemo.model;

/**
 * 共用的Model
 */
public class Book {
    public String title;
    public String author;
    public int rating;
    public String image;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
