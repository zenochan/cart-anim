package zeno.name.animcart;

import android.graphics.PointF;
import android.support.annotation.FloatRange;

/**
 * @author 陈治谋 (513500085@qq.com)
 */
@SuppressWarnings("unused")
public class BezierCurve
{
  /**
   * 二次贝塞尔曲线插值
   */
  public static PointF bezier(@FloatRange(from = 0, to = 1) float t, PointF point0, PointF point1, PointF point2)
  {
    float oneMinusT = 1.0f - t;
    PointF point = new PointF();
    point.x = oneMinusT * oneMinusT * point0.x
        + 2 * t * oneMinusT * point1.x
        + t * t * point2.x;
    point.y = oneMinusT * oneMinusT * point0.y
        + 2 * t * oneMinusT * point1.y
        + t * t * point2.y;
    return point;
  }

  /**
   * 三次贝塞尔曲线插值
   */
  public static PointF bezier(@FloatRange(from = 0, to = 1) float t, PointF point0, PointF point1, PointF point2, PointF point3)
  {
    float oneMinusT = 1.0f - t;
    PointF point = new PointF();
    point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
        + 3 * oneMinusT * oneMinusT * t * (point1.x)
        + 3 * oneMinusT * t * t * (point2.x)
        + t * t * t * (point3.x);

    point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
        + 3 * oneMinusT * oneMinusT * t * (point1.y)
        + 3 * oneMinusT * t * t * (point2.y)
        + t * t * t * (point3.y);
    return point;
  }
}
