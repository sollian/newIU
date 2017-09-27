package com.sollian.buz.controller

import com.sollian.buz.bean.Favorite
import com.sollian.buz.dao.BoardDB
import com.sollian.buz.dao.FavoriteDB
import com.sollian.buz.response.FavoriteResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Response

/**
 * @author solli on 2017/9/24.
 */
class FavoriteController : AbsController() {
    companion object {
        val API_FAVORITE = API_HEAD + "/favorite/"
    }

    fun asyncGet(level: Int = 0, consumer: ((response: FavoriteResponse) -> Unit)?) {
        getObservable(API_FAVORITE + level + FORMAT + '?' + APP_KEY)
                .observeOn(Schedulers.io())
                .map {
                    saveFavorite2DB(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }

    /**
     * @param level
     * @param name
     * @param dir     是否为自定义目录：0不是，1是
     */
    fun asyncAdd(name: String, level: Int = 0, dir: Int = 0,
                 consumer: ((response: FavoriteResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "name" to name,
                "dir" to dir.toString()
        )

        postObservable(API_FAVORITE + "add/" + level + FORMAT + '?' + APP_KEY, params)
                .observeOn(Schedulers.io())
                .map {
                    saveFavorite2DB(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }

    fun asyncDelete(name: String, level: Int = 0, dir: Int = 0,
                    consumer: ((response: FavoriteResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "name" to name,
                "dir" to dir.toString()
        )

        postObservable(API_FAVORITE + "delete/" + level + FORMAT + '?' + APP_KEY, params)
                .observeOn(Schedulers.io())
                .map {
                    saveFavorite2DB(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }

    fun syncQuery(): Favorite? {
        val favorites = FavoriteDB.query()
        return if (favorites.isNotEmpty()) {
            val favorite = favorites[0]
            fillBoard2Favorite(favorite)
            if (favorite.boadNames.length == favorite.board.size) favorite else null
        } else null
    }

    private fun fillBoard2Favorite(favorite: Favorite) {
        val names = favorite.boadNames.split(Favorite.SPLITTER)
        val boards = BoardDB.queryByNames(*names.toTypedArray())
        favorite.board = boards
    }

    private fun saveFavorite2DB(response: Response?): FavoriteResponse {
        val favoriteResponse = FavoriteResponse(getJson(response))
        if (favoriteResponse.success()) {
            val favorite = favoriteResponse.obj!!
            favorite.boadNames = {
                var names = ""
                favorite.board.forEach {
                    names += it.name + Favorite.SPLITTER
                }
                names.dropLast(1)
            }.invoke()
            FavoriteDB.replace(favorite)
            BoardDB.insertOrReplace(favorite.board)
        }
        return favoriteResponse
    }
}