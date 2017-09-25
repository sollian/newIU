package com.sollian.buz.bean;

import com.sollian.base.annotation.Local;

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
public class Article {
    @Id(autoincrement = true)
    private Long gId;

    // 文章id
    @Index(unique = true)
    private int     id;
    // 该文章所属主题的id
    private int     group_id;
    // 该文章回复文章的id
    private int     reply_id;
    // 文章标记 分别是m g ; b u o 8
    private String  flag;
    // 文章是否置顶
    private boolean is_top;
    // 该文章是否是主题帖
    private boolean is_subject;
    // 文章是否有附件
    private boolean has_attachment;
    // 当前登陆用户是否对文章有管理权限 包括编辑，删除，修改附件
    private boolean is_admin;
    // 文章标题
    private String  title;
    // 文章发表时间，unixtimestamp
    private long    post_time;
    // 所属版面名称
    private String  board_name;
    // 在/board/:name的文章列表和/search/(article|threads)中不存在此属性
    private String  content;
    // 该文章的前一篇文章id,只存在于/article/:board/:id中
    private int     previous_id;
    // 该文章的后一篇文章id,只存在于/article/:board/:id中
    private int     next_id;
    // 该文章同主题前一篇文章id,只存在于/article/:board/:id中
    private int     threads_previous_id;
    // 该文章同主题后一篇文章id,只存在于/article/:board/:id中
    private int     threads_next_id;
    // 该主题回复文章数,只存在于/board/:name，/threads/:board/:id和/search/threads中
    private int     reply_count;

    /**
     * 附加
     */
    @Transient
    private User       user;
    @Transient
    private Attachment attachment;
    @Local
    private String     userId;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 434328755)
    private transient ArticleDao myDao;

    @Generated(hash = 1273310861)
    public Article(Long gId, int id, int group_id, int reply_id, String flag,
            boolean is_top, boolean is_subject, boolean has_attachment,
            boolean is_admin, String title, long post_time, String board_name,
            String content, int previous_id, int next_id, int threads_previous_id,
            int threads_next_id, int reply_count, String userId) {
        this.gId = gId;
        this.id = id;
        this.group_id = group_id;
        this.reply_id = reply_id;
        this.flag = flag;
        this.is_top = is_top;
        this.is_subject = is_subject;
        this.has_attachment = has_attachment;
        this.is_admin = is_admin;
        this.title = title;
        this.post_time = post_time;
        this.board_name = board_name;
        this.content = content;
        this.previous_id = previous_id;
        this.next_id = next_id;
        this.threads_previous_id = threads_previous_id;
        this.threads_next_id = threads_next_id;
        this.reply_count = reply_count;
        this.userId = userId;
    }

    @Generated(hash = 742516792)
    public Article() {
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean is_top() {
        return is_top;
    }

    public void setIs_top(boolean is_top) {
        this.is_top = is_top;
    }

    public boolean is_subject() {
        return is_subject;
    }

    public void setIs_subject(boolean is_subject) {
        this.is_subject = is_subject;
    }

    public boolean isHas_attachment() {
        return has_attachment;
    }

    public void setHas_attachment(boolean has_attachment) {
        this.has_attachment = has_attachment;
    }

    public boolean is_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPost_time() {
        return post_time;
    }

    public void setPost_time(long post_time) {
        this.post_time = post_time;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrevious_id() {
        return previous_id;
    }

    public void setPrevious_id(int previous_id) {
        this.previous_id = previous_id;
    }

    public int getNext_id() {
        return next_id;
    }

    public void setNext_id(int next_id) {
        this.next_id = next_id;
    }

    public int getThreads_previous_id() {
        return threads_previous_id;
    }

    public void setThreads_previous_id(int threads_previous_id) {
        this.threads_previous_id = threads_previous_id;
    }

    public int getThreads_next_id() {
        return threads_next_id;
    }

    public void setThreads_next_id(int threads_next_id) {
        this.threads_next_id = threads_next_id;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public boolean getIs_top() {
        return this.is_top;
    }

    public boolean getIs_subject() {
        return this.is_subject;
    }

    public boolean getHas_attachment() {
        return this.has_attachment;
    }

    public boolean getIs_admin() {
        return this.is_admin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    @Generated(hash = 2112142041)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getArticleDao() : null;
    }
}
