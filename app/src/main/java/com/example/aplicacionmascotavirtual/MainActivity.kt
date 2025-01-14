package com.example.aplicacionmascotavirtual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionmascotavirtual.api.ApiClient
import com.example.aplicacionmascotavirtual.ui.theme.AplicacionMascotaVirtualTheme
import com.example.aplicacionmascotavirtual.ui.theme.navegacion.AppNavigation
import com.example.aplicacionmascotavirtual.ui.theme.navegacion.BottomNavigationBar
import com.example.aplicacionmascotavirtual.data.Mascota

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplicacionMascotaVirtualTheme {
                val navController = rememberNavController()
                val mascota = remember { Mascota() }

                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AppNavigation(navController = navController, mascota = mascota)
                    }
                }
            }
        }
    }
}
fun main() {
    val apiClient = ApiClient()

    print("Ingrese su nombre: ")
    val name = readLine() ?: ""  // Captura el input del usuario o usa una cadena vacía si es nulo

    // Capturar el correo
    print("Ingrese su correo: ")
    val email = readLine() ?: ""

    // Capturar la contraseña
    print("Ingrese su contraseña: ")
    val password = readLine() ?: ""

    // Validar que los datos no estén vacíos
    if (name.isBlank() || email.isBlank() || password.isBlank()) {
        println("Todos los campos son obligatorios. Inténtelo nuevamente.")
        return
    }

    // Crear el objeto User con los datos del usuario
    val user = User(name = name, email = email, password = password)

    apiClient.registerUser(user)
}

