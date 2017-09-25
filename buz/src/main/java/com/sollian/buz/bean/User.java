package com.sollian.buz.bean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author sollian on 2017/9/4.
 */
@Entity(
        active = true
)
public class User {
    @Id(autoincrement = true)
    private Long gId;

    /**
     * 基本信息
     */
    // 用户id
    private String  id;
    // 用户昵称
    private String  user_name;
    // 用户性别：m表示男性，f表示女性，n表示隐藏性别
    private String  gender;
    // 用户星座 若隐藏星座则为空
    private String  astro;
    // 用户头像地址
    private String  face_url;
    // 用户qq
    private String  qq;
    // 用户msn
    private String  msn;
    // 用户个人主页
    private String  home_page;
    /**
     * 论坛属性
     */
    // 论坛等级
    private String  level;
    // 用户生命值
    private int     life;
    // 用户发文数量
    private int     post_count;
    // 积分
    private int     score;
    // 用户是否在线
    private boolean is_online;
    // 用户注册时间，unixtimestamp,当前登陆用户为 自己或是当前用户具有管理权限
    private long    first_login_time;
    // 用户上次登录时间，unixtimestamp
    private long    last_login_time;
    // 用户上次登录
    private String  last_login_ip;
    // 登录次数,当前登陆用户为 自己或是当前用户具有管理权限
    private long    login_count;
    // 用户身份
    private String  role;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1507654846)
    private transient UserDao    myDao;

    @Generated(hash = 821434301)
    public User(Long gId, String id, String user_name, String gender, String astro,
            String face_url, String qq, String msn, String home_page, String level,
            int life, int post_count, int score, boolean is_online,
            long first_login_time, long last_login_time, String last_login_ip,
            long login_count, String role) {
        this.gId = gId;
        this.id = id;
        this.user_name = user_name;
        this.gender = gender;
        this.astro = astro;
        this.face_url = face_url;
        this.qq = qq;
        this.msn = msn;
        this.home_page = home_page;
        this.level = level;
        this.life = life;
        this.post_count = post_count;
        this.score = score;
        this.is_online = is_online;
        this.first_login_time = first_login_time;
        this.last_login_time = last_login_time;
        this.last_login_ip = last_login_ip;
        this.login_count = login_count;
        this.role = role;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAstro() {
        return astro;
    }

    public void setAstro(String astro) {
        this.astro = astro;
    }

    public String getFace_url() {
        return face_url;
    }

    public void setFace_url(String face_url) {
        this.face_url = face_url;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getHome_page() {
        return home_page;
    }

    public void setHome_page(String home_page) {
        this.home_page = home_page;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean is_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    public long getFirst_login_time() {
        return first_login_time;
    }

    public void setFirst_login_time(long first_login_time) {
        this.first_login_time = first_login_time;
    }

    public long getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(long last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public long getLogin_count() {
        return login_count;
    }

    public void setLogin_count(long login_count) {
        this.login_count = login_count;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean didValid() {
        return id == null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", gender='" + gender + '\'' +
                ", astro='" + astro + '\'' +
                ", face_url='" + face_url + '\'' +
                ", qq='" + qq + '\'' +
                ", msn='" + msn + '\'' +
                ", home_page='" + home_page + '\'' +
                ", level='" + level + '\'' +
                ", life=" + life +
                ", post_count=" + post_count +
                ", score=" + score +
                ", is_online=" + is_online +
                ", first_login_time=" + first_login_time +
                ", last_login_time=" + last_login_time +
                ", last_login_ip='" + last_login_ip + '\'' +
                ", login_count=" + login_count +
                ", role='" + role + '\'' +
                '}';
    }

    public Long getGId() {
        return this.gId;
    }

    public void setGId(Long gId) {
        this.gId = gId;
    }

    public boolean getIs_online() {
        return this.is_online;
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
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}
