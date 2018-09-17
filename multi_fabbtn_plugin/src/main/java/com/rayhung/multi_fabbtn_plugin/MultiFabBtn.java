package com.rayhung.multi_fabbtn_plugin;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MultiFabBtn extends ConstraintLayout implements MovableFabCallback{

    private Context mContext;
    private View mView;

    private MultiFabBtnCallback mCallback;

    private ConstraintLayout mainClayout;
    private MovableFab mainFab;
    private FloatingActionButton fab01;
    private FloatingActionButton fab02;
    private FloatingActionButton fab03;
    private FloatingActionButton fab04;
    private FloatingActionButton fab05;

    private int viewHeight;
    private int viewWidth;

    private int multiFabBtnWidth = Math.round(TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 42,getResources().getDisplayMetrics()));
    private float fabBtnDistance = 250f;
    private float degree30x = Double.valueOf(fabBtnDistance * Math.cos(Math.toRadians(23))).floatValue();
    private float degree45x = Double.valueOf(fabBtnDistance * Math.cos(Math.toRadians(45))).floatValue();
    private float degree60x = Double.valueOf(fabBtnDistance * Math.cos(Math.toRadians(67))).floatValue();
    private float degree30y = Double.valueOf(fabBtnDistance * Math.sin(Math.toRadians(23))).floatValue();
    private float degree45y = Double.valueOf(fabBtnDistance * Math.sin(Math.toRadians(45))).floatValue();
    private float degree60y = Double.valueOf(fabBtnDistance * Math.sin(Math.toRadians(67))).floatValue();

    private boolean isFabOpen = false;

    public MultiFabBtn(Context context) {
        super(context);
        init(context);
    }

    public MultiFabBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MultiFabBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setCallbackListener(MultiFabBtnCallback mCallback){
        this.mCallback = mCallback;
    }

    public void setMultiFabBtnWidth(int multiFabBtnWidth){
        this.multiFabBtnWidth = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, multiFabBtnWidth,getResources().getDisplayMetrics()));
    }

    public void setfabBtnDistance(float fabBtnDistance){
        this.fabBtnDistance = fabBtnDistance;
    }

    public void init(Context context){
        this.mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.multifabbtn,this,true);
        mainClayout = mView.findViewById(R.id.mainClayout);
        mainFab = mView.findViewById(R.id.fab_main);
        fab01 = mView.findViewById(R.id.fab_01);
        fab02 = mView.findViewById(R.id.fab_02);
        fab03 = mView.findViewById(R.id.fab_03);
        fab04 = mView.findViewById(R.id.fab_04);
        fab05 = mView.findViewById(R.id.fab_05);
        mainFab.setCallbackListener(this);
        onClickHandler();
    }

    private void onClickHandler(){
        mainFab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isFabOpen = !isFabOpen;
                controlFabMenu(mainFab.getX(),mainFab.getY());
            }
        });
        fab01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.Fab01OnClick();
                }
            }
        });
        fab02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.Fab02OnClick();
                }
            }
        });
        fab03.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.Fab03OnClick();
                }
            }
        });
        fab04.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.Fab04OnClick();
                }
            }
        });
        fab05.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.Fab05OnClick();
                }
            }
        });
    }

    @Override
    public void callControlFabMenu(float dx, float dy) {
        controlFabMenu(dx,dy);
    }

    private void controlFabMenu(float dx, float dy){
        viewHeight = mainClayout.getHeight();
        viewWidth = mainClayout.getWidth();
        if (isFabOpen) {
            showFABMenu(viewWidth,viewHeight,dx,dy);
        }else {
            closeFABMenu();
        }
    }

    private void showFABMenu(int viewWidth,int viewHeight,float fabDownX,float fabDownY){
        if ((Math.round(fabDownX)) < viewWidth / 2) {
            //if current X < half viewWidth then the button might be in II Quadrant or III Quadrant
            if (Math.round(fabDownY) < viewHeight / 2) {
                //if current Y < half viewHeight then the button is in II Quadrant
                createIIQuadrantButtons(fabDownX, fabDownY);
            }else {
                //if current Y > half viewHeight then the button is in III Quadrant
                createIIIQuadrantButtons(fabDownX, fabDownY);
            }
        }else {
            //if current X > half viewWidth then the button might be in I Quadrant or IV Quadrant
            if (Math.round(fabDownY) < viewHeight / 2) {
                //if current Y < half viewHeight then the button is in I Quadrant
                createIQuadrantButtons(fabDownX, fabDownY);
            }else {
                //if current Y > half viewHeight then the button is in IV Quadrant
                createIVQuadrantButtons(fabDownX, fabDownY);
            }
        }
    }

    private void createIQuadrantButtons(float x, float y){
        if (fab01.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(mainClayout);
            fab01.show();
            fab02.show();
            fab03.show();
            fab04.show();
            fab05.show();
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(multiFabBtnWidth, multiFabBtnWidth);
            fab01.setLayoutParams(cLp);
            fab02.setLayoutParams(cLp);
            fab03.setLayoutParams(cLp);
            fab04.setLayoutParams(cLp);
            fab05.setLayoutParams(cLp);
        }
        fab01.setX(x- fabBtnDistance);
        fab01.setY(y);
        fab02.setX(x-degree30x);
        fab02.setY(y+degree30y);
        fab03.setX(x-degree45x);
        fab03.setY(y+degree45y);
        fab04.setX(x-degree60x);
        fab04.setY(y+degree60y);
        fab05.setX(x);
        fab05.setY(y+ fabBtnDistance);
        isFabOpen = true;
    }

    private void createIIQuadrantButtons(float x, float y){
        if (fab01.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(mainClayout);
            fab01.show();
            fab02.show();
            fab03.show();
            fab04.show();
            fab05.show();
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(multiFabBtnWidth, multiFabBtnWidth);
            fab01.setLayoutParams(cLp);
            fab02.setLayoutParams(cLp);
            fab03.setLayoutParams(cLp);
            fab04.setLayoutParams(cLp);
            fab05.setLayoutParams(cLp);
        }
        fab01.setX(x+ fabBtnDistance);
        fab01.setY(y);
        fab02.setX(x+degree30x);
        fab02.setY(y+degree30y);
        fab03.setX(x+degree45x);
        fab03.setY(y+degree45y);
        fab04.setX(x+degree60x);
        fab04.setY(y+degree60y);
        fab05.setX(x);
        fab05.setY(y+ fabBtnDistance);
        isFabOpen = true;
    }

    private void createIIIQuadrantButtons(float x, float y){
        if (fab01.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(mainClayout);
            fab01.show();
            fab02.show();
            fab03.show();
            fab04.show();
            fab05.show();
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(multiFabBtnWidth, multiFabBtnWidth);
            fab01.setLayoutParams(cLp);
            fab02.setLayoutParams(cLp);
            fab03.setLayoutParams(cLp);
            fab04.setLayoutParams(cLp);
            fab05.setLayoutParams(cLp);
        }
        fab01.setX(x);
        fab01.setY(y- fabBtnDistance);
        fab02.setX(x+degree60x);
        fab02.setY(y-degree60y);
        fab03.setX(x+degree45x);
        fab03.setY(y-degree45y);
        fab04.setX(x+degree30x);
        fab04.setY(y-degree30y);
        fab05.setX(x+ fabBtnDistance);
        fab05.setY(y);
        isFabOpen = true;
    }

    private void createIVQuadrantButtons(float x, float y){
        if (fab01.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(mainClayout);
            fab01.show();
            fab02.show();
            fab03.show();
            fab04.show();
            fab05.show();
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(multiFabBtnWidth, multiFabBtnWidth);
            fab01.setLayoutParams(cLp);
            fab02.setLayoutParams(cLp);
            fab03.setLayoutParams(cLp);
            fab04.setLayoutParams(cLp);
            fab05.setLayoutParams(cLp);
        }
        fab01.setX(x);
        fab01.setY(y- fabBtnDistance);
        fab02.setX(x-degree60x);
        fab02.setY(y-degree60y);
        fab03.setX(x-degree45x);
        fab03.setY(y-degree45y);
        fab04.setX(x-degree30x);
        fab04.setY(y-degree30y);
        fab05.setX(x- fabBtnDistance);
        fab05.setY(y);
        isFabOpen = true;
    }

    private void closeFABMenu(){
//        TransitionManager.beginDelayedTransition(mainClayout);
        if (fab01 != null) fab01.hide();
        if (fab02 != null) fab02.hide();
        if (fab03 != null) fab03.hide();
        if (fab04 != null) fab04.hide();
        if (fab05 != null) fab05.hide();
        isFabOpen = false;
    }

    public void setMainFabIcon(int ResId){
        mainFab.setImageResource(ResId);
    }

    public void setFab01Icon(int ResId){
        fab01.setImageResource(ResId);
    }

    public void setFab02Icon(int ResId){
        fab02.setImageResource(ResId);
    }

    public void setFab03Icon(int ResId){
        fab03.setImageResource(ResId);
    }

    public void setFab04Icon(int ResId){
        fab04.setImageResource(ResId);
    }

    public void setFab05Icon(int ResId){
        fab05.setImageResource(ResId);
    }

    /**
     * MainFab
     * */
    public void setMainFabElevation(float elevation){
        mainFab.setElevation(elevation);
    }

    public void setMainFabImageResource(int ResId){
        mainFab.setImageResource(ResId);
    }

    public void setMainFabBackground(int ResId){
        mainFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(ResId)));
    }

    /**
     * Fab01
     * */
    public void setFab01Elevation(float elevation){
        fab01.setElevation(elevation);
    }

    public void setFab01ImageResource(int ResId){
        fab01.setImageResource(ResId);
    }

    public void setFab01Background(int ResId){
        fab01.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(ResId)));
    }

    /**
     * Fab02
     * */
    public void setFab02Elevation(float elevation){
        fab02.setElevation(elevation);
    }

    public void setFab02ImageResource(int ResId){
        fab02.setImageResource(ResId);
    }

    public void setFab02Background(int ResId){
        fab02.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(ResId)));
    }

    /**
     * Fab03
     * */
    public void setFab03Elevation(float elevation){
        fab03.setElevation(elevation);
    }

    public void setFab03ImageResource(int ResId){
        fab03.setImageResource(ResId);
    }

    public void setFab03Background(int ResId){
        fab03.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(ResId)));
    }

    /**
     * Fab04
     * */
    public void setFab04Elevation(float elevation){
        fab04.setElevation(elevation);
    }

    public void setFab04ImageResource(int ResId){
        fab04.setImageResource(ResId);
    }

    public void setFab04Background(int ResId){
        fab04.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(ResId)));
    }

    /**
     * Fab05
     * */
    public void setFab05Elevation(float elevation){
        fab05.setElevation(elevation);
    }

    public void setFab05ImageResource(int ResId){
        fab05.setImageResource(ResId);
    }

    public void setFab05Background(int ResId){
        fab05.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(ResId)));
    }

}