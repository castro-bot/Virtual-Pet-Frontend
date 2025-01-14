package com.example.aplicacionmascotavirtual.api

import com.example.aplicacionmascotavirtual.User
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class ApiClient {
    private val client = OkHttpClient()
    private val baseUrl = "https://8f81-2800-68-42-2-909e-69fa-18db-da1c.ngrok-free.app/users/" // Cambia según tu configuración

    fun registerUser(user: User) {
        val url = "$baseUrl/users/"

        // Crear el cuerpo JSON
        val json = JSONObject()
        json.put("name", user.name)
        json.put("email", user.email)
        json.put("password", user.password)

        // Construir el cuerpo de la solicitud
        val requestBody = json.toString().toRequestBody("application/json; charset=utf-8".toMediaType())

        // Crear la solicitud POST
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        // Enviar la solicitud
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error al registrar usuario: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    println("Usuario registrado exitosamente: ${response.body?.string()}")
                } else {
                    println("Error en la respuesta: ${response.code}, ${response.body?.string()}")
                }
            }
            })
        }
}