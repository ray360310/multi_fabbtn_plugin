package com.rayhung.multi_fabbtn_plugin;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MultiFabBtn extends ConstraintLayout implements MovableFabCallback{

    private Context mContext;
    private View mView;

    private ConstraintLayout mainClayout;
    private MovableFab mainFab;
    private FloatingActionButton fab01;
    private FloatingActionButton fab02;
    private FloatingActionButton fab03;
    private FloatingActionButton fab04;
    private FloatingActionButton fab05;

    private int viewHeight;
    private int viewWidth;

    private int fabBtnWidth = 75;
    private float btnDistance = 250f;
    private float degree30x = Double.valueOf(btnDistance * Math.cos(Math.toRadians(23))).floatValue();
    private float degree45x = Double.valueOf(btnDistance * Math.cos(Math.toRadians(45))).floatValue();
    private float degree60x = Double.valueOf(btnDistance * Math.cos(Math.toRadians(67))).floatValue();
    private float degree30y = Double.valueOf(btnDistance * Math.sin(Math.toRadians(23))).floatValue();
    private float degree45y = Double.valueOf(btnDistance * Math.sin(Math.toRadians(45))).floatValue();
    private float degree60y = Double.valueOf(btnDistance * Math.sin(Math.toRadians(67))).floatValue();

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
                Toast.makeText(getContext(),"fab01",Toast.LENGTH_SHORT).show();
            }
        });
        fab02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"fab02",Toast.LENGTH_SHORT).show();
            }
        });
        fab03.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"fab03",Toast.LENGTH_SHORT).show();
            }
        });
        fab04.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"fab04",Toast.LENGTH_SHORT).show();
            }
        });
        fab05.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"fab05",Toast.LENGTH_SHORT).show();
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
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(fabBtnWidth,fabBtnWidth);
            fab01.setLayoutParams(cLp);
            fab02.setLayoutParams(cLp);
            fab03.setLayoutParams(cLp);
            fab04.setLayoutParams(cLp);
            fab05.setLayoutParams(cLp);
        }
        fab01.setX(x-btnDistance);
        fab01.setY(y);
        fab02.setX(x-degree30x);
        fab02.setY(y+degree30y);
        fab03.setX(x-degree45x);
        fab03.setY(y+degree45y);
        fab04.setX(x-degree60x);
        fab04.setY(y+degree60y);
        fab05.setX(x);
        fab05.setY(y+btnDistance);
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
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(fabBtnWidth,fabBtnWidth);
            fab01.setLayoutParams(cLp);
            fab02.setLayoutParams(cLp);
            fab03.setLayoutParams(cLp);
            fab04.setLayoutParams(cLp);
            fab05.setLayoutParams(cLp);
        }
        fab01.setX(x+btnDistance);
        fab01.setY(y);
        fab02.setX(x+degree30x);
        fab02.setY(y+degree30y);
        fab03.setX(x+degree45x);
        fab03.setY(y+degree45y);
        fab04.setX(x+degree60x);
        fab04.setY(y+degree60y);
        fab05.setX(x);
        fab05.setY(y+btnDistance);
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
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(fabBtnWidth,fabBtnWidth);
            fab01.setLayoutParams(cLp);
            fab02.setLayoutParams(cLp);
            fab03.setLayoutParams(cLp);
            fab04.setLayoutParams(cLp);
            fab05.setLayoutParams(cLp);
        }
        fab01.setX(x);
        fab01.setY(y-btnDistance);
        fab02.setX(x+degree30x);
        fab02.setY(y-degree30y);
        fab03.setX(x+degree45x);
        fab03.setY(y-degree45y);
        fab04.setX(x+degree60x);
        fab04.setY(y-degree60y);
        fab05.setX(x+btnDistance);
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
            ConstraintLayout.LayoutParams cLp = new ConstraintLayout.LayoutParams(fabBtnWidth,fabBtnWidth);
            fab01.setLayoutParams(cLp);
            fab02.setLayoutParams(cLp);
            fab03.setLayoutParams(cLp);
            fab04.setLayoutParams(cLp);
            fab05.setLayoutParams(cLp);
        }
        fab01.setX(x);
        fab01.setY(y-btnDistance);
        fab02.setX(x-degree30x);
        fab02.setY(y-degree30y);
        fab03.setX(x-degree45x);
        fab03.setY(y-degree45y);
        fab04.setX(x-degree60x);
        fab04.setY(y-degree60y);
        fab05.setX(x-btnDistance);
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

}