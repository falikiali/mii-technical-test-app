package com.mii.techincaltest.app.presentation.promo.detail

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mii.techincaltest.app.R
import com.mii.techincaltest.app.domain.model.Promo
import com.mii.techincaltest.app.helper.ResultState
import com.mii.techincaltest.app.helper.Screens

@Composable
fun DetailPromoScreen(
    navController: NavController,
    id: Int
) {
    val viewModel = hiltViewModel<DetailPromoViewModel>()
    val resultPromo by viewModel.resultPromo.observeAsState()
    val context = LocalContext.current
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_loading))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

    LaunchedEffect(key1 = Unit) {
        viewModel.getPromoById(id)
    }

    Scaffold {
        when (resultPromo) {
            is ResultState.Unauthorized -> {
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
                            text = "Promo",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Toast.makeText(context, "Sesi anda telah habis", Toast.LENGTH_SHORT).show()
            }
            is ResultState.ConnectionTimeout -> {
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
                            text = "Promo",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Toast.makeText(context, "Koneksi bermasalah, mohon periksa kembali jaringan Anda", Toast.LENGTH_SHORT).show()
            }
            is ResultState.ServerUnderMaintenance -> {
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
                            text = "Promo",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Toast.makeText(context, "Server sedang bermasalah", Toast.LENGTH_SHORT).show()
            }
            is ResultState.Success -> {
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
                            text = "Promo",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(end = 16.dp)
                        ) {
                            Text(text = (resultPromo as ResultState.Success<Promo>).data.title)

                            Text(
                                text = (resultPromo as ResultState.Success<Promo>).data.location,
                                fontSize = 10.sp,
                                color = Color.DarkGray
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            text = (resultPromo as ResultState.Success<Promo>).data.name,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp),
                        text = "Deskripsi promo :",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 18.sp
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = (resultPromo as ResultState.Success<Promo>).data.desc,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp
                    )
                }
            }
            is ResultState.Loading -> {
                LottieAnimation(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    composition = composition,
                    progress = {
                        progress
                    }
                )
            }
            else -> {
                LottieAnimation(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    composition = composition,
                    progress = {
                        progress
                    }
                )
            }
        }
    }
}