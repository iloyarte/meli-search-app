package apps.iloyarte.melichallenge.utils

import android.app.Activity
import android.widget.Toast

fun Activity.toast(toast: String) {
    Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
}