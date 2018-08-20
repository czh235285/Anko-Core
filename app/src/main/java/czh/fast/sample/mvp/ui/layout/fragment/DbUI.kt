package czh.fast.sample.mvp.ui.layout.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.raizlabs.android.dbflow.sql.language.Delete
import com.raizlabs.android.dbflow.sql.language.Select
import com.rengwuxian.materialedittext.MaterialEditText
import czh.fast.lib.utils.anko.materialEditText
import czh.fast.lib.utils.checkALL
import czh.fast.lib.utils.textString
import czh.fast.lib.utils.toast
import czh.fast.lib.widget.SimpleDividerDecoration
import czh.fast.sample.R
import czh.fast.sample.db.User
import czh.fast.sample.db.User_Table

import czh.fast.sample.mvp.ui.fragment.DbFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


class DbUI : AnkoComponent<DbFragment> {

    lateinit var etName: MaterialEditText
    lateinit var etAge: MaterialEditText
    lateinit var rcv: RecyclerView

    override fun createView(ui: AnkoContext<DbFragment>): View = with(ui) {
        return verticalLayout {
            etName = materialEditText {
                hint = "姓名"
            }.lparams(matchParent) {
                setMargins(20, 20, 20, 20)
            }
            etAge = materialEditText {
                hint = "年龄"
                inputType= InputType.TYPE_CLASS_NUMBER
            }.lparams(matchParent) {
                setMargins(20, 20, 20, 20)
            }
            textView("添加") {
                setOnClickListener {
                    if (!checkALL(etAge, etName)) {
                        ctx.toast("输入不能为空")
                        return@setOnClickListener
                    }
                    val mList = Select().from(User::class.java).where(User_Table.urerName.`is`(etName.textString())).queryList()
                    if (mList.size == 0) {
                        val db = User()
                        db.urerName = etName.textString()
                        db.urerAge = etAge.textString().toInt()
                        db.save()
                        owner.queryList()
                    } else {
                        ctx.toast("重复名字")
                    }
                }
            }.lparams(width = matchParent) {
                setMargins(30, 30, 30, 30)
            }
            textView("清空") {
                setOnClickListener {
                    Delete.table(User::class.java)
                    owner.queryList()
                }
            }.lparams(width = matchParent) {
                setMargins(30, 30, 30, 30)
            }
            rcv = recyclerView {
                layoutManager = LinearLayoutManager(ctx)
                addItemDecoration(SimpleDividerDecoration(0xffe5e5e5.toInt(), 1))
                adapter = owner.mAdapter

            }


        }.apply {
            applyRecursively {
                when (it) {
                    is MaterialEditText -> {
                        it.apply {
                            setFloatingLabel(1)
                            maxLines = 1
                            textSize = 10f
                        }
                    }

                    is TextView -> {
                        it.apply {
                            padding = 30
                            gravity = Gravity.CENTER
                            textColorResource = R.color.white
                            backgroundColorResource = R.color.colorPrimary
                        }
                    }
                }
            }
        }
    }
}
