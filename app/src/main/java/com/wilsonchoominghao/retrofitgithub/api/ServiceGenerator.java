package com.wilsonchoominghao.retrofitgithub.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator
{
	private static String API_BASE_URL = "https://api.github.com/";
	private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

	private static Retrofit.Builder builder = new Retrofit.Builder()
			.baseUrl(API_BASE_URL)
			.addConverterFactory(GsonConverterFactory.create());

	private static Retrofit retrofit = builder.build();

	private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


	public static <S> S createService(Class<S> serviceClass) {
		if(!httpClientBuilder.interceptors().contains(loggingInterceptor)) {
			httpClientBuilder.addInterceptor(loggingInterceptor);
			builder = builder.client(httpClientBuilder.build());
			retrofit = builder.build();
		}
		return retrofit.create(serviceClass);
	}
}
