# cart-anim
购物车动画 - 贝塞尔曲线形式


## [fir.im](https://fir.im/cartanim)
![](./doc/fir.im-cartanim.png)

## Preview
![](./cart-anim.gif)

## 使用

```groovy
repositories {
  //...
  maven { url 'http://maven.mjtown.cn/' }
}

dependencies{
  compile 'com.android.support:appcompat-v7:25.3.1'
  compile 'name.zeno:cart-anim:1.0.1';
}
```

#### api

```java
/**
 * <li>{@link #startAnim(View)} 开始执行动画</li>
 * <li>{@link #setAnimLayout(int)} 设置动画布局</li>
 * <li>{@link #setCartView(View)} 设置动画执行终点的购物车View</li>
 * <li>{@link #setInterval(int)} 设置动画执行的间隔时间（ms）</li>
 * <li>{@link #setStartScale(float)} 设置起使位置缩放</li>
 * <li>{@link #setEndScale(float)} 设置结算位置缩放</li>
 */
```

## HOW TO HELP ME

- 使用过程中遇到 BUG 请给我提 issue
- 如果有好的功能需要扩展请给我提 pull request, 大家一起维护一个项目比一个人维护一个版本容易的多
- 如果觉得项目对你有帮助就请我喝瓶百岁山吧 ~
![](./doc/tenpay_3yuan.jpeg)

## CHANGE LOG

#### 1.0.1
- 添加开始与结束的 scale 设置api，现在可以大小不变，先大后小，先小后大拉
- appcompat 依赖改为 provided ，需要自行添加依赖, 别忘了哦

