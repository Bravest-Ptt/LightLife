package ts.af2.lightlife.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ts.af2.lightlife.R;
import ts.af2.lightlife.activity.AllStatusActivity;
import ts.af2.lightlife.activity.ArticleDetailsActivity;
import ts.af2.lightlife.activity.PublishStatusActivity;
import ts.af2.lightlife.activity.UserInfoActivity;
import ts.af2.lightlife.adapter.CommunityRecycleAdapter;
import ts.af2.lightlife.view.LoadMoreView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "CommunityFragment";
    private LoadMoreView mLoadMoreView;
    private int mCount = 1;
    private CommunityRecycleAdapter mCommunityRecycleAdapter;
    private static final int REFRASH_ONCE_ITEMS = 16;
    private Button mCommentButton;
    private TextView mAuthorStatus;
    private LinearLayout mAuthorInfo;

    public CommunityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoadMoreView = (LoadMoreView) view.findViewById(R.id.load_more_view);
//        mLoadMoreView.setRefreshing(true);
//        mLoadMoreView.setPullRefreshEnable(true);
//        mLoadMoreView.setPushRefreshEnable(true);
        mLoadMoreView.setFooterViewText(R.string.load_more);
        mLoadMoreView.setLinearLayout();
        mCommunityRecycleAdapter = new CommunityRecycleAdapter(getActivity(), setList());
        mLoadMoreView.setAdapter(mCommunityRecycleAdapter);
        mCommunityRecycleAdapter.setRecyItemOnClick(new CommunityRecycleAdapter.RecyItemOnClick() {
            @Override
            public void onItemOnclick(View view, int index) {
                startActivity(new Intent(getActivity(), ArticleDetailsActivity.class));
            }
        });
        mLoadMoreView.setOnLoadMoreListener(new LoadMoreListener());
        mLoadMoreView.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null));

        mCommentButton = (Button) view.findViewById(R.id.comment_btn);
        mCommentButton.setOnClickListener(this);

        mAuthorStatus = (TextView) view.findViewById(R.id.author_status);
        mAuthorStatus.setOnClickListener(this);

        mAuthorInfo = (LinearLayout) view.findViewById(R.id.author_info);
        mAuthorInfo.setOnClickListener(this);

    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCommunityRecycleAdapter.getDataList().addAll(setList());
                        mCommunityRecycleAdapter.notifyDataSetChanged();
                        mLoadMoreView.setLoadMoreCompleted();
                    }
                });

            }
        }, 1000);

    }

    private List<String> setList() {
        // TODO: 17-6-3 article struct list
        List<String> dataList = new ArrayList<>();
        int start = REFRASH_ONCE_ITEMS * (mCount - 1);
        for (int i = start; i < REFRASH_ONCE_ITEMS * mCount; i++) {
            dataList.add(Integer.toString(i));
        }
        return dataList;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.comment_btn:
                if (false) {
                    // TODO: 17-6-5 如果用户没有登录，则跳转至登录/注册界面
                } else {
                    intent.setClass(getActivity(), PublishStatusActivity.class);
                }
                break;
            case R.id.author_status:
                intent.setClass(getActivity(), AllStatusActivity.class);
                break;
            case R.id.author_info:
                intent.setClass(getActivity(), UserInfoActivity.class);
                break;
            default:

        }
        startActivity(intent);
    }

    class LoadMoreListener implements LoadMoreView.LoadMoreListener {
        @Override
        public void onRefresh() {
            Log.d(TAG, "dlj 140 LoadMoreListener onRefresh : ");
            setRefresh();
            getData();
        }

        @Override
        public void onLoadMore() {
            Log.d(TAG, "dlj 147 PullLoadMoreListener onLoadMore : ");
            mCount = mCount + 1;
            getData();
        }
    }

    private void setRefresh() {
        mCommunityRecycleAdapter.removeAllDataList();
        mCount = 1;
    }

}
