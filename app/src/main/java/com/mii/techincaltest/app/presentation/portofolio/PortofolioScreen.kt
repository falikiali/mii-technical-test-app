package com.mii.techincaltest.app.presentation.portofolio

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mii.techincaltest.app.helper.GeneralHelper
import com.mii.techincaltest.app.helper.GeneralHelper.convertToRupiah
import com.mii.techincaltest.app.helper.Screens

@Composable
fun PortofolioScreen(
    modifier: Modifier,
    navController: NavHostController
) {
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

        Text(
            text = "Chart portofolio",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        
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
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = GeneralHelper.parsingJsonPortofolioToModel().data[it].label
                    )

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