package com.sollian.buz.bean;

import com.sollian.base.annotation.Local;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * 十大@author sollian on 2017/9/20.
 */
@Entity(
        active = true
)
public class Widget {
    public static final char SPLITTER = ';';

    @Id(autoincrement = true)
    private Long gId;

    private String name;
    private String title;

    /**
     * 附加
     */
    @Transient
    private Article[] article;
    @Local
    private String    articleIds;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 142730641)
    private transient WidgetDao myDao;

    @Generated(hash = 1451035034)
    public Widget(Long gId, String name, String title, String articleIds) {
        this.gId = gId;
        this.name = name;
        this.title = title;
        this.articleIds = articleIds;
    }

    @Generated(hash = 938966889)
    public Widget() {
    }

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

    public String getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(String articleIds) {
        this.articleIds = articleIds;
    }

    public Long getGId() {
        return this.gId;
    }

    public void setGId(Long gId) {
        this.gId = gId;
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
    @Generated(hash = 403624969)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWidgetDao() : null;
    }
}
