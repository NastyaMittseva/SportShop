package com.example.shop.ui

import android.os.Bundle
import android.util.Log
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity() {
    val tag = this.javaClass.simpleName

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The activity is being created.
        Log.d(tag, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        // The activity is about to become visible.
        Log.d(tag, "onStart")

    }

    override fun onResume() {
        super.onResume()
        // The activity has become visible (it is now "resumed").
        Log.d(tag, "onResume")

    }

    override fun onPause() {
        super.onPause()
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d(tag, "onPause")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onStop() {
        super.onStop()
        // The activity is no longer visible (it is now "stopped")
        Log.d(tag, "onStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        // The activity is about to be destroyed.
        Log.d(tag, "onDestroy")
    }
}