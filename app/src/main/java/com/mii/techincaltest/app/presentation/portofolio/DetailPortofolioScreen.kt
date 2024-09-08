package com.mii.techincaltest.app.presentation.portofolio

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mii.techincaltest.app.helper.GeneralHelper
import com.mii.techincaltest.app.helper.GeneralHelper.convertToRupiah

@Composable
fun DetailPortofolioScreen(
    navController: NavHostController,
    label: String
) {
    val dataPortofolioByLabel = GeneralHelper.parsingJsonPortofolioToModel().data.filter {
        it.label == label
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier,
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier,
                    text = label,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                items(dataPortofolioByLabel[0].data.size) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = dataPortofolioByLabel[0].data[it].transactionDate
                        )

                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = dataPortofolioByLabel[0].data[it].nominal.convertToRupiah()
                        )
                    }
                }
            }
        }
    }
}