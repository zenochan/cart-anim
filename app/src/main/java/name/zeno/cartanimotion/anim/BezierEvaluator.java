package name.zeno.cartanimotion.anim;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * @author 陈治谋 (513500085@qq.com)
 */
public class BezierEvaluator implements TypeEvaluator<PointF>
{
    @Override
    public PointF evaluate(float fraction, PointF startValue,
                           PointF endValue)
    {
        PointF point1 = new PointF((startValue.x + endValue.x) / 2,0);
        return BezierCurve.bezier(fraction, startValue, point1, endValue);
    }
}

