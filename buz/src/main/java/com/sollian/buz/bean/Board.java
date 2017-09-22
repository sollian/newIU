package com.sollian.buz.bean;

/**
 * @author sollian on 2017/9/4.
 */

public class Board {
    private int favorite_level = -1;
    // 版面名称
    private String     name;
    // 版面描述——中文名
    private String     description;
    // 版面所属根分区号
    private String     section;
    // 版面是否不可回复
    private boolean    is_no_reply;
    // 版面书否允许附件
    private boolean    allow_attachment;
    // 当前用户是否用发文、回复权限
    private boolean    allow_post;
    // 版面是否允许匿名发文
    private boolean    allow_anonymous;
    /**
     * 附加
     */
    private Pagination pagination;
    private Article[]  article;

    public int getFavorite_level() {
        return favorite_level;
    }

    public void setFavorite_level(int favorite_level) {
        this.favorite_level = favorite_level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public boolean is_no_reply() {
        return is_no_reply;
    }

    public void setIs_no_reply(boolean is_no_reply) {
        this.is_no_reply = is_no_reply;
    }

    public boolean isAllow_attachment() {
        return allow_attachment;
    }

    public void setAllow_attachment(boolean allow_attachment) {
        this.allow_attachment = allow_attachment;
    }

    public boolean isAllow_post() {
        return allow_post;
    }

    public void setAllow_post(boolean allow_post) {
        this.allow_post = allow_post;
    }

    public boolean isAllow_anonymous() {
        return allow_anonymous;
    }

    public void setAllow_anonymous(boolean allow_anonymous) {
        this.allow_anonymous = allow_anonymous;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Article[] getArticle() {
        return article;
    }

    public void setArticle(Article[] article) {
        this.article = article;
    }
}
