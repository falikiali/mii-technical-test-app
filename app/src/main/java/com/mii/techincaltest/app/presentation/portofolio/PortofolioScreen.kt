package com.mii.techincaltest.app.presentation.portofolio

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mii.techincaltest.app.component.DonutChart
import com.mii.techincaltest.app.helper.GeneralHelper
import com.mii.techincaltest.app.helper.GeneralHelper.convertToRupiah
import com.mii.techincaltest.app.helper.Screens

@Composable
fun PortofolioScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    val listPercentages = GeneralHelper.parsingJsonPortofolioToModel().data.map {
        it.percentage.toFloat() / 100
    }
    val listColors = listOf(Color.Red, Color.Blue, Color.Green, Color.Magenta)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier,
            text = "Portofolio",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            DonutChart(
                modifier = Modifier
                    .height(300.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                percentages = listPercentages,
                colors = listColors
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "List portofolio",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(GeneralHelper.parsingJsonPortofolioToModel().data.size) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                        .clickable { navController.navigate(Screens.DetailPortofolio.route + "/${GeneralHelper.parsingJsonPortofolioToModel().data[it].label}") },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Canvas(modifier = Modifier.size(32.dp), onDraw = {
                            drawCircle(color = listColors[it])
                        })
                        
                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            modifier = Modifier,
                            text = GeneralHelper.parsingJsonPortofolioToModel().data[it].label
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = GeneralHelper.parsingJsonPortofolioToModel().data[it].percentage + "%"
                    )
                }
            }
        }
    }
}