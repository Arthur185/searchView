package searchcut.airr.searchview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 13660 on 2018/3/23.
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private View convertView;
    public Context mContext;

    public BaseRecyclerViewHolder(Context mContext, View itemView) {
        super(itemView);
        convertView = itemView;
        this.mContext = mContext;
    }

    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }


    protected <T extends View> T retrieveView(int viewId) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (null == viewHolder) {
            viewHolder = new SparseArray<>();
            convertView.setTag(viewHolder);
        }
        View childView = viewHolder.get(viewId);
        if (null == childView) {
            childView = convertView.findViewById(viewId);
            viewHolder.put(viewId, childView);

        }
        return (T) childView;
    }

   public  void setTextView(int layoutId, String value) {
        TextView tv = getView(layoutId);
        tv.setText(value);
    }

    public void setTextViewColor(int layoutId, String value, int color) {
        TextView tv = getView(layoutId);
       // tv.setTextColor(UILApplication.getInstance().getResources().getColor(color));
        tv.setText(value);
    }

   /* public void setImageView(int layoutId, String url) {
        ImageView iv = getView(layoutId);
        Glide.with(mContext).load(url).fitCenter().into(iv);
    }*/

    public void setImageView(int layoutId, int redId) {
        ImageView iv = getView(layoutId);
        iv.setImageResource(redId);
    }

    public View getConvertView() {
        return convertView;
    }

}
