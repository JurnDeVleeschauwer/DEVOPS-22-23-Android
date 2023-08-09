package com.hogent.devOps_Android.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://localhost:44356/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL).build()
interface VmApiService {
    @GET("project/User")
    fun GetIndexOfProjectByIdUser(customer_id: Long):
            Deferred<List<VmProperty>>

    @GET("project/Detail")
    fun GetIndexOfProjectById(project_id: Long):
            Deferred<List<VmProperty>>

    @GET("virtualmachine/")
    fun GetIndexOfVmById(vm_id: Long):
            Deferred<List<VmProperty>>
}

object VmApi {
    val retrofitService : VmApiService by lazy {
        retrofit.create(VmApiService::class.java)
    }
}