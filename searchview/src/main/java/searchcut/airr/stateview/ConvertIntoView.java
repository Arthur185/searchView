package searchcut.airr.stateview;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ConvertIntoView {

    private static Map<String, Class> viewConfigure = new HashMap<>();

    protected static void setViewConfigure(Map<String, Class> viewConfigure) {
        ConvertIntoView.viewConfigure = viewConfigure;
    }

    public static BaseView convertIntoView(Context context, String tag) {
        return convertIntoView(context, null, tag, viewConfigure);
    }

    public static BaseView convertIntoView(ViewGroup group, Context context, String tag) {
        return convertIntoView(group, context, tag, viewConfigure);
    }

    public static BaseView convertIntoView(ViewGroup group, Context context, String tag, Map<String, Class> viewConfigure) {
        return convertIntoView(group, context, tag, viewConfigure, null);
    }

    /**
     * 将级联布局放到父布局中去
     * @param group
     * @param context
     * @param tag
     * @param viewConfigure
     * @param data
     * @return
     */
    public static BaseView convertIntoView(ViewGroup group, Context context, String tag, Map<String, Class> viewConfigure, Object data) {
        if (null == group) {
            return null;
        }
        //移除所有子view
        group.removeAllViews();
        BaseView baseView = convertIntoView(context, group, tag, viewConfigure,data);
        group.addView(baseView.getView());
        return baseView;
    }

    public static BaseView convertIntoView(Context context,ViewGroup group, String tag, Map<String, Class> viewConfigure) {
        return convertIntoView(context, group, tag, viewConfigure, null);
    }

    public static BaseView convertIntoView(Context context,ViewGroup group, String tag, Map<String, Class> viewConfigure, Object data) {
        if (null == viewConfigure) {
            throw new IllegalArgumentException("必须初始化viewConfigure");
        }

        BaseView view = null;
        try {
            //反射需要的BaseView子类，并初始化
            Class aClass = viewConfigure.get(tag);
            Constructor constructor = aClass.getConstructor(Context.class, Object.class, ViewGroup.class);
            view = (BaseView) constructor.newInstance(context, data, group);
            if (context instanceof FragmentActivity){//添加生命周期监听
                ((FragmentActivity)context).getLifecycle().addObserver(view);
            }
            Field field = aClass.getSuperclass().getDeclaredField("viewConfigure");
            field.setAccessible(true);
            field.set(view,viewConfigure);
            Method method = aClass.getSuperclass().getDeclaredMethod("handleCombination");
            method.setAccessible(true);
            method.invoke(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == view) {
            return null;
        }
        return view;
    }
}
