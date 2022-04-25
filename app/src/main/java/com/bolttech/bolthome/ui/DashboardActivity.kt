package com.bolttech.bolthome.ui

import Start
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import com.bolttech.bolthome.BoltHomeApplication.Companion.appComponent
import com.bolttech.bolthome.data.AppPreference
import com.bolttech.bolthome.ui.theme.BoltHomeTheme
import com.bolttech.bolthome.ui.theme.CustomColor
import com.bolttech.bolthome.ui.theme.DarkColorPalette
import com.bolttech.bolthome.ui.theme.LightColorPalette
import javax.inject.Inject

class DashBoardActivity : ComponentActivity() {

    @Inject
    lateinit var appPreference: AppPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        intent.extras?.getString("TOKEN").let {
            appPreference.setStoredTag(
                "TOKEN",
                it
                    ?: "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkVPVjVIQUM1OXZHYm14RC1WUG9LYSJ9.eyJpc3MiOiJodHRwczovL2Rldi15dDB0amgtci51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjI1N2JiMTUwNmUyMmMwMDZjMDQwNmQyIiwiYXVkIjpbImh0dHBzOi8vYXV0aDAtYXBpLmNvbSIsImh0dHBzOi8vZGV2LXl0MHRqaC1yLnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2NTA4NTk3NDcsImV4cCI6MTY1MDk0NjE0NywiYXpwIjoiclllcnBJRnl4YnIzZUc3a3JkcTRodkF4YlFBVmRSY1oiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwicGVybWlzc2lvbnMiOltdfQ.AmJA1nDao3sLqZAmC2E_04iKczKnyPiZiYRk9SQ_BN4oeEdXqulFUkH016UTJAAjSipIbCmqlJLtUoGydXoEbvNzgdjgiBgWH5QROrclLmmbdqTVYL1ICImUQQqzZBGoRskFDMC60ob5czZFQEf4-P0dOqB2ZP6bnvREtFGzVHyFqNKjb-3TrwIukK4vejjWAuTQ6d46GpcjXPtpFNy0fJIyXDiCf6k_10HQ36nmid8GgIMaMWKRkernpgB8vG5sMkJ1WUWXPmz6Lp91CqRW5wojA4YGBFNVvGstxxL9jYVdgZ5doPB6Qs3A-iPqewt5lW2lcRiOy7LBcyrq5mur4A"
            )
        }
        val myColor = intent.extras?.get("THEME")
        //?.let { myPreference.setStoredTag("THEMSELF",it) }
        val color: CustomColor? = myColor as? CustomColor
        val customColor: Colors? = color?.let {
            lightColors(
                primary = it.primary,
                primaryVariant = it.primaryVariant,
                secondary = it.secondary,
                surface = it.surface,
            )
        }

        setContent {
            BoltHomeTheme(
                customColor = (customColor ?: if (isSystemInDarkTheme()) {
                    DarkColorPalette
                } else {
                    LightColorPalette
                })
            ) {
                Start()
            }
        }
    }
}


