package com.sdsol.composebugsample

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.sdsol.composebugsample.destinations.ScreenADestination
import com.sdsol.composebugsample.destinations.ScreenBDestination

enum class BottomBarRoutes(
    val direction: DirectionDestinationSpec,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
) {
    ScreenA(ScreenADestination, R.drawable.ic_launcher_foreground, R.string.app_name),
    ScreenB(ScreenBDestination, R.drawable.ic_launcher_foreground, R.string.app_name)
}