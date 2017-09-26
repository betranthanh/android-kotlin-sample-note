package com.bett.nqnotes.activities

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.bett.nqnotes.MyApplication
import com.bett.nqnotes.R
import com.bett.nqtodo.di.AppComponent


/**
 * Created by bett on 9/20/17.
 */

open class BaseActivity : AppCompatActivity() {

    @LayoutRes
    protected open fun getLayoutResId(): Int {
        return R.layout.activity_base;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        ButterKnife.bind(this);
    }

    protected fun appComponent(): AppComponent {
        return (application as MyApplication).component
    }
}