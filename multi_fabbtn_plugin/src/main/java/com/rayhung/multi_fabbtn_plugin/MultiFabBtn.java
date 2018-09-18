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
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MultiFabBtn extends ConstraintLayout implements MovableFabCallback{

    private Context mContext;
    private View mView;

    private MultiFabBtnCallback mCallback;

    private ConstraintLayout mainClayout;
    private FrameLayout fab01FLayout;
    private FrameLayout fab02FLayout;
    private FrameLayout fab03FLayout;
    private FrameLayout fab04FLayout;
    private FrameLayout fab05FLayout;
    private MovableFab mainFab;
    private FloatingActionButton fab01;
    private FloatingActionButton fab02;
    private FloatingActionButton fab03;
    private FloatingActionButton fab04;
    private FloatingActionButton fab05;
    private TextView fab01Txw;
    private TextView fab02Txw;
    private TextView fab03Txw;
    private TextView fab04Txw;
    private TextView fab05Txw;

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
        fab01FLayout = mView.findViewById(R.id.fab01flayout);
        fab02FLayout = mView.findViewById(R.id.fab02flayout);
        fab03FLayout = mView.findViewById(R.id.fab03flayout);
        fab04FLayout = mView.findViewById(R.id.fab04flayout);
        fab05FLayout = mView.findViewById(R.id.fab05flayout);
        fab01 = mView.findViewById(R.id.fab_01);
        fab02 = mView.findViewById(R.id.fab_02);
        fab03 = mView.findViewById(R.id.fab_03);
        fab04 = mView.findViewById(R.id.fab_04);
        fab05 = mView.findViewById(R.id.fab_05);
        fab01Txw = mView.findViewById(R.id.fab01txw);
        fab02Txw = mView.findViewById(R.id.fab02txw);
        fab03Txw = mView.findViewById(R.id.fab03txw);
        fab04Txw = mView.findViewById(R.id.fab04txw);
        fab05Txw = mView.findViewById(R.id.fab05txw);

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
        if (fab01FLayout.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(mainClayout);
            fab01FLayout.setVisibility(VISIBLE);
            fab02FLayout.setVisibility(VISIBLE);
            fab03FLayout.setVisibility(VISIBLE);
            fab04FLayout.setVisibility(VISIBLE);
            fab05FLayout.setVisibility(VISIBLE);
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(multiFabBtnWidth, multiFabBtnWidth);
            fab01FLayout.setLayoutParams(cLp);
            fab02FLayout.setLayoutParams(cLp);
            fab03FLayout.setLayoutParams(cLp);
            fab04FLayout.setLayoutParams(cLp);
            fab05FLayout.setLayoutParams(cLp);
        }
        fab01FLayout.setX(x- fabBtnDistance);
        fab01FLayout.setY(y);
        fab02FLayout.setX(x-degree30x);
        fab02FLayout.setY(y+degree30y);
        fab03FLayout.setX(x-degree45x);
        fab03FLayout.setY(y+degree45y);
        fab04FLayout.setX(x-degree60x);
        fab04FLayout.setY(y+degree60y);
        fab05FLayout.setX(x);
        fab05FLayout.setY(y+ fabBtnDistance);
        isFabOpen = true;
    }

    private void createIIQuadrantButtons(float x, float y){
        if (fab01FLayout.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(mainClayout);
            fab01FLayout.setVisibility(VISIBLE);
            fab02FLayout.setVisibility(VISIBLE);
            fab03FLayout.setVisibility(VISIBLE);
            fab04FLayout.setVisibility(VISIBLE);
            fab05FLayout.setVisibility(VISIBLE);
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(multiFabBtnWidth, multiFabBtnWidth);
            fab01FLayout.setLayoutParams(cLp);
            fab02FLayout.setLayoutParams(cLp);
            fab03FLayout.setLayoutParams(cLp);
            fab04FLayout.setLayoutParams(cLp);
            fab05FLayout.setLayoutParams(cLp);
        }
        fab01FLayout.setX(x+ fabBtnDistance);
        fab01FLayout.setY(y);
        fab02FLayout.setX(x+degree30x);
        fab02FLayout.setY(y+degree30y);
        fab03FLayout.setX(x+degree45x);
        fab03FLayout.setY(y+degree45y);
        fab04FLayout.setX(x+degree60x);
        fab04FLayout.setY(y+degree60y);
        fab05FLayout.setX(x);
        fab05FLayout.setY(y+ fabBtnDistance);
        isFabOpen = true;
    }

    private void createIIIQuadrantButtons(float x, float y){
        if (fab01FLayout.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(mainClayout);
            fab01FLayout.setVisibility(VISIBLE);
            fab02FLayout.setVisibility(VISIBLE);
            fab03FLayout.setVisibility(VISIBLE);
            fab04FLayout.setVisibility(VISIBLE);
            fab05FLayout.setVisibility(VISIBLE);
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(multiFabBtnWidth, multiFabBtnWidth);
            fab01FLayout.setLayoutParams(cLp);
            fab02FLayout.setLayoutParams(cLp);
            fab03FLayout.setLayoutParams(cLp);
            fab04FLayout.setLayoutParams(cLp);
            fab05FLayout.setLayoutParams(cLp);
        }
        fab01FLayout.setX(x);
        fab01FLayout.setY(y- fabBtnDistance);
        fab02FLayout.setX(x+degree60x);
        fab02FLayout.setY(y-degree60y);
        fab03FLayout.setX(x+degree45x);
        fab03FLayout.setY(y-degree45y);
        fab04FLayout.setX(x+degree30x);
        fab04FLayout.setY(y-degree30y);
        fab05FLayout.setX(x+ fabBtnDistance);
        fab05FLayout.setY(y);
        isFabOpen = true;
    }

    private void createIVQuadrantButtons(float x, float y){
        if (fab01FLayout.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(mainClayout);
            fab01FLayout.setVisibility(VISIBLE);
            fab02FLayout.setVisibility(VISIBLE);
            fab03FLayout.setVisibility(VISIBLE);
            fab04FLayout.setVisibility(VISIBLE);
            fab05FLayout.setVisibility(VISIBLE);
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(multiFabBtnWidth, multiFabBtnWidth);
            fab01FLayout.setLayoutParams(cLp);
            fab02FLayout.setLayoutParams(cLp);
            fab03FLayout.setLayoutParams(cLp);
            fab04FLayout.setLayoutParams(cLp);
            fab05FLayout.setLayoutParams(cLp);
        }
        fab01FLayout.setX(x);
        fab01FLayout.setY(y- fabBtnDistance);
        fab02FLayout.setX(x-degree60x);
        fab02FLayout.setY(y-degree60y);
        fab03FLayout.setX(x-degree45x);
        fab03FLayout.setY(y-degree45y);
        fab04FLayout.setX(x-degree30x);
        fab04FLayout.setY(y-degree30y);
        fab05FLayout.setX(x- fabBtnDistance);
        fab05FLayout.setY(y);
        isFabOpen = true;
    }

    private void closeFABMenu(){
//        TransitionManager.beginDelayedTransition(mainClayout);
        if (fab01FLayout != null) fab01FLayout.setVisibility(GONE);
        if (fab02FLayout != null) fab02FLayout.setVisibility(GONE);
        if (fab03FLayout != null) fab03FLayout.setVisibility(GONE);
        if (fab04FLayout != null) fab04FLayout.setVisibility(GONE);
        if (fab05FLayout != null) fab05FLayout.setVisibility(GONE);
        isFabOpen = false;
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

    public void setFabsTextSize(float textSizeSP){
        fab01Txw.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSizeSP);
        fab02Txw.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSizeSP);
        fab03Txw.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSizeSP);
        fab04Txw.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSizeSP);
        fab05Txw.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSizeSP);
    }

    public void setFabsTextColor(int ResID){
        fab01Txw.setTextColor(ResID);
        fab02Txw.setTextColor(ResID);
        fab03Txw.setTextColor(ResID);
        fab04Txw.setTextColor(ResID);
        fab05Txw.setTextColor(ResID);
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

    public void setFab01Text(String inputStr){
        fab01Txw.setText(inputStr);
    }

    public void setFab01TextColor(int ResID){
        fab01Txw.setTextColor(getResources().getColor(ResID));
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

    public void setFab02Text(String inputStr){
        fab02Txw.setText(inputStr);
    }

    public void setFab02TextColor(int ResID){
        fab02Txw.setTextColor(getResources().getColor(ResID));
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

    public void setFab03Text(String inputStr){
        fab03Txw.setText(inputStr);
    }

    public void setFab03TextColor(int ResID){
        fab03Txw.setTextColor(getResources().getColor(ResID));
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

    public void setFab04Text(String inputStr){
        fab04Txw.setText(inputStr);
    }

    public void setFab04TextColor(int ResID){
        fab04Txw.setTextColor(getResources().getColor(ResID));
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

    public void setFab05Text(String inputStr){
        fab05Txw.setText(inputStr);
    }

    public void setFab05TextColor(int ResID){
        fab05Txw.setTextColor(getResources().getColor(ResID));
    }

}