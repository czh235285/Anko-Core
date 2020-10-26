

# Anko-Core

用于自己快速开发的框架，加入了许多项目通用的东西，免去重复造轮子,持续完善中...

主要集成谷歌推荐jetpack组件库。（抛弃了xml，采用anko dsl布局）



## 关于Anko

##### 优点

- 页面构建速度快，已经使用anko做过几个完整项目，性能肉眼可见的比xml快几倍  [使用Anko创建快400%的布局](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/1123/6795.html)
- 比xml更简单的屏幕适配
- 代码布局更好的利用kotlin的扩展函数
- 可以在构建布局时就加入逻辑判断

##### 缺点

- 不能预览，Android Studio3.3以前可以下插件预览，不过开发工具我基本都更新到最新的稳定版了
- 学习成本较高，熟练后开发速度比xml更快，不熟的话很慢
- Anko库已经停止维护。(ps：停止维护不是因为不好，而是因为打算推compose)

##### 我选择Anko的理由

- 性能，当测试、产品说你做的app比IOS的还流畅，你心里不爽吗？

- 优点真的太多了，就不说了， 说说针对它的缺点处理。首先，不能预览这个问题，由于超简单的适配方案，完全对着设计图一把梭，也不太需要预览，后面我会介绍适配方案。学习成本问题，作为程序员我觉得只有愿意和不愿意学的区别。停止维护，这个可能很多人比较介意，其实，Anko主要也就是一个扩展库，让动态布局变成dsl的写法，对于各种控件的扩展，Anko库里还是support包的，可以直接不引入，只引入基础库支持dsl布局，然后自己去扩展控件，我这边就这样做的了~

  

## 介绍

- demo只有几个简单的页面，仅供参考学习。

- 网络请求  retrofit2.6+ViewModel+协程

- 大量扩展函数，提高开发速度

- 下拉刷新：谷歌原生SwipeRefreshLayout

- [adapter](https://gitee.com/czh235285/AnkoAdapter) 自己封装的，支持空布局，header，footer，加载更多，多type。demo里有针对通用列表界面进行二次封装。

- 主要用于自己开发所用，第三方依赖全是自己常用的框架整合一起了，耦合较高，不建议直接使用，仅供参考，如果框架和我用的差不多，也可以直接用~

  

## 使用

use Gradle:

```
repositories {
        maven { url "https://jitpack.io" }
        google()
        jcenter()
}
dependencies {
  implementation 'c.core:Anko-Core:1.0.0'
}
```

## 扩展函数

最基本的shape,命名参数可以不写的，这里写出来方便理解

````kotlin
view.commonShape(
                solidColor = "#f2f2f2".color,
                radius = 2.pxf,
                strokeColor = "#e5e5e5".color,
                strokeWidth = 2
            )               
````

更多定制化的shape，边框/4个圆角大小不一样/渐变色

````kotlin
view.buildShape {
          shapeStroke {
              width = 2
              color = "#ff0000".color
          }
          shapeRadius {
              topLeft = 10.pxf
              topRight = 20.pxf
              bottomRight = 30.pxf
              bottomLeft = 40.pxf
          }
          shapeGradient {
              orientation = GradientDrawable.Orientation.LEFT_RIGHT
              startColor = "#ffffff".color
              endColor = "#333333".color
          }
}
````

selector扩展，shapeDrawable中也可以直接使用上面buildShape中的方法，还有其他状态的就不全写了。

```kotlin
view.stateListDrawable {
                    defaultState {
                        shapeDrawable {
                            shapeSolidColor = "#ffffff".color
                        }
                    }
                    pressedState {
                        shapeDrawable {
                            shapeSolidColor = "#ff0000".color
                        }
                    }
                    unPressedState {
                        shapeDrawable {
                            shapeSolidColor = "#00ff00".color
                        }
                    }
                }
```

还有很多实用的扩展函数，直接看源码吧~



## Anko超简单的适配方案

适配原理

网上各种开源适配方案，目的基本都是让不同手机展示一样的内容，也就是进行百分比适配。

UI设计图上控件的宽度 / UI设计图宽度=设备上控件的真实宽度 / 设备的宽度

也就是

设备上控件的真实宽度 = UI设计图上控件的宽度  * （设备的宽度/ UI设计图宽度）

由于anko是代码布局，设备的宽度可以直接获取的，一个扩展函数就可以直接计算了，控件宽高直接按设计图上写即可。简单粗暴吧

举例

```kotlin
class MainActivityUI : AnkoComponent<MainActivity> {
    lateinit var magicIndicator: MagicIndicator
    lateinit var viewPager: ViewPager

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            viewPager = noScrollViewPager {
                id = Ids.viewPager
            }.lparams(-1, 0, 1f)
            line {}.lparams(-1, 1)
            magicIndicator = magicIndicator {
            }.lparams(-1, 133.px)
        }
    }
}
```

这是我demo中首页的布局代码。这个133.px后面的.px就是自己扩展的方法。

具体扩展方法看 [DimensionEx](https://gitee.com/czh235285/Anko-Core/blob/master/lib/src/main/java/c/core/ex/DimensionEx.kt )

我这里设计图宽高默认是750*1334，配置方法

```kotlin
android {
    defaultConfig {
        manifestPlaceholders = [
                designWidth: 750,
                designHeight: 1334,
        ]
    }
}
```