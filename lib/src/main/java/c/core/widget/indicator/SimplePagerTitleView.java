package c.core.widget.indicator;


import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;


/**
 * 带文本的指示器标题
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class SimplePagerTitleView extends TextView implements IMeasurablePagerTitleView {
    protected int mSelectedColor;
    protected int mNormalColor;
    protected int mSelectedTextSize;
    protected int mNormalTextSize;
    protected int padding;
    protected boolean isSelectBold = true;

    public void setSelectBold(boolean selectBold) {
        isSelectBold = selectBold;
    }

    public SimplePagerTitleView(Context context, int padding) {
        super(context, null);
        this.padding = padding;
        init(context);
    }

    private void init(Context context) {
        setGravity(Gravity.CENTER);
        setPadding(padding, 0, padding, 0);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        setTextColor(mSelectedColor);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mSelectedTextSize);
        if (isSelectBold){
            setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        setTextColor(mNormalColor);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mNormalTextSize);
        if (isSelectBold){
            setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
    }

    @Override
    public int getContentLeft() {
        Rect bound = new Rect();
        String longestString = "";
        if (getText().toString().contains("\n")) {
            String[] brokenStrings = getText().toString().split("\\n");
            for (String each : brokenStrings) {
                if (each.length() > longestString.length()) longestString = each;
            }
        } else {
            longestString = getText().toString();
        }
        getPaint().getTextBounds(longestString, 0, longestString.length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 - contentWidth / 2;
    }

    @Override
    public int getContentTop() {
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 - contentHeight / 2);
    }

    @Override
    public int getContentRight() {
        Rect bound = new Rect();
        String longestString = "";
        if (getText().toString().contains("\n")) {
            String[] brokenStrings = getText().toString().split("\\n");
            for (String each : brokenStrings) {
                if (each.length() > longestString.length()) longestString = each;
            }
        } else {
            longestString = getText().toString();
        }
        getPaint().getTextBounds(longestString, 0, longestString.length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 + contentWidth / 2;
    }

    @Override
    public int getContentBottom() {
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 + contentHeight / 2);
    }

    public int getSelectedColor() {
        return mSelectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        mSelectedColor = selectedColor;
    }

    public int getNormalColor() {
        return mNormalColor;
    }

    public void setSelectedTextSize(int mSelectedTextSize) {
        this.mSelectedTextSize = mSelectedTextSize;
    }

    public void setNormalTextSize(int mNormalTextSize) {
        this.mNormalTextSize = mNormalTextSize;
    }

    public void setNormalColor(int normalColor) {
        mNormalColor = normalColor;
    }
}
