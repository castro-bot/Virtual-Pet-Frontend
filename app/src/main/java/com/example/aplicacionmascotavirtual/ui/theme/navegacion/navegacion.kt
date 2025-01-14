package com.example.aplicacionmascotavirtual.ui.theme.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.aplicacionmascotavirtual.data.Mascota
import com.example.aplicacionmascotavirtual.screens.ConfiguracionScreen
import com.example.aplicacionmascotavirtual.screens.EleccionAnimalScreen
import com.example.aplicacionmascotavirtual.screens.Inicio
import com.example.aplicacionmascotavirtual.screens.MascotaScreen
import com.example.aplicacionmascotavirtual.ui.theme.vistas.Registro

@Composable
fun AppNavigation(navController: NavHostController, mascota: Mascota) {
    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            Inicio(navController)
        }

        composable("registro") {
            Registro(navController)
        }

        composable("eleccion_animal") {
            EleccionAnimalScreen(
                mascota = mascota,
                onAnimalSeleccionado = { tipoAnimal ->
                    mascota.tipoAnimal = tipoAnimal
                    navController.navigate("configuracion")
                }
            )
        }

        composable("configuracion") {
            ConfiguracionScreen(mascota)
        }

        composable("mascota") {
            MascotaScreen(mascota)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    // Solo mostrar la barra de navegación si no estamos en inicio o registro
    if (currentRoute != "inicio" && currentRoute != "registro") {
        NavigationBar {
            NavigationBarItem(
                selected = currentRoute == "eleccion_animal",
                onClick = { navController.navigate("eleccion_animal") },
                label = { Text("Inicio") },
                icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") }
            )

            NavigationBarItem(
                selected = currentRoute == "mascota",
                onClick = { navController.navigate("mascota") },
                label = { Text("Mascota") },
                icon = { Icon(Icons.Default.Star, contentDescription = "Mascota") }
            )

            NavigationBarItem(
                selected = currentRoute == "configuracion",
                onClick = { navController.navigate("configuracion") },
                label = { Text("Configuración") },
                icon = { Icon(Icons.Default.Settings, contentDescription = "Configuración") }
            )
            }
        }
}