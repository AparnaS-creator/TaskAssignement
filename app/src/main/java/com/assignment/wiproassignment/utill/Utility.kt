@file:Suppress("DEPRECATION")

package com.assignment.wiproassignment.utill

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.net.Uri
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.assignment.wiproassignment.R
import com.squareup.picasso.Picasso
import org.json.JSONObject

/**
 * Created by Aparna S.
 * it contain all the common methods that are uses as per requirement
 */

/**
 * show toast
 */
fun showToast(mContext: Context, message: String, duration: Int) {
    try {
        val toast = Toast.makeText(mContext, message, duration)
        val view = toast.view
        val text = view.findViewById<TextView>(android.R.id.message)
        view.background.setColorFilter(
            Color.parseColor("#19769f"),
            PorterDuff.Mode.SRC_IN
        );
        text.setTextColor(Color.WHITE)
        text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_info_icon, 0, 0, 0)
        text.compoundDrawablePadding = 10
        toast.setGravity(Gravity.BOTTOM, 40, 40)
        toast.show()
    } catch (e: Exception) {
        e.printStackTrace()
        try {
            val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.BOTTOM, 40, 40)
            toast.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}



/**
 *  display Image
 */
fun showImage(imageView: ImageView, pic: String, placeholder: Int) {
    try {
        Picasso.get()
            .load(pic)
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    } catch (e: Exception) {
        e.printStackTrace()
        imageView.setImageResource(placeholder)
    }
}










