package com.sollian.buz.bean;

import com.sollian.base.annotation.Local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * @author sollian on 2017/9/20.
 */
@Entity(
        active = true
)
public class Section {
    public static final char SPLITTER = ';';

    @Id(autoincrement = true)
    private Long gId;

    // 分区数量
    private int       section_count;
    // 分区名称
    @Index(unique = true)
    private String    name;
    // 分区表述
    private String    description;
    // 是否是根分区
    private boolean   is_root;
    // 该分区所属根分区名称
    private String    parent;
    // 分区数组
    @Transient
    private Section[] section;
    // 子目录name数组
    @Transient
    private String[]  sub_section;
    // 子分区数组
    @Transient
    private Board[]   board;
    @Local
    private String    subSectionNames;
    @Local
    private String    boardNames;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1718547978)
    private transient SectionDao myDao;

    @Generated(hash = 898837483)
    public Section(Long gId, int section_count, String name, String description,
            boolean is_root, String parent, String subSectionNames,
            String boardNames) {
        this.gId = gId;
        this.section_count = section_count;
        this.name = name;
        this.description = description;
        this.is_root = is_root;
        this.parent = parent;
        this.subSectionNames = subSectionNames;
        this.boardNames = boardNames;
    }

    @Generated(hash = 111791983)
    public Section() {
    }

    public int getSection_count() {
        return section_count;
    }

    public void setSection_count(int section_count) {
        this.section_count = section_count;
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

    public boolean is_root() {
        return is_root;
    }

    public void setIs_root(boolean is_root) {
        this.is_root = is_root;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Section[] getSection() {
        return section;
    }

    public void setSection(Section[] section) {
        this.section = section;
    }

    public String[] getSub_section() {
        return sub_section;
    }

    public void setSub_section(String[] sub_section) {
        this.sub_section = sub_section;
    }

    public Board[] getBoard() {
        return board;
    }

    public void setBoard(Board[] board) {
        this.board = board;
    }

    public Long getGId() {
        return this.gId;
    }

    public void setGId(Long gId) {
        this.gId = gId;
    }

    public boolean getIs_root() {
        return this.is_root;
    }

    public String getSubSectionNames() {
        return this.subSectionNames;
    }

    public void setSubSectionNames(String subSectionNames) {
        this.subSectionNames = subSectionNames;
    }

    public String getBoardNames() {
        return this.boardNames;
    }

    public void setBoardNames(String boardNames) {
        this.boardNames = boardNames;
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
    @Generated(hash = 479686395)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSectionDao() : null;
    }
}
