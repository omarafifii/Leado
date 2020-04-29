package com.leado.ui.congrats

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.leado.R
import kotlinx.android.synthetic.main.fragment_congrats.*

class CongratsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_congrats, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        congrats_share_btn.setOnClickListener {
            val share = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "https://developer.android.com/training/sharing/")

                // (Optional) Here we're setting the title of the content
                putExtra(Intent.EXTRA_TITLE, "Introducing content previews")

                // (Optional) Here we're passing a content URI to an image to be displayed
//                data = contentUri
//                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }, null)

            Log.d("sign", "before intent")
            startActivity(Intent.createChooser(share, "Share images to.."))
            Log.e("sign", "after intent")
        }

        congrats_achievements_btn.setOnClickListener {
            //            val intent = Intent(this.context, AchievementsActivity::class.java)
//            Log.d("sign", "before intent")
//            startActivity(intent)
//            Log.e("sign", "after intent")
        }

    }


}