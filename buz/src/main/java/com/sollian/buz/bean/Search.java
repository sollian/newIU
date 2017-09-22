package com.sollian.buz.bean;

/**
 * @author sollian on 2017/9/20.
 */

public class Search {
    private Pagination pagination;
    private Article[] threads;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Article[] getThreads() {
        return threads;
    }

    public void setThreads(Article[] threads) {
        this.threads = threads;
    }
}
