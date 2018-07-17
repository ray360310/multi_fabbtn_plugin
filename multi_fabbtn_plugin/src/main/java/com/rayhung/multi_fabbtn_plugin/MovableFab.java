package com.rayhung.multi_fabbtn_plugin;


import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MovableFab extends FloatingActionButton implements View.OnTouchListener {

    private final static float CLICK_DRAG_TOLERANCE = 10; // Often, there will be a slight, unintentional, drag when the user taps the FAB, so we need to account for this.

    private GestureDetector mGestureDetector;
    private Context mContext;
    private MovableFabCallback mCallback;

    private float downRawX, downRawY;
    private float dX, dY;

    public MovableFab(Context context) {
        super(context);
        init(context);
    }

    public MovableFab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MovableFab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext){
        this.mContext = mContext;
        mGestureDetector = new GestureDetector(mContext,new SingleTapConfirm());
        setOnTouchListener(this);
    }

    public void setCallbackListener(MovableFabCallback mCallback){
        this.mCallback = mCallback;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        //Main View (The Entire View)
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();

        //Object View (FAB View)
        View viewParent = (View)view.getParent();
        int parentWidth = viewParent.getWidth();
        int parentHeight = viewParent.getHeight();

//        if (mGestureDetector.onTouchEvent(motionEvent)) {
//            float currentDownX = view.getX() - motionEvent.getRawX();
//            float currentDownY = view.getY() - motionEvent.getRawY();
//            if (!isFABOpen) {
//                showFABMenu(viewWidth,viewHeight,currentDownX,currentDownY);
//            }else {
//                closeFABMenu(viewWidth,viewHeight,currentDownX,currentDownY);
//            }
//            return true;
//        }
        if (action == MotionEvent.ACTION_DOWN) {

            downRawX = motionEvent.getRawX();
            downRawY = motionEvent.getRawY();
            dX = view.getX() - downRawX;
            dY = view.getY() - downRawY;

            return true; // Consumed

        }
        else if (action == MotionEvent.ACTION_MOVE) {

            float newX = motionEvent.getRawX() + dX;
            newX = Math.max(0, newX); // Don't allow the FAB past the left hand side of the parent
            newX = Math.min(parentWidth - viewWidth, newX); // Don't allow the FAB past the right hand side of the parent

            float newY = motionEvent.getRawY() + dY;
            newY = Math.max(0, newY); // Don't allow the FAB past the top of the parent
            newY = Math.min(parentHeight - viewHeight, newY); // Don't allow the FAB past the bottom of the parent

            view.animate()
                    .x(newX)
                    .y(newY)
                    .setDuration(0)
                    .start();
            mCallback.callControlFabMenu(newX,newY);
            return true; // Consumed

        }
        else if (action == MotionEvent.ACTION_UP) {

            float upRawX = motionEvent.getRawX();
            float upRawY = motionEvent.getRawY();

            float upDX = upRawX - downRawX;
            float upDY = upRawY - downRawY;

            if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE) { // A click
                return performClick();
            }
            else { // A drag
                return true; // Consumed
            }

        }
        else {
            return super.onTouchEvent(motionEvent);
        }
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    }

}