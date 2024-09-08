package com.mii.techincaltest.app.presentation.promo

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mii.techincaltest.app.R
import com.mii.techincaltest.app.domain.model.Promo
import com.mii.techincaltest.app.helper.GeneralHelper.convertToRupiah
import com.mii.techincaltest.app.helper.ResultState
import com.mii.techincaltest.app.helper.Screens

@Composable
fun PromoScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    val viewModel = hiltViewModel<PromoViewModel>()
    val resultPromos by viewModel.resultPromos.observeAsState()
    val context = LocalContext.current
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_loading))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

    LaunchedEffect(key1 = Unit) {
        viewModel.getPromos()
    }

    when (resultPromos) {
        is ResultState.Unauthorized -> {
            Column(
                modifier = modifier
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Promo",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Toast.makeText(context, "Sesi anda telah habis", Toast.LENGTH_SHORT).show()
        } 
        is ResultState.ConnectionTimeout -> {
            Column(
                modifier = modifier
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Promo",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Toast.makeText(context, "Koneksi bermasalah, mohon periksa kembali jaringan Anda", Toast.LENGTH_SHORT).show()
        }
        is ResultState.ServerUnderMaintenance -> {
            Column(
                modifier = modifier
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Promo",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Toast.makeText(context, "Server sedang bermasalah", Toast.LENGTH_SHORT).show()
        }
        is ResultState.Success -> {
            Column(
                modifier = modifier
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Promo",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                PromoScreenListPromo(
                    promos = (resultPromos as ResultState.Success<List<Promo>>).data,
                    navController = navController
                )
            }
        }
        is ResultState.Loading -> {
            LottieAnimation(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                composition = composition,
                progress = {
                    progress
                }
            )
        }
        else -> {
            LottieAnimation(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                composition = composition,
                progress = {
                    progress
                }
            )
        }
    }
}

@Composable
fun PromoScreenListPromo(
    promos: List<Promo>,
    navController: NavHostController
) {
    LazyColumn {
        items(promos.size) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screens.DetailPromo.route + "/${promos[it].id}") },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(text = promos[it].title)
                    Text(
                        text = promos[it].location,
                        fontSize = 10.sp,
                        color = Color.DarkGray
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp),
                    text = promos[it].name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}