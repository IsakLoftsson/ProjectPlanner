package `is`.hi.hbv601g.projectplanner.data

import com.google.gson.GsonBuilder
import `is`.hi.hbv601g.projectplanner.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    val baseUrl = "https://hbv601g-backend.herokuapp.com/"

    fun getInstance(): Retrofit {

        val gson = GsonBuilder().setLenient().create()

        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        val client = builder.build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}