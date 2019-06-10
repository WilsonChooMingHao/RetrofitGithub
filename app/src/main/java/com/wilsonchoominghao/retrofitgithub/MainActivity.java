package com.wilsonchoominghao.retrofitgithub;

import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.wilsonchoominghao.retrofitgithub.api.ServiceGenerator;
import com.wilsonchoominghao.retrofitgithub.api.model.GitHubRepo;
import com.wilsonchoominghao.retrofitgithub.api.service.GitHubClient;
import com.wilsonchoominghao.retrofitgithub.ui.adapter.GitHubRepoAdapter;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.pagination_list);

		GitHubClient gitHubClient = ServiceGenerator.createService(GitHubClient.class);
		Call<List<GitHubRepo>> call = gitHubClient.reposForUser("WilsonChooMingHao");

		call.enqueue(new Callback<List<GitHubRepo>>()
		{
			@Override public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response)
			{
				List<GitHubRepo> repos = response.body();

				listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
			}

			@Override public void onFailure(Call<List<GitHubRepo>> call, Throwable t)
			{
				Toast.makeText(MainActivity.this, "error :<", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
