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
                    ?: "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJUZXZtakhwQjIzREk3Z3c4RnYtMkdSRzJjb0czWVhEbHU3TjNoSmc2cEtrIn0.eyJleHAiOjE2NTM1NjMxNTAsImlhdCI6MTY1MzU2MTM1MCwianRpIjoiZGJjYjE2NGEtMDMxYS00M2YwLThlMDQtOGM2M2ZlNTY5OGJmIiwiaXNzIjoiaHR0cHM6Ly9rZXljbG9hay5jdy5hcHAuZGV2aWNlLmJvbHR0ZWNoLmFzaWEvYXV0aC9yZWFsbXMvaG9tZV9wcm90ZWNfcHJvZF9ib2x0dGVjaCIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIyODk3MDgyMy0yZTYwLTQ4NDktYTgzMC1hNjkxNGM4NTI3NGMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJob21lcHJvdGVjdF9hZG1pbiIsInNlc3Npb25fc3RhdGUiOiI5M2IyMTQ0NS0yNmFjLTQ1NjItOGRiZi0xYjMwOGRjMzRjYTgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6IkFuYW5kIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYW5hbmQua3VtYXJAcHVyZXNvZnR3YXJlLmNvbSIsImdpdmVuX25hbWUiOiJBbmFuZCIsImVtYWlsIjoiYW5hbmQua3VtYXJAcHVyZXNvZnR3YXJlLmNvbSJ9.l0Gg2DcRGBtJ-o3Op6zXnMwqWziJPjnxLBObQ_08TD3mkH4TJqf8LIyWp0kr10awQSRntPnodiZ3yA4BagJoQMskTOKNmhADdkZP_GKMuTCIkmQmuUGgZsR8Hvm_JXXN2PbRw80RmRHtDuE4sNr4PHaAOuYcOzBc-1uwD01U1dYShHmMpqVw8BG6qOBzwprhTBx6CNiGZR1iqNXxMQ51pOvhyMoTvdbPEjpYttknhFx4TbGPUdqlk3uV185NpDzRDn43Y2Edi-Z1fqNVxMHNPTZSS8iO38wCb5lIgeCA4D3r3lnwLL_1g9kg_hBHUeIpNNy6o-DxUNsQpU0lhnmUPQ"
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


