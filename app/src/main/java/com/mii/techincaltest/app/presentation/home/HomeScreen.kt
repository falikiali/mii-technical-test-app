package com.mii.techincaltest.app.presentation.home

import android.database.sqlite.SQLiteConstraintException
import android.provider.CalendarContract.Colors
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mii.techincaltest.app.component.BarcodeScanner
import com.mii.techincaltest.app.domain.model.Account
import com.mii.techincaltest.app.domain.model.Transaction
import com.mii.techincaltest.app.helper.GeneralHelper.convertToRupiah
import com.mii.techincaltest.app.helper.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavHostController,
    barcodeScanner: BarcodeScanner
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val resultAccount by viewModel.resultAccount.observeAsState()
    val resultHistoryTransaction by viewModel.resultHistoryTransaction.observeAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.getAccount()
        viewModel.getHistoryTransaction()
    }

    Column(
        modifier = modifier
    ) {
        HomeScreenAccountCard(
            modifier = modifier,
            account = resultAccount,
            scope = scope,
            barcodeScanner = barcodeScanner
        ) { id, merchant, amount ->
            if (amount <= resultAccount!!.balance) {
                scope.launch {
                    val result = viewModel.addNewTransaction(id, merchant, amount, resultAccount!!.balance)
                    if (result.isSuccess) {
                        navController.navigate(Screens.ConfirmPayment.route)
                    } else {
                        val exception = result.exceptionOrNull()

                        if (exception is SQLiteConstraintException) {
                            Toast.makeText(context, "Transaksi Anda kadaluwarsa", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Transaksi gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Saldo tidak cukup", Toast.LENGTH_SHORT).show()
            }
        }

        if (resultHistoryTransaction?.isNotEmpty() == true) {
            HomeScreenHistoryTransaction(historyTransaction = resultHistoryTransaction!!)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAccountCard(
    modifier: Modifier,
    account: Account?,
    scope: CoroutineScope,
    barcodeScanner: BarcodeScanner,
    onConfirmPayment: (id: String, merchant: String, amount: Int) -> Unit
) {
    var valueScanned by remember {
        mutableStateOf("")
    }

    val sheetState = rememberModalBottomSheetState()

    if (valueScanned.isNotEmpty()) {
        ModalBottomSheet(
            onDismissRequest = { valueScanned = "" },
            sheetState = sheetState
        ) {
            val arrayValue = valueScanned.split('.')

            BottomSheetConfirmationPayment(
                id = arrayValue[1],
                merchant = arrayValue[2],
                amount = arrayValue[3].toInt(),
                onCancel = {
                    valueScanned = ""
                }
            ) {
                onConfirmPayment(
                    arrayValue[1],
                    arrayValue[2],
                    arrayValue[3].toInt()
                )
                valueScanned = ""
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.7f),
                    text = account?.name ?: "-",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.7f),
                    text = if (account != null) "Saldo ${account.balance.convertToRupiah()}" else "Saldo Rp0"
                )
            }

            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    scope.launch {
                        valueScanned = barcodeScanner.startScan() ?: ""
                    }
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.QrCode,
                        contentDescription = ""
                    )

                    Text(text = "Bayar")
                }
            }
        }
    }
}

@Composable
fun HomeScreenHistoryTransaction(
    historyTransaction: List<Transaction>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Riwayat transaksi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(historyTransaction.size) {
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
                        text = historyTransaction[it].merchantName
                    )

                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = historyTransaction[it].amount.convertToRupiah()
                    )
                }
            }
        }
    }
}

@Composable
fun BottomSheetConfirmationPayment(
    id: String,
    merchant: String,
    amount: Int,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Silahkan konfirmasi pembayaran Anda",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = merchant)
                Text(
                    text = id,
                    fontSize = 10.sp,
                    color = Color.DarkGray
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp),
                text = amount.convertToRupiah(),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.onPrimary
                ),
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.primary
                ),
                onClick = { onCancel() }
            ) {
                Text(
                    text = "Batal",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Button(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .weight(1f),
                shape = RoundedCornerShape(12.dp),
                onClick = { onConfirm() }
            ) {
                Text(text = "Konfirmasi")
            }
        }
    }
}