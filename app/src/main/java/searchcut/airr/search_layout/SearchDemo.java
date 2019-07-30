package searchcut.airr.search_layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import searchcut.airr.searchview.ICallBack;
import searchcut.airr.searchview.SearchView;



public class SearchDemo extends AppCompatActivity {

    // 1. 初始化搜索框变量
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. 绑定视图
        setContentView(R.layout.activity_search);

        // 3. 绑定组件
        searchView = (SearchView) findViewById(R.id.search_view);

        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                System.out.println("我收到了" + string);
            }
        });

        List<String> list = new ArrayList<>();
        list.add("啦啦啦啦啦啦啦哈哈哈哈哈");
        list.add("你好吗");
        list.add("啦啦哈哈");
        list.add("你");
        list.add("哈哈六六六");
        list.add("我");
        list.add("我好");
        list.add("我");
        list.add("我好");
        list.add("我");
        list.add("我好");
        list.add("我");
        list.add("我好");

        searchView.setFuzzyData(list);


        HashMap<String, Class> hashMap = new HashMap<>();
        hashMap.put("1", CustomView.class);

        searchView.setOtherView(hashMap);
    }
}