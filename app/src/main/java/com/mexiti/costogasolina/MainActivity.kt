package com.mexiti.costogasolina


import androidx.compose.material3.TextFieldDefaults
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import java.text.NumberFormat


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mexiti.costogasolina.ui.theme.CostoGasolinaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CostoGasolinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF64B5F6)
                ) {
                    CostGasLayout("Android")
                }
            }
        }
    }
}


@Composable
fun CostGasLayout(name: String,modifier: Modifier = Modifier) {
    var precioLitroEntrada by remember {
        mutableStateOf("")
    }
    var cantidadLitrosEntrada by remember {
        mutableStateOf("")
    }
    var propinaEntrada by remember {
        mutableStateOf("")
    }
    var testigoPropina by remember { mutableStateOf(false) }




    val precioLitro = precioLitroEntrada.toDoubleOrNull() ?: 0.0
    val cantidadLitros = cantidadLitrosEntrada.toDoubleOrNull() ?: 0.0
    val propina = propinaEntrada.toDoubleOrNull() ?: 0.0


    //val total= calcularMonto(precioLitro,cantidadtLitros)
    val total = calcularMonto(precioLitro, cantidadLitros,
        propina, testigoPropina)




    Column (
        modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){


        Text(
            text = stringResource(R.string.calcular_monto),


            )
        EditNumberField(
            label = R.string.ingresa_gasolina,
            leadingIcon = R.drawable.money_gas ,
            keyboardsOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = precioLitroEntrada,
            onValueChanged = {
                precioLitroEntrada=it
            }
        )
        EditNumberField(
            label=R.string.litros,
            R.drawable.gasolinera,
            keyboardsOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value=cantidadLitrosEntrada,
            onValueChanged ={
                cantidadLitrosEntrada=it
            }
        )


        EditNumberField(
            label=R.string.propina,
            R.drawable.propina3,
            keyboardsOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value=propinaEntrada,
            onValueChanged ={
                propinaEntrada=it
            }
        )
        Switch(
            checked = testigoPropina,
            onCheckedChange =  {
                testigoPropina=it
            }
        )
        Text(
            text= stringResource(R.string.monto_total,total),
        )


    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    keyboardsOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = stringResource(id = label), color = Color.White) },
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null) },
        singleLine = true,
        keyboardOptions = keyboardsOptions,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFF0D47A1),
        )
    )
}


@Preview(showBackground = true)
@Composable
fun CostGasLayoutPreview() {
    CostoGasolinaTheme {
        CostGasLayout("Android")
    }
}


private fun calcularMonto(precioLitros:Double,cantidadtLitros:Double,
                          sumaPropina:Double, testigoPropina: Boolean) : String{


    val monto=precioLitros*cantidadtLitros
    val total:Double
    if (testigoPropina){
        total=monto+sumaPropina
    }
    else{
        total=monto
    }
    return NumberFormat.getCurrencyInstance().format(total)


}
