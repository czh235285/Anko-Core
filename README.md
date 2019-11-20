## FastKotlin

用于自己快速开发的框架，加入了许多项目通用的东西，免去重复造轮子,待完善。。。

[码云链接](https://gitee.com/czh235285/FastKotlin)国内下载快点

抛弃了xml，完全采用anko DSL布局。

kotlin常用插件 JsonToKotlinClass同java的GsonFormat

use Gradle:

```
repositories {
  maven { url "[https://jitpack.io](https://jitpack.io)" }
  mavenCentral()
  google()
}
dependencies {
  implementation 'com.gitee.czh235285:FastKotlin:2.0.4'
}
```
## 介绍

* 一个简单的Demo，初学kotlin的可以看看,kotlin+协程+retrofit2+mvp+AnKo
* 屏幕适配自己扩展函数实现的最简单百分比适配方法，适用任何设备。详细看demo
* 很多人尝试协程做网络封装又不知道怎么下手的可以参考下，本人公司项目已经直接用上了，不过乐固加固后会出问题，360加固没毛病。
* 内容不多，但是网络请求，图片，数据库，RecyclerView的adapter写法都有涉及到，差不多常见项目也就这些了吧！
* 沉浸式状态栏，浅色状态栏。
* 大量常用工具类，扩展函数
* 网络请求用的 Retrofit2.6
* 数据库 [dbflow](https://github.com/Raizlabs/DBFlow)(目前最好用的安卓数据库，lib中未添加，具体用法可以看demo)
* 通用adapter [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)。换anko后已经弃用这个，目前用的基类是自己写的。
* 下拉刷新/上拉加载库 [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)
* 6.0动态权限申请 [acp](https://github.com/mylhyl/AndroidAcp)

## 一键生成MVP代码
![](mvp.gif)


加载图片:

```

一行代码简单调用

imageView.load(url)  

```

跳转Activity:

```

不带参数
openActivity<OtherActivity>()  或者
openActivityForResult<OtherActivity>(500)

传值
openActivity<OtherActivity>("id" to 1,"name" to "姓名")  或者
openActivityForResult<OtherActivity>(500,"id" to 1,"name" to "姓名")

OtherActivity获取参数

val id=intent.extras.getInt("id")
val name=intent.extras.getString("name")

```

Dialog:

```

一行代码简单调用

 showDialog("提示语")  或者
 showDialog("提示语",{ //确定按钮操作})

```

网络请求加载动画:

```

仿百度加载动画
 showLoading()
 stopLoading()

```
