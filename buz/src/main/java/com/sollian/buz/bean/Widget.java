package com.sollian.buz.bean;

/**
 * 十大@author sollian on 2017/9/20.
 */

public class Widget {
    private String    name;
    private String    title;
    /**
     * 附加
     */
    private Article[] article;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Article[] getArticle() {
        return article;
    }

    public void setArticle(Article[] article) {
        this.article = article;
    }
}
