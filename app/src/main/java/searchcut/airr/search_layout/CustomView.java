package searchcut.airr.search_layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import searchcut.airr.search_layout.databinding.ViewCustomBinding;
import searchcut.airr.stateview.BaseView;

/**
 * @author wangfei
 * @date 2019/7/30.
 */
public class CustomView extends BaseView<Object, ViewCustomBinding> {
    public CustomView(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_custom;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {

    }

    @Override
    protected void initListener(View view, boolean isUpdate) {

    }

    @Override
    protected ConbinationBuilder combinationViewBuilder() {
        return null;
    }

    @Override
    protected void initViewConfigure(HashMap viewConfigure) {

    }
}
