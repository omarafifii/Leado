package com.leado.ui.navBarFragments.Home

import androidx.lifecycle.ViewModel
import com.leado.R
import com.leado.model.Path

class HomeScrollViewModel : ViewModel() {


    val pathIconList= listOf(
        R.drawable.ic_course_1,
        R.drawable.ic_course_2,
        R.drawable.ic_course_3)

    var pathList = mutableListOf<Path>()

    init{
      pathList.apply {
            add(Path("Personal-Branding",  R.drawable.ic_course_1))
            add(Path("Mind-Mapping",R.drawable.ic_course_2))
            add(Path("Self-awareness",R.drawable.ic_course_3))
        }

    }

}
