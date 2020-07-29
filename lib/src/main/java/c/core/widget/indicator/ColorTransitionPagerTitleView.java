package c.core.widget.indicator;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;

import net.lucode.hackware.magicindicator.buildins.ArgbEvaluatorHolder;


/**
 * 两种颜色过渡的指示器标题
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class ColorTransitionPagerTitleView extends SimplePagerTitleView {

    public ColorTransitionPagerTitleView(Context context, int p) {
        super(context, p);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(leavePercent, mSelectedColor, mNormalColor);
        setTextColor(color);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mNormalTextSize);
        if (isSelectBold) {
            setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(enterPercent, mNormalColor, mSelectedColor);
        setTextColor(color);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mSelectedTextSize);
        if (isSelectBold) {
            setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

    @Override
    public void onSelected(int index, int totalCount) {
    }

    @Override
    public void onDeselected(int index, int totalCount) {
    }
}
