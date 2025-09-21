package `in`.surojit.seekhoassignmentapp.ui.components

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtils {

    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val n = cm.activeNetwork ?: return false
        val cap = cm.getNetworkCapabilities(n) ?: return false
        return when {
            cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            cap.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}