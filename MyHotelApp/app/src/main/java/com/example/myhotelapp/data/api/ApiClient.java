package com.example.myhotelapp.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://Hotel-env.eba-wmvqizie.us-east-1.elasticbeanstalk.com/";

    private static Retrofit getClient() {
        // Create a custom Gson instance with custom settings
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd") // Example: Set custom date format
                // Add any other customizations as needed
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ApiService getApiService() {
        return ApiClient.getClient().create(ApiService.class);
    }

}
