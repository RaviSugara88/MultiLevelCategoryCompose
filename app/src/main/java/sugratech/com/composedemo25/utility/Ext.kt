package sugratech.com.composedemo25.utility

import android.content.Context
import android.view.View

/*
fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun log(tag: String, message: String) {
    if (BuildConfig.DEBUG) Log.d(tag, message)
}
*/

fun calculateSpanCount(context: Context): Int {
    val screenWidthDp = context.resources.configuration.screenWidthDp
    return when {
        screenWidthDp >= 900 -> 4
        screenWidthDp >= 600 -> 3
        else -> 2
    }
}
