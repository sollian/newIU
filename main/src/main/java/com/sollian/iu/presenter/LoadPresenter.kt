package com.sollian.iu.presenter

import android.content.Intent
import android.os.Bundle
import com.sollian.base.view.BasePresenter
import com.sollian.buz.sharepref.SharePrefs
import com.sollian.iu.activity.LoadActivity
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.activity.SignActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author sollian on 2017/9/26.
 */
class LoadPresenter(page: LoadActivity) : BasePresenter<LoadActivity>(page) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Observable.create<Boolean> {
            it.onNext(SharePrefs.name.isNotEmpty() and SharePrefs.password.isNotEmpty())
        }
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val intent: Intent =
                            if (it) Intent(page, MainActivity::class.java)
                            else Intent(page, SignActivity::class.java)
                    page.startActivity(intent)
                    page.finish()
                }

    }
}