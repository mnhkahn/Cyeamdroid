package com.cyeam.cyeamdroid.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bryce on 14-6-25.
 */
public class Blog implements Serializable {

    public static final String BLOG_SER = "com.cyeam.cyeamdroid.blog.ser";
    public static final String Item = "item";
    public static final String Title = "title";
    public static final String Figure = "figure";
    public static final String Info = "info";
    public static final String Description = "description";
    public static final String Link = "link";
    public static final String PubDate = "pubDate";

    private String title;

    private String figure;

    private String info;

    private String description;

    private String link;

    private Date pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getInfo() { return info; }

    public void setInfo(String info) { this.info = info; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
}
