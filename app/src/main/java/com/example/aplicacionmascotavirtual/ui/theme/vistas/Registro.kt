package com.example.aplicacionmascotavirtual.ui.theme.vistas

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacionmascotavirtual.Componentes.BackgroundImage
import com.example.aplicacionmascotavirtual.Componentes.EspacioV
import com.example.aplicacionmascotavirtual.Componentes.IngresarContrasenia
import com.example.aplicacionmascotavirtual.Componentes.IngresarTexto
import com.example.aplicacionmascotavirtual.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundImage()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logomascota2),
                    contentDescription = "Logo",
                    modifier = Modifier.size(200.dp)
                )
            }

            EspacioV(20)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Crear Cuenta",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                IngresarTexto(
                    value = username,
                    texto = "Nombre de Usuario",
                    onValueChange = { username = it }
                )
                EspacioV(12)

                IngresarTexto(
                    value = email,
                    texto = "Correo Electrónico",
                    onValueChange = { email = it }
                )
                EspacioV(12)

                IngresarContrasenia(
                    value = password,
                    texto = "Contraseña",
                    onValueChange = { password = it }
                )
                EspacioV(12)

                IngresarContrasenia(
                    value = confirmPassword,
                    texto = "Confirmar Contraseña",
                    onValueChange = { confirmPassword = it }
                )

                if (errorMessage.isNotEmpty()) {
                    EspacioV(8)
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                EspacioV(24)

                Button(
                    onClick = {
                        when {
                            username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                                errorMessage = "Por favor, complete todos los campos"
                            }

                            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                                errorMessage = "Correo electrónico inválido"
                            }

                            password != confirmPassword -> {
                                errorMessage = "Las contraseñas no coinciden"
                            }

                            password.length < 6 -> {
                                errorMessage = "La contraseña debe tener al menos 6 caracteres"
                            }

                            else -> {
                                navController.navigate("eleccion_animal")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text("Registrarse", fontSize = 18.sp)
                }

                EspacioV(16)

                TextButton(
                    onClick = {
                        Toast.makeText(this , "Este es un Toast", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(
                        "¿Ya tienes cuenta? Iniciar sesión",
                        color = Color.White
                    )
                }
            }
        }
    }
}