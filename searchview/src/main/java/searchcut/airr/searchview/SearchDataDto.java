package searchcut.airr.searchview;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangfei
 * @date 2019/7/24.
 */
public class SearchDataDto {
    public static String HISTORY_RECORD = "0";

    private List<SearchItem> datas = new ArrayList<>();

    private String title;
    private List<String> data;

    private View.OnClickListener onClickListener = null;
    private ICallBack onCallBackListener = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ICallBack getOnCallBackListener() {
        return onCallBackListener;
    }

    public void setOnCallBackListener(ICallBack onCallBackListener) {
        this.onCallBackListener = onCallBackListener;
    }

    public List<SearchItem> getViews(List<SearchItem> list) {
        datas.clear();
        datas.add(new SearchItem(HISTORY_RECORD, this));
        if (list != null && list.size() != 0) {
            datas.addAll(list);
        }
        return datas;
    }

}
