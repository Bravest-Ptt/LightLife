package ts.af2.lightlife.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import ts.af2.lightlife.R;

public class LoadMoreView extends LinearLayout {

    private final static String TAG = "LoadMoreView";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LoadMoreListener mLoadMoreListener;
    private boolean mHasMore = true;
    private boolean mIsRefresh = false;
    private boolean mIsLoadMore = false;
    private boolean mPullRefreshEnable = true;
    private boolean mPushRefreshEnable = true;
    private View mFooterView;
    private FrameLayout mEmptyViewContainer;
    private Context mContext;
    private TextView mLoadMoreText;
    private AdapterDataObserver mEmptyDataObserver;
    private SlidingLeftListener mSlidingLeftListener;

    public LoadMoreView(Context context) {
        super(context);
        initView(context);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.load_more_layout, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark,
                android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshOnRefresh());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setOnTouchListener(new onTouchRecyclerView());
        mRecyclerView.addOnScrollListener(new RecyclerViewOnScroll());

        mFooterView = view.findViewById(R.id.footerView);
        mEmptyViewContainer = (FrameLayout) view.findViewById(R.id.emptyView);
        mLoadMoreText = (TextView) view.findViewById(R.id.load_more);
        mFooterView.setVisibility(View.GONE);
        mEmptyViewContainer.setVisibility(View.GONE);

        this.addView(view);
    }

    /**
     * LinearLayoutManager
     */
    public void setLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * GridLayoutManager
     */
    public void setGridLayout(int spanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mRecyclerView.getLayoutManager();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void scrollToTop() {
        mRecyclerView.scrollToPosition(0);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
            if (mEmptyDataObserver == null) {
                mEmptyDataObserver = new AdapterDataObserver();
            }
            adapter.registerAdapterDataObserver(mEmptyDataObserver);
        }
    }

    public void showEmptyView() {
        RecyclerView.Adapter<?> adapter = mRecyclerView.getAdapter();
        if (adapter != null && mEmptyViewContainer.getChildCount() != 0) {
            if (adapter.getItemCount() == 0) {
                mFooterView.setVisibility(View.GONE);
                mEmptyViewContainer.setVisibility(VISIBLE);
            } else {
                mEmptyViewContainer.setVisibility(GONE);
            }
        }

    }

    public void setPullRefreshEnable(boolean enable) {
        mPullRefreshEnable = enable;
        setSwipeRefreshEnable(enable);
    }

    public boolean getPullRefreshEnable() {
        return mPullRefreshEnable;
    }

    public void setSwipeRefreshEnable(boolean enable) {
        mSwipeRefreshLayout.setEnabled(enable);
    }

    public boolean getSwipeRefreshEnable() {
        return mSwipeRefreshLayout.isEnabled();
    }

    public void setColorSchemeResources(int... colorResIds) {
        mSwipeRefreshLayout.setColorSchemeResources(colorResIds);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public void setRefreshing(final boolean isRefreshing) {
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                if (mPullRefreshEnable) {
                    mSwipeRefreshLayout.setRefreshing(isRefreshing);
                }
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RecyclerView.Adapter<?> adapter = mRecyclerView.getAdapter();
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(mEmptyDataObserver);
        }
    }

    public class SwipeRefreshOnRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if (!isRefresh()) {
                setIsRefresh(true);
                refresh();
            }
        }
    }

    public class RecyclerViewOnScroll extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int lastCompletelyVisibleItem = 0;
            int firstVisibleItem = 0;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int totalItemCount = layoutManager.getItemCount();
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
                lastCompletelyVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            } else if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
                lastCompletelyVisibleItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                firstVisibleItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
            }
            if (firstVisibleItem == 0 || firstVisibleItem == RecyclerView.NO_POSITION) {
                if (getPullRefreshEnable()) {
                    setSwipeRefreshEnable(true);
                }
            } else {
                setSwipeRefreshEnable(false);
            }
            if (getPushRefreshEnable() &&
                    !isRefresh()
                    && isHasMore()
                    && (lastCompletelyVisibleItem == totalItemCount - 1)
                    && !mIsLoadMore()
                    && (dx > 0 || dy > 0)) {
                setIsLoadMore(true);
                loadMore();
            }
        }
    }

    /**
     * Solve IndexOutOfBoundsException exception When loading, user can not scoll the page
     */
    public class onTouchRecyclerView implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return mIsRefresh/* || mIsLoadMore*/;
        }
    }

    private class AdapterDataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            showEmptyView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            showEmptyView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            showEmptyView();
        }
    }

    public boolean getPushRefreshEnable() {
        return mPushRefreshEnable;
    }

    public void setPushRefreshEnable(boolean pushRefreshEnable) {
        mPushRefreshEnable = pushRefreshEnable;
    }

    public void setFooterViewText(CharSequence text) {
        mLoadMoreText.setText(text);
    }

    public void setFooterViewText(int resid) {
        mLoadMoreText.setText(resid);
    }

    public void setEmptyView(View emptyView) {
        mEmptyViewContainer.removeAllViews();
        mEmptyViewContainer.addView(emptyView);
    }

    public void refresh() {
        if (mLoadMoreListener != null) {
            mLoadMoreListener.onRefresh();
        }
    }

    public void loadMore() {
        if (mLoadMoreListener != null && mHasMore) {
            mFooterView.animate()
                    .translationY(0)
                    .setDuration(300)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            mFooterView.setVisibility(View.VISIBLE);
                        }
                    })
                    .start();
            invalidate();
            mLoadMoreListener.onLoadMore();
        }
    }

    public void setLoadMoreCompleted() {
        mIsRefresh = false;
        setRefreshing(false);

        mIsLoadMore = false;
        mFooterView.animate()
                .translationY(mFooterView.getHeight())
                .setDuration(300)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    public boolean mIsLoadMore() {
        return mIsLoadMore;
    }

    public void setIsLoadMore(boolean isLoadMore) {
        mIsLoadMore = isLoadMore;
    }

    public boolean isRefresh() {
        return mIsRefresh;
    }

    public void setIsRefresh(boolean isRefresh) {
        mIsRefresh = isRefresh;
    }

    public boolean isHasMore() {
        return mHasMore;
    }

    public void setHasMore(boolean hasMore) {
        mHasMore = hasMore;
    }

    public void setOnLoadMoreListener(LoadMoreListener listener) {
        mLoadMoreListener = listener;
    }

    public interface LoadMoreListener {
        void onRefresh();

        void onLoadMore();
    }

    public void setSlidingLeftListener(SlidingLeftListener listener) {
        mSlidingLeftListener = listener;
    }

    public interface SlidingLeftListener {
        // TODO: 17-6-7 slide left to delete item. Fragment is set to slide, does this slide work?
    }
}
