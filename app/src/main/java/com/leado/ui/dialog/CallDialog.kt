package com.leado.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.leado.R

class CallDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        } ?: throw IllegalStateException("Activity cannot be null")

        builder?.apply {
            setView(requireActivity().layoutInflater.inflate(R.layout.fragment_call_dialog, null))

            setPositiveButton("Agree") { dialog, which ->
            }
            setNegativeButton("Cancel") { dialog, which -> getDialog()?.cancel()}
        }

        builder?.setMessage("Help!")?.setTitle("choose With Me")
        return builder!!.create()

    }


}


