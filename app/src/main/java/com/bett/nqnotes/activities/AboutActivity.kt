package com.bett.nqnotes.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.LayoutRes
import butterknife.OnClick
import com.bett.nqnotes.R

class AboutActivity : BaseActivity() {

    @LayoutRes
    override fun getLayoutResId(): Int {
        return R.layout.activity_about
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this);
    }

    @OnClick(R.id.btnBack)
    fun onClickBtnBack() {
        finish()
    }

    @OnClick(R.id.btnGitHub)
    fun onClickBtnGitHub() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_github)))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    @OnClick(R.id.txtEmail)
    fun onClickTxtEmail() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            val data = Uri.parse("mailto:betranthanh@gmail.com?subject=&body=")
            intent.data = data
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }
}
