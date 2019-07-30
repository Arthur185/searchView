package searchcut.airr.searchview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

import searchcut.airr.stateview.StateView;

/**
 * @author wangfei
 * @date 2019/7/8.
 */
public class DataAdapter extends BaseRecyclerAdapter<SearchItem, DataAdapter.ViewHolder> {
    private HashMap<String, Class> configViewMap = new HashMap<>();

    public DataAdapter(int layout) {
        super(null, layout);
        initMap(null);
    }

    public void initMap(HashMap<String, Class> hashMap) {
        configViewMap.clear();
        configViewMap.put(SearchDataDto.HISTORY_RECORD, HistoryView.class);
        if (hashMap != null) {
            configViewMap.putAll(hashMap);
        }
    }


    @Override
    protected DataAdapter.ViewHolder createMHDViewHolder(Context mContext, View itemView, int viewType) {
        return new ViewHolder(itemView, viewType + "");
    }

    @Override
    protected void bindDate(DataAdapter.ViewHolder holder, SearchItem searchItem, int position) {
        holder.mStateView.setData(String.valueOf(getItemViewType(position)), configViewMap, searchItem.getSearchDataDto());
    }

    @Override
    protected void itemClick(Context context, SearchItem searchItem) {

    }

    @Override
    public int getItemViewType(int position) {
        if (list == null || list.size() == 0) {
            return super.getItemViewType(position);
        } else {
            try {
                return Integer.valueOf(list.get(position).getmViewTag());
            } catch (Exception e) {
                return super.getItemViewType(position);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private StateView mStateView;

        public ViewHolder(View itemView, String viewType) {
            super(itemView);
            initView(itemView, viewType);
        }

        private void initView(View itemView, String viewType) {
            mStateView = itemView.findViewById(R.id.sv_data);
        }
    }
}
