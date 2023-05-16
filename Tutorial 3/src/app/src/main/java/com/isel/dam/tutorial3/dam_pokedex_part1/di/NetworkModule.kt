package com.isel.dam.tutorial3.dam_pokedex_part1.di

import android.util.Log
import com.isel.dam.tutorial3.dam_pokedex_part1.data.network.PokemonApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        Log.d("Pokemon-API-Request",request.toString())
        return chain.proceed(request)
    }
}
internal object NetworkModule
{
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


    fun providePokedexService(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }



    fun initPokemonRemoteService() : PokemonApi
    {
        val okHttpClient = provideOkHttpClient()
        val retrofit = provideRetrofit(okHttpClient)
        val pokemonApi = providePokedexService(retrofit)
        return pokemonApi
    }
}

