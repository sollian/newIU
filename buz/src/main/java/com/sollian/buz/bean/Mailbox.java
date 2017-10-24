package com.sollian.buz.bean;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author sollian on 2017/9/20.
 */

public class Mailbox {
    //收件箱
    public static final String INBOX   = "inbox";
    //发件箱
    public static final String OUTBOX  = "outbox";
    //回收站
    public static final String DELETED = "deleted";

    @StringDef({INBOX, OUTBOX, DELETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxType {
    }

    // 是否有新邮件
    private boolean new_mail;
    // 信箱是否已满
    private boolean full_mail;
    // 信箱已用空间
    private String  space_used;
    // 当前用户是否能发信
    private boolean can_send;

    /**
     * 附加
     */
    // 信箱类型描述，包括：收件箱，发件箱，废纸篓
    private String     description;
    // 当前信箱的分页信息
    private Pagination pagination;
    // 当前信箱所包含的信件元数据数组
    private Mail[]     mail;

    public boolean isNew_mail() {
        return new_mail;
    }

    public void setNew_mail(boolean new_mail) {
        this.new_mail = new_mail;
    }

    public boolean isFull_mail() {
        return full_mail;
    }

    public void setFull_mail(boolean full_mail) {
        this.full_mail = full_mail;
    }

    public String getSpace_used() {
        return space_used;
    }

    public void setSpace_used(String space_used) {
        this.space_used = space_used;
    }

    public boolean isCan_send() {
        return can_send;
    }

    public void setCan_send(boolean can_send) {
        this.can_send = can_send;
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

    public Mail[] getMail() {
        return mail;
    }

    public void setMail(Mail[] mail) {
        this.mail = mail;
    }
}
