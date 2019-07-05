package czh.fast.lib.application;

import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.vise.log.ViseLog;
import com.vise.log.inner.LogcatTree;
import com.vise.utils.assist.SSLUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.interceptor.HttpLogInterceptor;

import java.util.HashMap;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

abstract public class BaseAPP extends Application {
    public abstract String setBaseUrl();

    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
        initNet();
        initFresco();
    }

    private void initLog() {
        ViseLog.getLogConfig().configAllowLog(true)//是否输出日志
                .configShowBorders(false);//是否排版显示
        ViseLog.plant(new LogcatTree());//添加打印日志信息到Logcat的树
    }

    private void initNet() {
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(setBaseUrl())
                .globalHeaders(new HashMap<String, String>())
                .globalParams(new HashMap<String, String>())
                .readTimeout(12).writeTimeout(12)
                .connectTimeout(12)
                .retryCount(1)
                .retryDelayMillis(6000)
                .setCookie(true)
                .interceptor(new HttpLogInterceptor().setLevel(HttpLogInterceptor.Level.BODY))
                .converterFactory(GsonConverterFactory.create())
                .SSLSocketFactory(SSLUtil.getSslSocketFactory(null, null, null));
    }


    private void initFresco() {
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("ImageCache").setMaxCacheSize(200 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(100 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(50 * ByteConstants.MB)
                .setMaxCacheSize(80 * ByteConstants.MB).build();


        MemoryTrimmableRegistry memoryTrimmableRegistry = NoOpMemoryTrimmableRegistry
                .getInstance();
        memoryTrimmableRegistry.registerMemoryTrimmable(new MemoryTrimmable() {
            @Override
            public void trim(MemoryTrimType trimType) {
                final double suggestedTrimRatio = trimType.getSuggestedTrimRatio();

                if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() ==
                        suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInBackground
                        .getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground
                        .getSuggestedTrimRatio() == suggestedTrimRatio) {
                    //清空内存缓存
                    ImagePipelineFactory.getInstance().getImagePipeline().clearMemoryCaches();
                }
            }
        });

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
                .setDownsampleEnabled(true).build();
        Fresco.initialize(this, config);
    }

}
