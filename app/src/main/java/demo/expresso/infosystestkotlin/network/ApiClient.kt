package demo.expresso.infosystestkotlin.network

import android.content.Context
import demo.expresso.infosystestkotlin.network.ApiClient.interCeptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null
    var mContext: Context?=null

    //This is used to Store response in Cache and populate last cache data automatic
    val httpClient: OkHttpClient
        get() {
            val cacheSize:Long = 10 * 1024 * 1024
            val cache = Cache(mContext!!.cacheDir, cacheSize)
            return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interCeptor)
                .build()

        }

    //This is Used to print request and response
    val interCeptor: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return loggingInterceptor
        }

    fun getClient(context: Context): Retrofit? {
        mContext = context
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }

        return retrofit
    }
}
