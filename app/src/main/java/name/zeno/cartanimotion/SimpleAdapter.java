package name.zeno.cartanimotion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 陈治谋 (513500085@qq.com)
 */
class SimpleAdapter extends BaseAdapter
{
    public interface CartClickListener
    {
        void onClickCart(View v);
    }

    private Context           context;
    private LayoutInflater    inflater;
    private CartClickListener listener;


    public SimpleAdapter(Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public CartClickListener getListener()
    {
        return listener;
    }

    public void setListener(CartClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public int getCount()
    {
        return 30;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_goods, parent, false);
            ImageView cartIv = (ImageView) convertView.findViewById(R.id.iv_cart);
            cartIv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (listener != null)
                        listener.onClickCart(v);
                }
            });
        }
        return convertView;
    }
}

