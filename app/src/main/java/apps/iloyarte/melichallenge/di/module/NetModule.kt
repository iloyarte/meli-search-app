package apps.iloyarte.melichallenge.di.module

//@Module
class NetModule {
//
//    // Retrofit cache
//    @Provides
//    @Singleton
//    fun provideOkHttpCache(application: Application): Cache {
//        val cacheSize = 10 * 1024 * 1024L // 10 MiB cache
//        return Cache(application.getCacheDir(), cacheSize)
//    }
//
//    // Gson converter
//    @Provides
//    @Singleton
//    fun provideGson(): Gson {
//        val gsonBuilder = GsonBuilder()
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//        return gsonBuilder.create()
//    }
//
//    // HTTP Client
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(cache: Cache): OkHttpClient {
//        val client = OkHttpClient.Builder()
//        client.cache(cache)
//        return client.build()
//    }
//
//    // Retrofit instance
//    @Provides
//    @Singleton
//    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .baseUrl(BuildConfig.BASE_URL)
//            .client(okHttpClient)
//            .build()
//    }
}