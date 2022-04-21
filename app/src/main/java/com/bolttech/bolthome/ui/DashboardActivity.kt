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
                    ?: "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ6MWtleC14SDFtLUtVSEhwbmJGU2w0SmN3VV85dEZZdmkxTEowRUFMbWhVIn0.eyJleHAiOjE2NTA1NDExMjgsImlhdCI6MTY1MDUwNTEyOCwianRpIjoiODJjOTY2N2UtNmRiMS00ODQ4LWJmN2QtMzVhZWJjODA5NDg2IiwiaXNzIjoiaHR0cHM6Ly9rZXljbG9hay5jdy5hcHAuc3RhZ2luZy5kZXZpY2UuYm9sdHRlY2guYXNpYS9hdXRoL3JlYWxtcy9ob21lX3Byb3RlY19kZXZfYm9sdHRlY2giLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNWY2ODE0MTYtZDZjNy00NGZkLTk4MWEtZTAyYWFmZTAyOGJiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiaG9tZXByb3RlY3RfYWRtaW4iLCJzZXNzaW9uX3N0YXRlIjoiOTYyZGE5MjUtNTQ2MS00NDgwLThhMWItY2U4YmQzODdkZDU0IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjMwMDAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJKaXRlbiIsInByZWZlcnJlZF91c2VybmFtZSI6ImJiQGJiLmJiIiwiZ2l2ZW5fbmFtZSI6IkppdGVuIiwiZW1haWwiOiJiYkBiYi5iYiJ9.hxjHVkHZRwmBVINqGGbCnN11g5a2tbR2OaOOzf1rred3kzjOEVQg4ck-EsLu1oQh78qKZJzt_76nraAwPUBQ20uhhqN2c6rysxAXC3NzwbptWAWCYTN22h9LOLEXGWZu0MuG_wxOorfo2_458t8kbaVOcUuFPjiSysyBEWmjb9zM0PMksA6IWdUOQFQJtiXiiZR8xfM3qE-JaUy3oBAfI2hbd8FM-aWe6rrtQzoDxb85Q9HgGx75lnIzKkELNBmoFhbQyNHe8fmDhVBAngjh7gwKZQQCSopjIxUbr4ek8VtGoRaLG2k9G_iWpV3-Wd569J5NdigFSQL7NloDCG7h3g"
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


