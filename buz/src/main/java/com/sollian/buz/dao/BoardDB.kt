package com.sollian.buz.dao

import com.sollian.buz.bean.Board
import com.sollian.buz.bean.BoardDao

/**
 * @author sollian on 2017/9/25.
 */
object BoardDB : AbsDB() {
    override fun getSessionKey() = "board"

    fun queryByName(name: String): Board? {
        return db().queryBuilder()
                .where(BoardDao.Properties.Name.eq(name))
                .unique()
    }

    fun queryByNames(vararg names: String): List<Board> {
        return db().queryBuilder()
                .where(BoardDao.Properties.Name.`in`(names))
                .list()
    }

    fun insertOrUpdate(board: Board) {
        db().insertOrReplace(board)
    }

    fun insertOrUpdate(boards: Iterable<Board>) {
        db().insertOrReplaceInTx(boards)
    }

    private fun db() = session().boardDao
}