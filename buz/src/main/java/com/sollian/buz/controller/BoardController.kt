package com.sollian.buz.controller

import com.sollian.buz.bean.Board
import com.sollian.buz.dao.BoardDB
import com.sollian.buz.response.BoardResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author solli on 2017/9/23.
 */
class BoardController : AbsController() {
    companion object {
        val API_BOARD = API_HEAD + "/board/"
    }

    /**
     * 获取指定版面的信息
     *
     * @param name 合法的版面名称
     * @param page 文章的页数
     */
    fun asyncGet(name: String, page: Int,
                 consumer: ((response: BoardResponse) -> Unit)?) {
        getObservable("$API_BOARD$name$FORMAT?page=$page&$APP_KEY")
                .observeOn(Schedulers.io())
                .map {
                    val boardResponse = BoardResponse(getJson(it))
                    if (boardResponse.success()) {
                        saveBoard2DB(boardResponse.obj!!, page)
                    }
                    boardResponse
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }

    fun syncGet(name: String) = BoardDB.queryByName(name)

    private fun saveBoard2DB(board: Board, page: Int) {
        if ((page == 0)) {
            BoardDB.insertOrReplace(board)
        }
        ArticleController().safeInsertOrUpdate(*board.article)
    }
}