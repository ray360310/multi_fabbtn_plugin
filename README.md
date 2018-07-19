# Multi-fabbtn-plugin
A multi-FloatingActionButton plugin for Android Applications.


## Download

Download this by Gradle

in Project/build.gradle add this in allprojects{reposities{}}

> maven { url 'https://jitpack.io' }

in Project/module/build.gradle add this in dependencies{}

> implementation 'com.github.ray360310:multi_fabbtn_plugin:-SNAPSHOT'

## Usage

First, add MultiFabBtn to your xml view

    <com.rayhung.multi_fabbtn_plugin.MultiFabBtn
        android:id="@+id/mfab"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">
        
Then in your Activity/Fragment, implement MultiFabBtnCallback to active FloatingActionButton onClick event.

Add some Function:

        //must add
        mfab.setCallbackListener(this)
        //optional
        mfab.setMultiFabBtnWidth(42)
        mfab.setfabBtnDistance(250f)
        mfab.setMainFabBackground(android.R.color.black)
        mfab.setMainFabIcon(R.mipmap.ic_launcher_round)
        mfab.setFab01Background(android.R.color.holo_orange_light)
        mfab.setFab01Icon(R.mipmap.ic_launcher_round)
        mfab.setFab02Background(android.R.color.holo_green_light)
        mfab.setFab02Icon(R.mipmap.ic_launcher_round)
        mfab.setFab03Background(android.R.color.holo_green_dark)
        mfab.setFab03Icon(R.mipmap.ic_launcher_round)
        mfab.setFab04Background(android.R.color.holo_blue_dark)
        mfab.setFab04Icon(R.mipmap.ic_launcher_round)
        mfab.setFab05Background(android.R.color.holo_blue_bright)
        mfab.setFab05Icon(R.mipmap.ic_launcher_round)

Done.