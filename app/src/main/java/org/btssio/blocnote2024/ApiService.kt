package org.btssio.siozon2024

import org.btssio.blocnote2024.Note
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL: String = "http://10.0.2.2:3000/"

interface ApiService {
    @GET("notes")
    suspend fun getNotes(): List<Note>

    companion object {

        var apiService: ApiService? = null
        fun getInstance(): ApiService {

            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }

            return apiService!!

        }
    }
}