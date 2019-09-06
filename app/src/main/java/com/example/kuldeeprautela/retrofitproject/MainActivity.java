package com.example.kuldeeprautela.retrofitproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {
TextView textView1,textView2;
ImageView imageView;
EditText editText;
String name="";
public class ImageMaker extends AsyncTask<String, Void,Bitmap>{

    @Override
    protected Bitmap doInBackground(String... strings) {
     Bitmap bitmap=null;
      try{
          URL url=new URL(strings[0]);
          HttpURLConnection connection=(HttpURLConnection) url.openConnection();
          InputStream inputStream=connection.getInputStream();
          bitmap= BitmapFactory.decodeStream(inputStream);
      }
      catch(Exception exception){

      }
        return bitmap;
    }
}

public void f1(View view){
    name=editText.getText().toString();
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    FeedApi feedApi=retrofit.create(FeedApi.class);

        Call<Details> call = feedApi.getFeed(name);
        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                try {
                    if(response.body().getName()!=null || response.body().getBio()!=null || response.body().getAvatar()!=null) {
                        textView1.setText(response.body().getName());
                        textView2.setText(response.body().getBio());
                        ImageMaker imageMaker = new ImageMaker();
                        imageView.setImageBitmap(imageMaker.execute(response.body().getAvatar()).get());
                    }
                else
                    throw new NullPointerException();
                }
                catch (Exception exception){
                    Toast.makeText(MainActivity.this,"No user is registered with this user name",Toast.LENGTH_LONG).show();
                }
                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                Log.d("body", "failure");
            }
        });

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=(TextView) findViewById(R.id.textView);
        textView2=(TextView) findViewById(R.id.textView2);
        imageView=(ImageView) findViewById(R.id.imageView);
        editText=(EditText) findViewById(R.id.edittext);
    }
}
