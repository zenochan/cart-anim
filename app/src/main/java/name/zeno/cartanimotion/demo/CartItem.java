package name.zeno.cartanimotion.demo;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kale.adapter.item.AdapterItem;
import lombok.Setter;

/**
 * @author 陈治谋 (513500085@qq.com)
 * @since 16/8/12
 */
public class CartItem implements AdapterItem
{
  @BindView(R.id.iv_cart) ImageView ivCart;

  @Setter private Action1<View> onClickCartListener;

  @Override public void bindViews(View view)
  {
    ButterKnife.bind(this, view);
  }

  @Override public int getLayoutResId()
  {
    return R.layout.item_cart;
  }

  @Override public void setViews()
  {

  }

  @Override public void handleData(Object o, int i)
  {

  }

  @OnClick(R.id.iv_cart) public void onClick(View v)
  {
    if (onClickCartListener != null) {
      onClickCartListener.call(v);
    }
  }
}
