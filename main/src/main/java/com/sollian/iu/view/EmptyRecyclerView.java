package com.sollian.iu.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author sollian on 2017/10/24.
 */

public class EmptyRecyclerView extends RecyclerView {
    private final AdapterDataObserver observer = new EmptyDataObserver();
    private View vEmpty;

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context,
            @Nullable
                    AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context,
            @Nullable
                    AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        Adapter<?> a = getAdapter();
        if (a != null) {
            a.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View empty) {
        vEmpty = empty;
        checkIfEmpty();
    }

    private void checkIfEmpty() {
        Adapter<?> adapter = getAdapter();
        boolean showEmpty = adapter == null || adapter.getItemCount() == 0;

        if (vEmpty != null) {
            vEmpty.setVisibility(showEmpty ? VISIBLE : GONE);
        }
    }

    private class EmptyDataObserver extends AdapterDataObserver {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    }
}
