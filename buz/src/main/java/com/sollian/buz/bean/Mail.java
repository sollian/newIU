package com.sollian.buz.bean;

/**
 * @author sollian on 2017/9/5.
 */

public class Mail {
    // 信件编号，此编号为/mail/:box/:num中的num
    private int        index;
    // 是否已读
    private boolean    is_read;
    // 是否回复
    private boolean    is_reply;
    // 是否有附件
    private boolean    has_attachment;
    // 信件标题
    private String     title;
    // 发信人，此为user元数据，如果user不存在则为用户id
    private User       user;
    // 发信时间
    private long       post_time;
    // 所属信箱名
    private String     box_name;
    // 信件内容，只存在于/mail/:box/:num中
    private String     content;
    // 信件的附件列表,只存在于/mail/:box/:num中
    private Attachment attachment;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean is_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public boolean is_reply() {
        return is_reply;
    }

    public void setIs_reply(boolean is_reply) {
        this.is_reply = is_reply;
    }

    public boolean isHas_attachment() {
        return has_attachment;
    }

    public void setHas_attachment(boolean has_attachment) {
        this.has_attachment = has_attachment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getPost_time() {
        return post_time;
    }

    public void setPost_time(long post_time) {
        this.post_time = post_time;
    }

    public String getBox_name() {
        return box_name;
    }

    public void setBox_name(String box_name) {
        this.box_name = box_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
