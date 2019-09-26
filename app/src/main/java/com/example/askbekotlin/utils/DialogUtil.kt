package com.example.askbekotlin.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.askbekotlin.R

object DialogUtil {
    private var progress: AlertDialog? = null

    fun showProgress(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.layout_loading_dialog)
        builder.setCancelable(false)
        progress = builder.create()
        progress!!.show()
    }

    fun dismiss() {
        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
        }
    }

    fun showSimpleOkErrorDialog(activity: Activity, title: String, message: String) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton("確認") { dialog, _ -> dialog.cancel() }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun showSimpleDialog(
        activity: Activity,
        title: String,
        message: String,
        positiveButton: String
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(positiveButton) { dialog, _ -> dialog.cancel() }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun showSimpleDialog(
        activity: Activity, title: String, message: String, button: String,
        simpleDialogInterface: SimpleDialogInterface
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(button) { dialog, _ ->
                simpleDialogInterface.positiveButtonPressed()
                dialog.cancel()
            }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun showSimpleDialog(
        activity: Activity,
        title: String,
        message: String,
        positiveButton: String,
        negativeButton: String,
        simpleDialogInterface: SimpleDialogInterface
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(positiveButton) { dialog, _ ->
                simpleDialogInterface.positiveButtonPressed()
                dialog.cancel()
            }
            .setNegativeButton(negativeButton) { dialog, _ -> dialog.cancel() }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun showSimpleDialog(
        activity: Activity,
        title: String,
        message: String,
        positiveButton: String,
        negativeButton: String,
        simpleDialogInterface: SimpleDialogInterface,
        negativeButtonPressed: NegativeButtonPressed
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(positiveButton) { dialog, _ ->
                simpleDialogInterface.positiveButtonPressed()
                dialog.cancel()
            }
            .setNegativeButton(negativeButton) { dialog, _ ->
                negativeButtonPressed.onPress()
                dialog.cancel()
            }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    interface SimpleDialogInterface {
        fun positiveButtonPressed()
    }

    interface NegativeButtonPressed {
        fun onPress()
    }

}