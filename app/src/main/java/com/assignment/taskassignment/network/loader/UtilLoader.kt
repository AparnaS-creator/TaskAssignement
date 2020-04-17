package com.assignment.taskassignment.loader

import android.content.Context
import com.assignment.taskassignment.network.loader.TransparentProgressDialog


class UtilLoader {
    lateinit var progressDialogObj: TransparentProgressDialog
     var context: Context

    constructor(context: Context) {
        this.context = context
    }


    fun startLoader(context: Context) {
        try {
            progressDialogObj = TransparentProgressDialog(context)
            progressDialogObj.show()
        } catch (e: Exception) {
        }
    }

    fun stopLoader() {
        try {
                progressDialogObj.dismiss()
        } catch (e: Exception) {
        }
    }


}