package de.conlance.kotlinclean.features.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.platform.BaseActivity

class LoginActivity :BaseActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

}
