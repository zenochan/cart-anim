package zeno.name.animcart;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

/**
 * <h1>API</h1>
 * <ul>
 * <li>{@link #startAnim(View)} 开始执行动画</li>
 * <li>{@link #setAnimLayout(int)} 设置动画布局</li>
 * <li>{@link #setCartView(View)} 设置动画执行终点的购物车View</li>
 * <li>{@link #setInterval(int)} 设置动画执行的间隔时间（ms）</li>
 * </ul>
 *
 * @author 陈治谋 (513500085@qq.com)
 * @since 16/8/9
 */
public class CartAnimView extends FrameLayout
{
  private PointF location = new PointF();

  private List<View> viewPool = new ArrayList<>();

  @Setter private int interval = 1000;    //执行动画的时间

  @LayoutRes
  @Setter(onParam = @__({@LayoutRes}))
  private         int  animLayout;
  @Setter private View cartView;
  private PointF cartLocation = new PointF(0, 0);

  public CartAnimView(Context context)
  {
    this(context, null);
  }

  public CartAnimView(Context context, AttributeSet attrs)
  {
    this(context, attrs, 0);
  }

  public CartAnimView(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  public void startAnim(final View view)
  {
    final View animView;
    if (!viewPool.isEmpty()) {
      animView = viewPool.get(0);
      viewPool.remove(animView);
    } else {
      animView = LayoutInflater.from(getContext()).inflate(animLayout, this, false);
    }

    if (cartView != null) {
      int[] l = new int[2];
      cartView.getLocationInWindow(l);
      cartLocation.set(l[0] - location.x, l[1] - location.y);
    } else {
      cartLocation.set(0, getMeasuredHeight());
    }

    int[] l = new int[2];
    view.getLocationInWindow(l);
    PointF startP = new PointF(l[0] - location.x, l[1] - location.y);
    ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierEvaluator(), startP, cartLocation);
    valueAnimator.addUpdateListener(animation -> {
      PointF pointF = (PointF) animation.getAnimatedValue();
      animView.setX(pointF.x);
      animView.setY(pointF.y);
    });
    valueAnimator.addListener(new Animator.AnimatorListener()
    {
      public void onAnimationStart(Animator animation)
      {
        CartAnimView.this.addView(animView);
      }

      public void onAnimationEnd(Animator animation)
      {
        CartAnimView.this.removeView(animView);
        viewPool.add(animView);
      }

      public void onAnimationCancel(Animator animation) { }

      public void onAnimationRepeat(Animator animation) { }
    });

    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.playTogether(
        ObjectAnimator.ofFloat(animView, "scaleX", 0.3f, 1f),
        ObjectAnimator.ofFloat(animView, "scaleY", 0.3f, 1f),
        valueAnimator
    );
    animatorSet.setDuration(interval);
    animatorSet.start();
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom)
  {
    super.onLayout(changed, left, top, right, bottom);
    int[] location = new int[2];
    getLocationInWindow(location);
    this.location.set(location[0], location[1]);
  }
}
