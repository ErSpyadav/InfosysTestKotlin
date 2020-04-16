package demo.expresso.infosystestkotlin.network

import demo.expresso.infosystestkotlin.model.ImageData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{path}")
    fun getImageData(@Path("path") path: String): Call<ImageData>

    companion object {

        val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json"
    }
}
