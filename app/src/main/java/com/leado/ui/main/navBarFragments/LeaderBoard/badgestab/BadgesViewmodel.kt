package com.leado.ui.main.navBarFragments.LeaderBoard.badgestab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BadgesViewmodel : ViewModel() {

    var badgesTitle = mutableListOf<String>("Badge 1", "Badge 2", "Badge 3", "Badge 4", "Badge 5", "Badge 6", "Badge 7")

    var badgesDescription =
        mutableListOf<String>("Desc. 1", "Desc. 2", "Desc. 3", "Desc. 4", "Desc. 5", "Desc. 6", "Desc. 7")

    var badgeslist = mutableListOf<String>()


    val mutableBadgesTitle = MutableLiveData<MutableList<String>>()

    val mutableBadgesDescription = MutableLiveData<MutableList<String>>()

    init {
        mutableBadgesTitle.value = badgesTitle
        mutableBadgesDescription.value = badgesDescription
    }

//    var badgesTitle =
//        liveData<List<String>> { listOf("Badge 1", "Badge 2", "Badge 3", "Badge 4", "Badge 5", "Badge 6", "Badge 7")}

//    var badgesTitle =
//        MutableLiveData<List<String>> { listOf("Badge 1", "Badge 2", "Badge 3", "Badge 4", "Badge 5", "Badge 6", "Badge 7")}

//    var badgesDescription =
//        liveData<List<String>> {listOf("Desc. 1", "Desc. 2", "Desc. 3", "Desc. 4", "Desc. 5", "Desc. 6", "Desc. 7")}
}