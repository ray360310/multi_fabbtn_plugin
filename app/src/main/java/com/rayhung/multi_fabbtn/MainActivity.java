//package com.rayhung.multi_fabbtn;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.rayhung.multi_fabbtn_plugin.MultiFabBtn;
//import com.rayhung.multi_fabbtn_plugin.MultiFabBtnCallback;
//
//public class MainActivity extends AppCompatActivity implements MultiFabBtnCallback {
//
//    private MultiFabBtn mfab;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mfab = findViewById(R.id.mfab);
//
//        //must add
//        mfab.setCallbackListener(this);
//        //optional
//        mfab.setMultiFabBtnWidth(42);
//        mfab.setfabBtnDistance(500f);
//
//        //custom Fab function
//        mfab.setFabsTextSize(14f);
//        mfab.setFabsTextColor(android.R.color.black);
//
//        //each fab setting
////        mfab.setMainFabBackground(android.R.color.black);
////        mfab.setMainFabImageResource(R.mipmap.ic_launcher_round);
//        mfab.setMainFabElevation(0f);
//
//        mfab.setFab01Elevation(0f);
////        mfab.setFab01ImageResource(R.mipmap.ic_launcher_round);
//        mfab.setFab01Background(android.R.color.darker_gray);
//        mfab.setFab01Text("前進");
//
//
//        mfab.setFab02Elevation(0f);
////        mfab.setFab02ImageResource(R.mipmap.ic_launcher_round);
//        mfab.setFab02Background(android.R.color.darker_gray);
//        mfab.setFab02Text("刷新");
//
//        mfab.setFab03Background(android.R.color.darker_gray);
////        mfab.setFab03ImageResource(R.mipmap.ic_launcher_round);
//        mfab.setFab03Elevation(0f);
//        mfab.setFab03Text("首頁");
//
//        mfab.setFab04Background(android.R.color.darker_gray);
////        mfab.setFab04ImageResource(R.mipmap.ic_launcher_round);
//        mfab.setFab04Elevation(0f);
//        mfab.setFab04Text("清除緩存");
//
//        mfab.setFab05Background(android.R.color.darker_gray);
////        mfab.setFab05ImageResource(R.mipmap.ic_launcher_round)
//        mfab.setFab05Elevation(0f);
//        mfab.setFab05Text("後退");
//
//
//
//    }
//
//    @Override
//    public void Fab01OnClick() {
//        Toast.makeText(MainActivity.this,"前進",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void Fab02OnClick() {
//        Toast.makeText(MainActivity.this,"刷新",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void Fab03OnClick() {
//        Toast.makeText(MainActivity.this,"首頁",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void Fab04OnClick() {
//        Toast.makeText(MainActivity.this,"清除緩存",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void Fab05OnClick() {
//        Toast.makeText(MainActivity.this,"後退",Toast.LENGTH_SHORT).show();
//    }
//}
