package de.conlance.kotlinclean.core.platform

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.extension.inTransaction

import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
