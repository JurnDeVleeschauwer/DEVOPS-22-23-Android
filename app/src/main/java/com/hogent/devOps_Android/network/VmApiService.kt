package com.hogent.devOps_Android.network

import com.hogent.devOps_Android.database.entities.CourseEnumJsonAdapter
import com.hogent.devOps_Android.database.entities.RoleEnumJsonAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


private const val BASE_URL = "https://10.0.2.2:44356/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(RoleEnumJsonAdapter())
    .add(CourseEnumJsonAdapter())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(getUnsafeOkHttpClient())
    .build()
interface VmApiService {
    @GET("project/User")
    fun GetIndexOfProjectByIdUser(@Query("UserId")customer_id: String):
            Deferred<NetworkProjectenContainer>

    @GET("project/Detail")
    fun GetIndexOfProjectById( @Query("ProjectenId") project_id: Long):
            Deferred<NetworkProjectenDetailContainer>

    @GET("virtualmachine/{id}")
    fun GetIndexOfVmById(@Path("id")vm_id: Long):
            Deferred<NetworkVMContainer>

    @GET("User/{id}")
    fun GetIndexOfUserById( @Path("id") UserId: String):
            Deferred<NetworkNetworkUserContainer>
}

object VmApi {
    val retrofitService : VmApiService by lazy {
        retrofit.create(VmApiService::class.java)
    }
}

private fun getUnsafeOkHttpClient(): OkHttpClient? {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts =
            arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(
            sslSocketFactory,
            (trustAllCerts[0] as X509TrustManager)
        )
        builder.hostnameVerifier { hostname, session -> true }
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}