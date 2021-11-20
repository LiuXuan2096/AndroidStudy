package com.liuxuan2096.threadrxjava.network;

import com.liuxuan2096.threadrxjava.model.Repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("users/{username}/repos")
    Single<List<Repo>> getRepos(@Path("username") String username);
}
