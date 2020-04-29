package com.leado.ui.main.navBarFragments.LeaderBoard

import androidx.lifecycle.ViewModel

class AchievementsViewModel : ViewModel() {

    var badgesTitle = mutableListOf<String>("Badge 1", "Badge 2", "Badge 3", "Badge 4", "Badge 5", "Badge 6", "Badge 7")

    var badgesDescription =
        mutableListOf<String>("Desc. 1", "Desc. 2", "Desc. 3", "Desc. 4", "Desc. 5", "Desc. 6", "Desc. 7")

    var memberName = mutableListOf<String>("Name 1", "Name 2", "Name 3", "Name 4", "Name 5", "Name 6", "Name 7")
    var points = mutableListOf<String>(
        "Points. 1",
        "Points. 2",
        "Points. 3",
        "Points. 4",
        "Points. 5",
        "Points. 6",
        "Points. 7"
    )

}
