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
                    ?: "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ6MWtleC14SDFtLUtVSEhwbmJGU2w0SmN3VV85dEZZdmkxTEowRUFMbWhVIn0.eyJleHAiOjE2NTY0Mzg4NjQsImlhdCI6MTY1NjQwMjg2NCwianRpIjoiOGJmN2NhMTktYjE5ZS00YTI4LWI2NGItZmZmMjcxOWYzNTAzIiwiaXNzIjoiaHR0cHM6Ly9rZXljbG9hay5jdy5hcHAuc3RhZ2luZy5kZXZpY2UuYm9sdHRlY2guYXNpYS9hdXRoL3JlYWxtcy9ob21lX3Byb3RlY19kZXZfYm9sdHRlY2giLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiN2QyNWJhM2MtNzNkYi00YTBmLTk0N2EtMWZmZWQ0NzczMjNmIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiaG9tZXByb3RlY3RfYWRtaW4iLCJzZXNzaW9uX3N0YXRlIjoiNzE4ZDRiMWMtYTdhNi00YzVlLWJhODUtZDdhNmM2ZmI5YjU0IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjMwMDAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IkFuYW5kIEt1bWFyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYW5hbmQua3VtYXJAcHVyZXNvZnR3YXJlLmNvbSIsImdpdmVuX25hbWUiOiJBbmFuZCBLdW1hciIsImVtYWlsIjoiYW5hbmQua3VtYXJAcHVyZXNvZnR3YXJlLmNvbSJ9.wMCaWGlonYi_A1Yg43IORV4ISUzUVzzjcRdlZ8MYLGLpBuxXpwub-uD8KWKm5-_B2u2Nj3tldESvah2xGS8lEYKcuIw0MrotfyjAY-clsVlVCO_a3bUYEKw8RWboPxsUgaTfzscSuCbkwJ_5CKtlMfdLOFNzMniA2sBIqGk828SSK7utefjrcvmP_kDkZtncati19G2Nw0F_jx2XLdiSjju2F7Ln3YjdEh4O2ZNe3H2vHl7BNmOxh8kZdSIdATT354vmDXx7uTxxOA2J8ioUqRD3uDvQ0ghfXOTQML1Lh0qIdOZhNwZwgsqcDFbR07rINlEMFc-xPJV36ItqMzEaag"
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


