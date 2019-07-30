package searchcut.airr.stateview;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.HashMap;

public abstract class BaseView<T, VB extends ViewDataBinding> implements LifecycleObserver {

    private HashMap<String, Class> viewConfigure = null;
    protected View root;
    protected VB mBinding;
    public T data;

    protected ConbinationBuilder conbinationBuilder;

    public BaseView(Context context, Object data, ViewGroup parent) {
        this.data = (T) data;
        root = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        mBinding = DataBindingUtil.bind(root);
        initViewConfigure(viewConfigure);
        initView(root, false);
        initListener(root, false);
    }

    public abstract int getLayoutId();

    protected abstract void initView(View view, boolean isUpdate);

    protected abstract void initListener(View view, boolean isUpdate);

    protected abstract void initViewConfigure(HashMap<String, Class> viewConfigure);

    public void updateData(Object data) {
        this.data = (T) data;
        initView(root, true);
        initListener(root, true);
    }

    private void handleCombination() {
        getCombinationBuild();
        handleCombinationView();
    }

    private void handleCombinationView() {
        try {
            if (null == conbinationBuilder ||
                    (null != conbinationBuilder && conbinationBuilder.combinationViewId == 0) ||
                    (null != conbinationBuilder && TextUtils.isEmpty(conbinationBuilder.getTag()))) {
                return;
            }
            ViewGroup group = root.findViewById(conbinationBuilder.getCombinationViewId());
            ConvertIntoView.convertIntoView(group, root.getContext(), conbinationBuilder.getTag(), conbinationBuilder.getViewConfigure(),data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * view的下级组合
     */
    protected abstract ConbinationBuilder combinationViewBuilder();

    public void setViewConfigure(HashMap<String, Class> viewConfigure) {
        this.viewConfigure = viewConfigure;
    }

    //获取下级组合配置
    public void getCombinationBuild() {
        conbinationBuilder = combinationViewBuilder();
        if (null != conbinationBuilder && null == conbinationBuilder.getViewConfigure()) {
            conbinationBuilder.setViewConfigure(viewConfigure);
        }
    }

    public class ConbinationBuilder {

        private HashMap<String, Class> viewConfigure = BaseView.this.viewConfigure;
        private int combinationViewId = 0;
        private String tag = "";

        public int getCombinationViewId() {
            return combinationViewId;
        }

        public String getTag() {
            return tag;
        }

        public ConbinationBuilder setCombinationViewId(int combinationViewId) {
            this.combinationViewId = combinationViewId;
            return this;
        }

        public ConbinationBuilder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public HashMap<String, Class> getViewConfigure() {
            return viewConfigure;
        }

        public ConbinationBuilder setViewConfigure(HashMap<String, Class> viewConfigure) {
            this.viewConfigure = viewConfigure;
            return this;
        }
    }

    public View getView() {
        return root;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
//        EventBus.getDefault().unregister(this);
    }
}
