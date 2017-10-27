package com.sollian.buz.bean;

import com.sollian.base.annotation.Local;

/**
 * @author sollian on 2017/9/20.
 */

public class Vote {
    public static final int SINGLE_VOTE = 0;
    public static final int MULTI_VOTE  = 1;

    // 投票标识id
    private int      vid;
    // 投票标题
    private String   title;
    // 投票发起时间戳
    private long     start;
    // 投票截止时间戳
    private long     end;
    // 投票参与的人数
    private int      user_count;
    // 投票总票数(投票类型为单选时与user_count相等)，如果设置投票后可见且还没投票这个值为-1,只存在于/vote/:id中
    private int      vote_count;
    // 投票类型，0为单选，1为多选
    private int      type;
    // 每个用户能投票数的最大值，只有当type为1时，此属性有效
    private int      limit;
    // 投票所关联的投票版面的文章id
    private int      aid;
    // 投票是否截止
    private boolean  is_end;
    // 投票是否被删除
    private boolean  is_deleted;
    // 投票结果是否投票后可见
    private boolean  is_result_voted;
    // 投票发起人的用户元数据，如果该用户不存在则为字符串
    private User     user;
    // 当前用户的投票结果，如果用户已投票，则含有两个属性time(int)和viid(array)，分别表示投票的时间和所投选项的viid数组；
    // 如果用户没投票则为false
    private Voted    voted;
    // 投票选项，由投票选项元数据组成的数组，只存在于/vote/:id中
    private Option[] options;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public boolean is_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }

    public boolean is_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public boolean is_result_voted() {
        return is_result_voted;
    }

    public void setIs_result_voted(boolean is_result_voted) {
        this.is_result_voted = is_result_voted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Voted getVoted() {
        return voted;
    }

    public void setVoted(Voted voted) {
        this.voted = voted;
    }

    public Option[] getOptions() {
        return options;
    }

    public void setOptions(Option[] options) {
        this.options = options;
    }

    public boolean didVoted() {
        return voted != null;
    }

    public boolean didMultiVote() {
        return type == 1;
    }

    public boolean canVote() {
        return !(didVoted() || is_end);
    }

    /**
     * 投票选项元数据
     *
     * @author sollian
     */
    public static class Option {
        // 投票选项标识id
        private int     viid;
        // 选项内容
        private String  label;
        // 该选项为已投票数，如果设置投票后可见且还没投票这个值为-1
        private int     num;
        @Local
        private float   num_relative;
        @Local
        private boolean isChecked;

        public int getViid() {
            return viid;
        }

        public void setViid(int viid) {
            this.viid = viid;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public float getNum_relative() {
            return num_relative;
        }

        public void setNum_relative(float num_relative) {
            this.num_relative = num_relative;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }

    public static class Voted {
        // 所投选项的viid数组
        private int[] viid;
        // 投票的时间
        private long  time;

        public int[] getViid() {
            return viid;
        }

        public void setViid(int[] viid) {
            this.viid = viid;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}
