package de.conlance.kotlinclean.core.platform

import android.content.Context
import de.conlance.kotlinclean.core.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}