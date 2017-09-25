package com.sollian.buz.bean;

import com.sollian.base.annotation.Local;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * @author sollian on 2017/9/5.
 */
@Entity(
        active = true
)
public class Favorite {
    public static final char SPLITTER = ';';

    @Id(autoincrement = true)
    private Long gId;

    private int         level;
    @Transient
    private List<Board> board;
    @Transient
    private Favorite[]  sub_favorite;

    //版面名称，用';'做间隔
    @Local
    private String boadNames;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 56835728)
    private transient FavoriteDao myDao;

    @Generated(hash = 27796949)
    public Favorite(Long gId, int level, String boadNames) {
        this.gId = gId;
        this.level = level;
        this.boadNames = boadNames;
    }

    @Generated(hash = 459811785)
    public Favorite() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Board> getBoard() {
        return board;
    }

    public void setBoard(List<Board> board) {
        this.board = board;
    }

    public Favorite[] getSub_favorite() {
        return sub_favorite;
    }

    public void setSub_favorite(Favorite[] sub_favorite) {
        this.sub_favorite = sub_favorite;
    }

    public Long getGId() {
        return this.gId;
    }

    public void setGId(Long gId) {
        this.gId = gId;
    }

    public String getBoadNames() {
        return this.boadNames;
    }

    public void setBoadNames(String boadNames) {
        this.boadNames = boadNames;
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
    @Generated(hash = 1250288999)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFavoriteDao() : null;
    }
}
