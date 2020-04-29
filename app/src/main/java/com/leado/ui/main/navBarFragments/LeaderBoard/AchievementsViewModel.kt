package com.leado.ui.main.navBarFragments.LeaderBoard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.leado.model.Badge
import com.leado.model.User
import com.leado.repos.BadgeRepo
import com.leado.repos.UserRepo

class AchievementsViewModel : ViewModel() {

//    var badgesTitle = mutableListOf<String>("Badge 1", "Badge 2", "Badge 3", "Badge 4", "Badge 5", "Badge 6", "Badge 7")

//    var badgesDescription =
//        mutableListOf<String>("Desc. 1", "Desc. 2", "Desc. 3", "Desc. 4", "Desc. 5", "Desc. 6", "Desc. 7")

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

    private var badgeRepo: BadgeRepo = BadgeRepo
    private var userRepo: UserRepo = UserRepo


    /**get Badges for BadgesFragment**/
    var badgeByList = mutableListOf<Badge>()
    val liveBadgeByList = badgeRepo.getBadgeByList().switchMap {

        badgeByList.clear()
        badgeByList.addAll(it)
        val _liveBadgeByList = MutableLiveData<MutableList<Badge>>()
        _liveBadgeByList.value = it
        return@switchMap _liveBadgeByList
    }

    /**get Users for Leaderboard**/
    var userByList = mutableListOf<User>()
    val liveUserByList = userRepo.getUserByList().switchMap {
        userByList.clear()
        userByList.addAll(it)
        val _liveUserByList = MutableLiveData<MutableList<User>>()
        _liveUserByList.value = it
        return@switchMap _liveUserByList
    }

}
