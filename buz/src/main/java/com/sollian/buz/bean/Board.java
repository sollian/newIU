package com.sollian.buz.bean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Transient;

/**
 * @author sollian on 2017/9/4.
 */
@Entity(
        active = true
)
public class Board {
    @Id(autoincrement = true)
    private Long gId;

    @Transient
    private int favorite_level;

    // 版面名称
    @Index(unique = true)
    private String     name;
    // 版面描述——中文名
    private String     description;
    // 版面所属根分区号
    private String     section;
    // 版面是否不可回复
    private boolean    is_no_reply;
    // 版面书否允许附件
    private boolean    allow_attachment;
    // 当前用户是否有发文、回复权限
    private boolean    allow_post;
    // 版面是否允许匿名发文
    private boolean    allow_anonymous;
    /**
     * 附加
     */
    @Transient
    private Pagination pagination;
    @Transient
    private Article[]  article;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 754839907)
    private transient BoardDao   myDao;

    @Generated(hash = 1853759102)
    public Board(Long gId, String name, String description, String section,
            boolean is_no_reply, boolean allow_attachment, boolean allow_post,
            boolean allow_anonymous) {
        this.gId = gId;
        this.name = name;
        this.description = description;
        this.section = section;
        this.is_no_reply = is_no_reply;
        this.allow_attachment = allow_attachment;
        this.allow_post = allow_post;
        this.allow_anonymous = allow_anonymous;
    }

    @Generated(hash = 1406520307)
    public Board() {
    }

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

    public Long getGId() {
        return this.gId;
    }

    public void setGId(Long gId) {
        this.gId = gId;
    }

    public boolean getIs_no_reply() {
        return this.is_no_reply;
    }

    public boolean getAllow_attachment() {
        return this.allow_attachment;
    }

    public boolean getAllow_post() {
        return this.allow_post;
    }

    public boolean getAllow_anonymous() {
        return this.allow_anonymous;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 525723581)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBoardDao() : null;
    }
}
