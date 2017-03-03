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
  compile 'name.zeno:cart-anim:1.0.0';
}
```

#### api

```java
/**
 * {@link #startAnim(View)} 开始执行动画
 * {@link #setAnimLayout(int)} 设置动画布局
 * {@link #setCartView(View)} 设置动画执行终点的购物车View
 * {@link #setInterval(int)} 设置动画执行的间隔时间（ms）
 */
```

