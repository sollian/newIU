package com.sollian.buz.bean;

/**
 * @author sollian on 2017/9/20.
 */

public class VoteList {
    //最新投票
    public static final String NEW  = "new";
    //我的投票
    public static final String ME   = "me";
    //我参与的投票
    public static final String JOIN = "join";
    //热门投票
    public static final String HOT  = "hot";
    //全部
    public static final String ALL  = "all";

    // 所查询的投票列表的投票元数据构成的数组
    private Vote[]     votes;
    // 当前投票列表的分页信息
    private Pagination pagination;

    public Vote[] getVotes() {
        return votes;
    }

    public void setVotes(Vote[] votes) {
        this.votes = votes;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
