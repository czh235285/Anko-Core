package czh.fast.sample.mvp.ui.activity

import android.content.Intent
import android.net.Uri
import com.azhon.appupdate.manager.DownloadManager
import czh.fast.sample.R
import czh.fast.sample.base.AnkoActivity
import czh.fast.sample.mvp.ui.layout.UpdateActivityUI
import org.jetbrains.anko.setContentView


class UpdateActivity : AnkoActivity() {
    val ui = UpdateActivityUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }


    override fun afterInitView() {

    }

    val url = "https://wanshangzhijia.oss-cn-beijing.aliyuncs.com/apk/wszj_v1.0.0.apk"

    fun update() {
        val manager: DownloadManager = DownloadManager.getInstance(this)
        manager.setApkName("appupdate.apk")
                .setApkUrl(url)
                .setSmallIcon(R.mipmap.ic_launcher)
                .download()

    }


    fun openBrowserUpdate() {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}