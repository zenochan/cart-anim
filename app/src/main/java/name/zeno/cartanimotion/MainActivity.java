package name.zeno.cartanimotion;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import name.zeno.cartanimotion.anim.BezierEvaluator;

/**
 * @author 陈治谋 (513500085@qq.com)
 */
public class MainActivity extends AppCompatActivity implements
        SimpleAdapter.CartClickListener
{
    @Bind(R.id.list)           ListView    listView;
    @Bind(R.id.anim_container) FrameLayout animContainer;
    private                    PointF      startP, endP, baseP;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SimpleAdapter adapter = new SimpleAdapter(this);
        adapter.setListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickCart(View v)
    {
        int[] location = new int[2];
        if (endP == null)
        {
            animContainer.getLocationOnScreen(location);
            baseP = new PointF(location[0],location[1]);
            endP = new PointF();
            endP.x = 0;
            endP.y = animContainer.getMeasuredHeight();
        }

        final int viewW, viewH;
        viewW = v.getMeasuredWidth();
        viewH = v.getMeasuredHeight();
        v.getLocationOnScreen(location);
        startP = new PointF(location[0]-baseP.x, location[1]-baseP.y);

        final View    animView      = getLayoutInflater().inflate(R.layout.cart_anim, animContainer, false);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierEvaluator(), startP, endP);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF pointF = (PointF) animation.getAnimatedValue();
                animView.setX(pointF.x);
                animView.setY(pointF.y);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener()
        {
            public void onAnimationStart(Animator animation)
            {
                animContainer.addView(animView);

            }

            public void onAnimationEnd(Animator animation)
            {
                animContainer.removeView(animView);
                animView.destroyDrawingCache();
            }

            public void onAnimationCancel(Animator animation) { }

            public void onAnimationRepeat(Animator animation) { }
        });

        AnimatorSet   animatorSet   = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(animView, "scaleX", 0.3f, 1f),
                ObjectAnimator.ofFloat(animView, "scaleY", 0.3f, 1f),
                valueAnimator
        );
        animatorSet.setDuration(1300);
        animatorSet.start();

    }
}
