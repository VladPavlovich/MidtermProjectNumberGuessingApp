package com.example.midtermproject

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class DeleteConfirmationDialogFragment(private val positiveCallback: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            //sets message to be displayed
            .setMessage("Are you sure you want to delete?")
            .setPositiveButton("Yes") { _, _ ->
                positiveCallback.invoke()
            }//doesn't get delete if no is pressed
            .setNegativeButton("No", null)
            .create()
    }
}
