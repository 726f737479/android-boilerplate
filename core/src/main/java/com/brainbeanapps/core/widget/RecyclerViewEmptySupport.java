package com.brainbeanapps.core.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Simple RecyclerView that support empty view (displaying some UI if adapter have no data)
 */
public class RecyclerViewEmptySupport extends RecyclerView {

    private View emptyView;

    private AdapterDataObserver observer = new AdapterDataObserver() {

        @Override public void onChanged() {
            checkIfEmpty();
        }

        @Override public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public RecyclerViewEmptySupport(Context context) {
        super(context);
    }

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override public void setAdapter(Adapter adapter) {

        RecyclerView.Adapter oldAdapter = getAdapter();

        if (oldAdapter != null) oldAdapter.unregisterAdapterDataObserver(observer);
        super.setAdapter(adapter);
        if (adapter != null) adapter.registerAdapterDataObserver(observer);

        checkIfEmpty();
    }

    /**
     * Method that set empty view
     *
     * @param emptyView view that contain UI that should display if adapter have no data
     */
    public void setEmptyView(View emptyView) {

        this.emptyView = emptyView;
        checkIfEmpty();
    }

    private void checkIfEmpty() {

        if (emptyView != null && getAdapter() != null) {

            boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
        }
    }
}