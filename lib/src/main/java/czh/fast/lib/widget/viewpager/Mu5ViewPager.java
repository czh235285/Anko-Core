package czh.fast.lib.widget.viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gjm on 2017/7/26.
 * Func:自定义viewpager实现滑动时 每张高度不同的图片 能够平滑过渡
 */

public class Mu5ViewPager extends ViewPager {

    protected int fixedSpeed;//重设滚动的速度
    protected int[] sourceHeights;//每张图片的高度
    private float defaultHeight;

    protected Mu5Interface mu5Interface;//Mu5ViewPager涉及到的一切事件回调
    protected OnPageChangeListener userCustomPageChangeListener;//提供给用户自定义OnPageChangeListener的能力
    protected Mu5PagerAdapter mu5PagerAdapter;
    private String TAG = this.getClass().getSimpleName();

    /**
     * Mu5ViewPager涉及到的一切事件回调
     *
     * @param mu5Interface
     */
    public void setMu5Interface(Mu5Interface mu5Interface) {
        this.mu5Interface = mu5Interface;
        if (mu5PagerAdapter != null) {
            mu5PagerAdapter.setMu5Interface(mu5Interface);
        }
    }

    /**
     * 提供给用户自定义OnPageChangeListener的能力
     *
     * @param userCustomPageChangeListener
     */
    public void setUserCustomPageChangeListener(OnPageChangeListener userCustomPageChangeListener) {
        this.userCustomPageChangeListener = userCustomPageChangeListener;
    }

    protected OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == sourceHeights.length - 1 || defaultHeight == 0) {//用于defaultHeight初始化之前的拦截,即等到获取到第一张图片再做操作
                return;
            }
            //计算ViewPager即将变化到的高度
            int height = (int) ((sourceHeights[position] == 0 ? defaultHeight : sourceHeights[position]) * (1 - positionOffset)
                    + (sourceHeights[(position + 1)] == 0 ? defaultHeight : sourceHeights[(position + 1)]) * positionOffset);

            //为ViewPager设置高度
            ViewGroup.LayoutParams params = Mu5ViewPager.this.getLayoutParams();
            params.height = height;
            Mu5ViewPager.this.setLayoutParams(params);

            if (userCustomPageChangeListener != null) {
                userCustomPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (userCustomPageChangeListener != null) {
                userCustomPageChangeListener.onPageSelected(position);
            }
            if (mu5Interface != null) {
                mu5Interface.onIndexChange(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (userCustomPageChangeListener != null) {
                userCustomPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };

    public Mu5ViewPager(Context context) {
        super(context);
        init();
    }

    public Mu5ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化数据
     */
    private void init() {
        if (fixedSpeed > 0) {
            setScrollerSpeed(fixedSpeed);
        }

        addOnPageChangeListener(onPageChangeListener);


    }

    /**
     * 设置图片的网络链接
     *
     * @param urls
     */
    public void setNewData(List<String> urls) {
        if (urls == null || urls.size() == 0)
            throw new RuntimeException("error:don't give a empty source to me!");
        sourceHeights = new int[urls.size()];
        if (mu5PagerAdapter != null) {
            mu5PagerAdapter.setUrls(urls);
            mu5PagerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置图片的网络链接，回调接口（注意：这个必须实现的）
     *
     * @param urls
     * @param mu5Interface
     */
    public void setData(List<String> urls, Mu5Interface mu5Interface) {
        if (urls == null || urls.size() == 0)
            throw new RuntimeException("error:don't give a empty source to me!");
        this.mu5Interface = mu5Interface;
        sourceHeights = new int[urls.size()];
        mu5PagerAdapter = new Mu5PagerAdapter(getContext(), urls, mu5Interface);
        this.setAdapter(mu5PagerAdapter);
    }

    /**
     * 设置图片的网络链接，回调接口（注意：这个必须实现的）
     *
     * @param urls
     * @param mu5Interface
     */
    public void setData(String[] urls, Mu5Interface mu5Interface) {
        setData(Arrays.asList(urls), mu5Interface);
    }


    public void bindSource(Bitmap loadedImage, int position, ImageView imageView) {
        if (loadedImage != null) {
            float scale = (float) loadedImage.getHeight() / loadedImage.getWidth();
            int height = (int) (scale * Utils.INSTANCE.getDisplayWidth(getContext()));
            setSourceHeights(height, position);
            imageView.setImageBitmap(loadedImage);
        } else {
//            Toast.makeText(getContext(), "error:picture is empty", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 设置加载出的图片的高度参数
     * <p>
     * 设计目的：主要便于用户使用自己的加载框架
     *
     * @param height
     * @param position
     */
    private void setSourceHeights(int height, int position) {

        if (height < 0) throw new RuntimeException("error:i got a wrong height:" + height);
        if (sourceHeights == null || sourceHeights.length == 0 || sourceHeights.length <= position)
            throw new RuntimeException("error:i don't have so much more index");
        sourceHeights[position] = height;
        if (position == 0 && defaultHeight == 0) {//初始化默认高度
            defaultHeight = height;
            ViewGroup.LayoutParams params = Mu5ViewPager.this.getLayoutParams();
            params.height = height;
            Mu5ViewPager.this.setLayoutParams(params);
        }
    }


    public void setScrollerSpeed(int speed) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), null, speed);
            mScroller.set(this, scroller);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
