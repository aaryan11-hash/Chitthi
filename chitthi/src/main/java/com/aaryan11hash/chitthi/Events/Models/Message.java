package com.aaryan11hash.chitthi.Events.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Message {

    private String data;
    private String author;

    public Message(String data, String author) {
        this.data = data;
        this.author = author;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Message{" +
                "data='" + data + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
