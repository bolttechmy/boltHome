package com.bolttech.bolthome.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bolttech.bolthome.R
import com.bolttech.bolthome.component.Page
import com.bolttech.bolthome.ui.theme.Typography

@Composable
fun RequestRepairScreen(navController: NavController) {
    val color = Color(0Xff170F4F)
    val context = LocalContext.current
    Page(backPress = {
        navController.popBackStack()
    }
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_goal_flag),
                    contentDescription = ""
                )
                Text(
                    "Before you begin",
                    color = color,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 16.dp)
                )
                Text(
                    "Some supporting documents may be required to process your request.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
                    lineHeight = 25.sp
                )
            }

            TheftHeader("Request relating to theft")
            TheftDetailLayout(text = "from local law authority.", spannedText = "A police report ")
            TheftDetailLayout("to bar the SIM.", spannedText = "Notice to network operator ")

            Spacer(modifier = Modifier.padding(16.dp))
            TheftHeader("Incident outside the country")
            TheftDetailLayout("At least one ", spannedText = "travel document", isReversed = true)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { Toast.makeText(context, "Coming soon", Toast.LENGTH_LONG).show() },
                    shape = RectangleShape,
                    modifier = Modifier.padding(top = 64.dp)
                ) {
                    Row(modifier = Modifier.padding(all = 8.dp)) {
                        Text("Okay, continue", style = Typography.button)
                        Image(
                            painterResource(R.drawable.ic_chevron_right),
                            contentDescription = "Localized description",
                            Modifier.padding(start = 8.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TheftHeader(heading: String) {
    Text(
        heading,
        fontSize = 14.sp,
        color = Color(0Xff170F4F),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun TheftDetailLayout(text: String, spannedText:String, isReversed:Boolean=false) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_ebook),
            contentDescription = "",
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
        )
        val annotatedString = buildAnnotatedString {
            if(!isReversed){
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append(spannedText)
                }
                append(text)
            }else {
                append(text)
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(spannedText)
                }
            }
        }
        Text(annotatedString, modifier = Modifier.padding(start = 16.dp))
    }
}