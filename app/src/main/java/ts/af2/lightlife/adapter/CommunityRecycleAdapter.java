package ts.af2.lightlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ts.af2.lightlife.R;

public class CommunityRecycleAdapter extends RecyclerView.Adapter<CommunityRecycleAdapter.ViewHolder> {

    private static final String TAG = "CommunityRecycleAdapter";
    private Context mContext;
    private List<String> dataList;
    RecyItemOnClick mRecyItemOnClick;

    public List<String> getDataList() {
        return dataList;
    }

    public void removeAllDataList() {
        this.dataList.removeAll(dataList);
    }

    public CommunityRecycleAdapter(Context context, List<String> dataList) {
        this.dataList = dataList;
        mContext = context;
    }

    public void setRecyItemOnClick(RecyItemOnClick recyItemOnClick) {
        this.mRecyItemOnClick = recyItemOnClick;
    }

    public interface RecyItemOnClick {
        public void onItemOnclick(View view, int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout articleLayout;
        public RelativeLayout userEditButons;
        public LinearLayout articleStructure1;
        public LinearLayout articleStructure2;

        public ViewHolder(View view) {
            super(view);
            articleLayout = (LinearLayout) view.findViewById(R.id.article_layout);
            articleStructure1 = (LinearLayout) view.findViewById(R.id.article_structure_1);
            articleStructure2 = (LinearLayout) view.findViewById(R.id.article_structure_2);
            userEditButons = (RelativeLayout) view.findViewById(R.id.edit_buttons);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mRecyItemOnClick != null) {
                int position = getPosition();
                mRecyItemOnClick.onItemOnclick(view, position);
            }
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.community_recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.title.setText("article");
        int position1 = position + 1;
        if (position1 % 7 == 0) {
            holder.articleStructure1.setVisibility(View.GONE);
            holder.articleStructure2.setVisibility(View.VISIBLE);
        } else {
            holder.articleStructure1.setVisibility(View.VISIBLE);
            holder.articleStructure2.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}