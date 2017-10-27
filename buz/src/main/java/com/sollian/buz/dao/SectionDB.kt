package com.sollian.buz.dao

import com.sollian.buz.bean.Section
import com.sollian.buz.bean.SectionDao

/**
 * @author sollian on 2017/10/27.
 */
object SectionDB : AbsDB() {
    override fun getSessionKey() = "section"

    fun insertOrReplace(section: Section) {
        db().insertOrReplace(section)
    }

    fun insertOrReplace(sections: Iterable<Section>) {
        db().insertOrReplaceInTx(sections)
    }

    fun queryRoot(): List<Section> {
        return db().queryBuilder()
                .where(SectionDao.Properties.Is_root.eq(true))
                .list()
    }

    fun queryByName(name: String): Section {
        return db().queryBuilder()
                .where(SectionDao.Properties.Name.eq(name))
                .unique()
    }

    private fun db() = session().sectionDao!!
}