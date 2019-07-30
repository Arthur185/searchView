package searchcut.airr.stateview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

public class StateView extends FrameLayout {

    private String tag;
    private BaseView baseView;
    private HashMap<String, BaseView> viewMap = new HashMap<>();
    private boolean isCacheView = true;

    public StateView(@NonNull Context context) {
        this(context, null);
    }

    public StateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(String tag, Map<String, Class> map) {
        setData(tag, map, null);
    }

    public boolean isCacheView() {
        return isCacheView;
    }

    public void setCacheView(boolean cacheView) {
        isCacheView = cacheView;
    }

    public void setData(String tag, Map<String, Class> map, Object data) {
        if (isCacheView && null != viewMap.get(tag)) {//获取缓存中的baseview
            baseView = viewMap.get(tag);
            this.removeAllViews();
            this.addView(baseView.getView());
            baseView.updateData(data);
            return;
        }
        if (!isCacheView && !TextUtils.isEmpty(this.tag) && this.tag.equalsIgnoreCase(tag) && null != baseView) {//是原来的布局
            this.removeAllViews();
            this.addView(baseView.getView());
            baseView.updateData(data);
            return;
        }

        //非原来布局处理
        this.tag = tag;
        baseView = ConvertIntoView.convertIntoView(this, getContext(), tag, map, data);
        if (isCacheView) {//缓存baseview
            viewMap.put(tag, baseView);
        }
    }
}
