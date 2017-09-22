package com.sollian.buz.bean;

import java.util.List;

/**
 * @author sollian on 2017/9/5.
 */

public class Favorite {
    private int         level;
    private List<Board> board;
    private Favorite[]  sub_favorite;

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
}
