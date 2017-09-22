package com.sollian.buz.bean;

/**
 * @author sollian on 2017/9/20.
 */

public class Refer {
    //@我的消息
    public static final String AT    = "at";
    //回复我的消息
    public static final String REPLY = "reply";

    // 提醒编号，此编号用于提醒的相关操作
    private int     index;
    // 提醒文章的id
    private int     id;
    // 提醒文章的group id
    private int     group_id;
    // 提醒文章的reply id
    private int     reply_id;
    // 提醒文章所在版面
    private String  board_name;
    // 提醒文章的标题
    private String  title;
    // 发出提醒的时间
    private long    time;
    // 提醒是否已读
    private boolean is_read;
    /**
     * 附加
     */
    // 提醒文章的发信人，此为user元数据，如果user不存在则为用户id
    private User    user;

    private String     description;
    private Pagination pagination;
    private Refer[]    article;

    // 当前类型的提醒是否启用
    private boolean enable;
    // 当前类型的新提醒个数
    private int new_count = -1;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean is_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Refer[] getArticle() {
        return article;
    }

    public void setArticle(Refer[] article) {
        this.article = article;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getNew_count() {
        return new_count;
    }

    public void setNew_count(int new_count) {
        this.new_count = new_count;
    }
}
