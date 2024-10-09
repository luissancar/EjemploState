package com.example.suma2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import coil.compose.rememberAsyncImagePainter
import com.example.suma2.ui.theme.Suma2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Suma2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Suma(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Suma(name: String, modifier: Modifier = Modifier) {

    var a by rememberSaveable() { mutableStateOf("") }
    var b by rememberSaveable() { mutableStateOf("") }
    var resultado by rememberSaveable() { mutableStateOf("Resultado") }
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Box(Modifier.height(20.dp))
        Text(text = resultado, fontSize = 20.sp)
        LeeDato(numero = a, onTextChange = { a = it })
        LeeDato(numero = b, onTextChange = { b = it })
        Operacion(a, b, "+") { resultado = it.toString() }
        Operacion(a, b, "-") { resultado = it.toString() }
        Operacion(a, b, "*") { resultado = it.toString() }
        Operacion(a, b, "/") { resultado = it.toString() }
        // Sumar(a, b) { resultado = it.toString() }
        //Restar(a, b) { resultado = it.toString() }
    }
}

@Composable
fun Sumar(a: String, b: String, onClick: (Int) -> Unit) {

    Button(
        onClick = {
            try {
                onClick(a.toInt() + b.toInt())
            } catch (e: Exception) {
                Log.d("error", "Error resta")
                onClick(0)
            }
        },
        shape = CircleShape,


        modifier = Modifier
            .padding(20.dp)
            .size(100.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.gato), // Cambia "mi_imagen" por el nombre de tu recurso
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape), // Recorta la imagen a una forma circular,
            contentScale = ContentScale.Crop,

            )
    }
}


@Composable
fun Operacion(a: String, b: String, o: String, onClick: (Int) -> Unit) {

    Button(
        onClick = {
            try {
                when (o) {
                    "+" -> onClick(a.toInt() + b.toInt())
                    "-" -> onClick(a.toInt() - b.toInt())
                    "*" -> onClick(a.toInt() * b.toInt())
                    "/" -> onClick(a.toInt() / b.toInt())
                }


            } catch (e: Exception) {
                Log.d("error", "Error suma")
                onClick(0)
            }


        },
        shape = CircleShape,
        modifier = Modifier
            .padding(20.dp)
            .wrapContentSize()
    ) {
        Row(Modifier.wrapContentSize()) {
            ImageFromUrl("https://ateuves.es/wp-content/uploads/2023/02/hermoso-retrato-gato-cerca2-scaled.jpg")
            Spacer(Modifier.width(10.dp))
            Text(o, fontSize = 30.sp)

        }
    }
}


@Composable
fun LeeDato(numero: String, onTextChange: (String) -> Unit) {
    TextField(
        value = numero, onValueChange = {
            if (it.isDigitsOnly())
                onTextChange(it)
        }, textStyle = TextStyle(
            fontSize = 18.sp,
            textAlign = TextAlign.End // Alineación del texto a la derecha
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )

}


@Composable
fun ImageFromUrl(url: String) {
    Image(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape),
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}
