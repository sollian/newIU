package com.sollian.buz.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property GId = new Property(0, Long.class, "gId", true, "_id");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property User_name = new Property(2, String.class, "user_name", false, "USER_NAME");
        public final static Property Gender = new Property(3, String.class, "gender", false, "GENDER");
        public final static Property Astro = new Property(4, String.class, "astro", false, "ASTRO");
        public final static Property Face_url = new Property(5, String.class, "face_url", false, "FACE_URL");
        public final static Property Qq = new Property(6, String.class, "qq", false, "QQ");
        public final static Property Msn = new Property(7, String.class, "msn", false, "MSN");
        public final static Property Home_page = new Property(8, String.class, "home_page", false, "HOME_PAGE");
        public final static Property Level = new Property(9, String.class, "level", false, "LEVEL");
        public final static Property Life = new Property(10, int.class, "life", false, "LIFE");
        public final static Property Post_count = new Property(11, int.class, "post_count", false, "POST_COUNT");
        public final static Property Score = new Property(12, int.class, "score", false, "SCORE");
        public final static Property Is_online = new Property(13, boolean.class, "is_online", false, "IS_ONLINE");
        public final static Property First_login_time = new Property(14, long.class, "first_login_time", false, "FIRST_LOGIN_TIME");
        public final static Property Last_login_time = new Property(15, long.class, "last_login_time", false, "LAST_LOGIN_TIME");
        public final static Property Last_login_ip = new Property(16, String.class, "last_login_ip", false, "LAST_LOGIN_IP");
        public final static Property Login_count = new Property(17, long.class, "login_count", false, "LOGIN_COUNT");
        public final static Property Role = new Property(18, String.class, "role", false, "ROLE");
    }

    private DaoSession daoSession;


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: gId
                "\"ID\" TEXT," + // 1: id
                "\"USER_NAME\" TEXT," + // 2: user_name
                "\"GENDER\" TEXT," + // 3: gender
                "\"ASTRO\" TEXT," + // 4: astro
                "\"FACE_URL\" TEXT," + // 5: face_url
                "\"QQ\" TEXT," + // 6: qq
                "\"MSN\" TEXT," + // 7: msn
                "\"HOME_PAGE\" TEXT," + // 8: home_page
                "\"LEVEL\" TEXT," + // 9: level
                "\"LIFE\" INTEGER NOT NULL ," + // 10: life
                "\"POST_COUNT\" INTEGER NOT NULL ," + // 11: post_count
                "\"SCORE\" INTEGER NOT NULL ," + // 12: score
                "\"IS_ONLINE\" INTEGER NOT NULL ," + // 13: is_online
                "\"FIRST_LOGIN_TIME\" INTEGER NOT NULL ," + // 14: first_login_time
                "\"LAST_LOGIN_TIME\" INTEGER NOT NULL ," + // 15: last_login_time
                "\"LAST_LOGIN_IP\" TEXT," + // 16: last_login_ip
                "\"LOGIN_COUNT\" INTEGER NOT NULL ," + // 17: login_count
                "\"ROLE\" TEXT);"); // 18: role
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_USER_ID ON \"USER\"" +
                " (\"ID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long gId = entity.getGId();
        if (gId != null) {
            stmt.bindLong(1, gId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String user_name = entity.getUser_name();
        if (user_name != null) {
            stmt.bindString(3, user_name);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(4, gender);
        }
 
        String astro = entity.getAstro();
        if (astro != null) {
            stmt.bindString(5, astro);
        }
 
        String face_url = entity.getFace_url();
        if (face_url != null) {
            stmt.bindString(6, face_url);
        }
 
        String qq = entity.getQq();
        if (qq != null) {
            stmt.bindString(7, qq);
        }
 
        String msn = entity.getMsn();
        if (msn != null) {
            stmt.bindString(8, msn);
        }
 
        String home_page = entity.getHome_page();
        if (home_page != null) {
            stmt.bindString(9, home_page);
        }
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(10, level);
        }
        stmt.bindLong(11, entity.getLife());
        stmt.bindLong(12, entity.getPost_count());
        stmt.bindLong(13, entity.getScore());
        stmt.bindLong(14, entity.getIs_online() ? 1L: 0L);
        stmt.bindLong(15, entity.getFirst_login_time());
        stmt.bindLong(16, entity.getLast_login_time());
 
        String last_login_ip = entity.getLast_login_ip();
        if (last_login_ip != null) {
            stmt.bindString(17, last_login_ip);
        }
        stmt.bindLong(18, entity.getLogin_count());
 
        String role = entity.getRole();
        if (role != null) {
            stmt.bindString(19, role);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long gId = entity.getGId();
        if (gId != null) {
            stmt.bindLong(1, gId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String user_name = entity.getUser_name();
        if (user_name != null) {
            stmt.bindString(3, user_name);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(4, gender);
        }
 
        String astro = entity.getAstro();
        if (astro != null) {
            stmt.bindString(5, astro);
        }
 
        String face_url = entity.getFace_url();
        if (face_url != null) {
            stmt.bindString(6, face_url);
        }
 
        String qq = entity.getQq();
        if (qq != null) {
            stmt.bindString(7, qq);
        }
 
        String msn = entity.getMsn();
        if (msn != null) {
            stmt.bindString(8, msn);
        }
 
        String home_page = entity.getHome_page();
        if (home_page != null) {
            stmt.bindString(9, home_page);
        }
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(10, level);
        }
        stmt.bindLong(11, entity.getLife());
        stmt.bindLong(12, entity.getPost_count());
        stmt.bindLong(13, entity.getScore());
        stmt.bindLong(14, entity.getIs_online() ? 1L: 0L);
        stmt.bindLong(15, entity.getFirst_login_time());
        stmt.bindLong(16, entity.getLast_login_time());
 
        String last_login_ip = entity.getLast_login_ip();
        if (last_login_ip != null) {
            stmt.bindString(17, last_login_ip);
        }
        stmt.bindLong(18, entity.getLogin_count());
 
        String role = entity.getRole();
        if (role != null) {
            stmt.bindString(19, role);
        }
    }

    @Override
    protected final void attachEntity(User entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // gId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // user_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // gender
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // astro
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // face_url
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // qq
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // msn
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // home_page
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // level
            cursor.getInt(offset + 10), // life
            cursor.getInt(offset + 11), // post_count
            cursor.getInt(offset + 12), // score
            cursor.getShort(offset + 13) != 0, // is_online
            cursor.getLong(offset + 14), // first_login_time
            cursor.getLong(offset + 15), // last_login_time
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // last_login_ip
            cursor.getLong(offset + 17), // login_count
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18) // role
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setGId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUser_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGender(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAstro(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFace_url(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setQq(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMsn(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setHome_page(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLevel(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setLife(cursor.getInt(offset + 10));
        entity.setPost_count(cursor.getInt(offset + 11));
        entity.setScore(cursor.getInt(offset + 12));
        entity.setIs_online(cursor.getShort(offset + 13) != 0);
        entity.setFirst_login_time(cursor.getLong(offset + 14));
        entity.setLast_login_time(cursor.getLong(offset + 15));
        entity.setLast_login_ip(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setLogin_count(cursor.getLong(offset + 17));
        entity.setRole(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setGId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getGId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getGId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
