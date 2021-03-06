package com.test.projectv4.RecyclerView.Helper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.test.projectv4.RecyclerView.Adapter.CheckLotteryAdapter;

public class CheckLotteryHelper extends ItemTouchHelper.SimpleCallback{
    private CheckLotteryHelperListener listener;

    public CheckLotteryHelper(int drag, int swipe, CheckLotteryHelperListener listener){
        super(drag, swipe);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder != null){
            final View foregroundView = ((CheckLotteryAdapter.MyViewHolder) viewHolder).pForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView
            , RecyclerView.ViewHolder viewHolder, float dX, float dY
            , int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((CheckLotteryAdapter.MyViewHolder) viewHolder).pForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);

    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((CheckLotteryAdapter.MyViewHolder) viewHolder).pForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((CheckLotteryAdapter.MyViewHolder) viewHolder).pForeground;

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface CheckLotteryHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
