package czh.fast.sample.mvp.ui.layout.item

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import czh.fast.lib.utils.anko.circleImageView
import czh.fast.lib.utils.anko.roundImageView
import czh.fast.lib.utils.setShape
import czh.fast.sample.R
import czh.fast.sample.utils.likeView
import czh.library.LikeView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class GoodsItemUI : AnkoComponent<Context> {
    lateinit var riv: SimpleDraweeView

    lateinit var tvTitle: TextView

    lateinit var tvPrice: TextView

    lateinit var civ: SimpleDraweeView

    lateinit var tvWantToBuy: TextView

    lateinit var likeview: LikeView

    lateinit var rl: RelativeLayout

    @SuppressLint("ResourceType")
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        frameLayout {
            lparams(matchParent, wrapContent)
            cardView {
                cardElevation = 10f
                setCardBackgroundColor(Color.parseColor("#ffffff"))
                radius = dip(8).toFloat()
                verticalLayout {
                    relativeLayout {
                        riv = roundImageView(10f, 10f, 0f, 0f) {
                            id = 1
                        }.lparams(matchParent, wrapContent)
                        rl = relativeLayout {
                            tvWantToBuy = textView("2000") {
                                rightPadding = dip(8)
                                textSize = 15f
                                textColorResource = R.color.white
                                gravity = Gravity.RIGHT
                                setShape("#DDfed000", radiusArray = floatArrayOf(60f, 60f, 0f, 0f, 0f, 0f, 60f, 60f))
                            }.lparams(dip(56), wrapContent) {
                                centerVertically()
                            }
                            likeview = likeView {
                                horizontalPadding = dip(40)
                                setmDefaultColor(Color.parseColor("#ffffffff"))
                                setmRadius(dip(5).toFloat())
                                setmCycleTime(350)
                                setmUnLikeType(2)
                                setmCheckedColor(Color.parseColor("#DE383D"))
                                selectLike(false)
                            }.lparams {
                                centerVertically()

                            }
                        }.lparams {
                            bottomMargin = dip(16)
                            alignParentRight()
                            sameBottom(1)
                        }
                    }

                    tvTitle = textView {
                        lines = 2
                        maxLines = 2
                        ellipsize = TextUtils.TruncateAt.END
                        horizontalPadding = dip(8)
                        topPadding = dip(20)
                        textSize = 15f
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = Color.parseColor("#333333")
                    }
                    relativeLayout {
                        lparams(matchParent, dip(40))
                        tvPrice = textView {
                            leftPadding = dip(8)
                            textSize = 15f
                            textColor = Color.parseColor("#333333")
                        }.lparams { centerVertically() }

                        civ = circleImageView { }.lparams(dip(24), dip(24)) {
                            alignParentRight()
                            centerVertically()
                            rightMargin = dip(16)
                        }
                    }


                }.lparams(matchParent, wrapContent)
            }.lparams(matchParent, wrapContent) {
                margin = dip(5)
            }
        }
    }
}