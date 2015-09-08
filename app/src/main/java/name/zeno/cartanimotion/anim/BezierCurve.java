package name.zeno.cartanimotion.anim;

import android.graphics.PointF;

/**
 * @author 陈治谋 (513500085@qq.com)
 */
public class BezierCurve
{
    public static PointF bezier(float t, PointF point0, PointF point1, PointF point2)
    {
        if (t < 0 || t > 1) {
            throw new IllegalArgumentException("t must between 0 and 1");
        }

        float  oneMinusT = 1.0f - t;
        PointF point     = new PointF();
        point.x = oneMinusT * oneMinusT * point0.x
                + 2 * t * oneMinusT * point1.x
                + t * t * point2.x;
        point.y = oneMinusT * oneMinusT * point0.y
                + 2 * t * oneMinusT * point1.y
                + t * t * point2.y;
        return point;
    }

    public static PointF bezier(float t, PointF point0, PointF point1, PointF point2, PointF point3)
    {
        if (t < 0 || t > 1) {
            throw new IllegalArgumentException("t must between 0 and 1");
        }

        float  oneMinusT = 1.0f - t;
        PointF point     = new PointF();
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
