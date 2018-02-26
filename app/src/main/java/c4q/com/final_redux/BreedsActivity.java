package c4q.com.final_redux;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BreedsActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private BreedModel breedModel;
    Retrofit retrofit;
    ImageView terryImage;
    ImageView spanielImage;
    ImageView retrieverImage;
    ImageView poodleImage;
    CardView terrier;
    CardView spaniel;
    CardView retriever;
    CardView poodle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);

        sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREF_KEY, MODE_PRIVATE);
        if (sharedPreferences.getString("username", "").equals("")) {
            Intent intent = new Intent(BreedsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        //image views
        terryImage = findViewById(R.id.terry_image);
        spanielImage = findViewById(R.id.spaniel_image);
        retrieverImage = findViewById(R.id.retriever_image);
        poodleImage = findViewById(R.id.poodle_image);
        //layout views
        terrier = findViewById(R.id.terry);
        spaniel = findViewById(R.id.spaniel);
        retriever = findViewById(R.id.retriever);
        poodle = findViewById(R.id.poodle);

        //Onclicks for te specific layouts
        terrier.setOnClickListener(this);
        spaniel.setOnClickListener(this);
        retriever.setOnClickListener(this);
        poodle.setOnClickListener(this);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getDogPic("terrier");
        getDogPic("spaniel");
        getDogPic("retriever");
        getDogPic("poodle");

    }


    private void getDogPic(String dogType) {
        Api dogService = retrofit.create(Api.class);

        Call<BreedModel> getBreedModel = dogService.getDog("type");
        getBreedModel.enqueue(new Callback<BreedModel>() {
            @Override
            public void onResponse(Call<BreedModel> call, Response<BreedModel> response) {
                breedModel = response.body();

                if (breedModel != null && breedModel.getMessage().contains("terrier")) {
                    Picasso.with(BreedsActivity.this)
                            .load(breedModel.getMessage())
                            .into((Target) terrier);
                }

                if (breedModel != null && breedModel.getMessage().contains("spaniel")) {
                    Picasso.with(BreedsActivity.this)
                            .load(breedModel.getMessage())
                            .into((Target) spaniel);
                }

                if (breedModel != null && breedModel.getMessage().contains("retriever")) {
                    Picasso.with(BreedsActivity.this)
                            .load(breedModel.getMessage())
                            .into((Target) retriever);
                }

                if (breedModel != null && breedModel.getMessage().contains("poodle")) {
                    Picasso.with(BreedsActivity.this)
                            .load(breedModel.getMessage())
                            .into((Target) poodle);
                }
            }

            @Override
            public void onFailure(Call<BreedModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //Switch case through layout
            case R.id.terry:
                Intent terryIntent = new Intent(BreedsActivity.this, DOGActivity.class);
                terryIntent.putExtra("breeds", "terrier");
                startActivity(terryIntent);
                break;

            case R.id.spaniel:
                Intent spanielIntent = new Intent(BreedsActivity.this, DOGActivity.class);
                spanielIntent.putExtra("breeds", "spaniel");
                startActivity(spanielIntent);
                break;

            case R.id.retriever:
                Intent retrieverIntent = new Intent(BreedsActivity.this, DOGActivity.class);
                retrieverIntent.putExtra("breeds", "retriever");
                startActivity(retrieverIntent);
                break;

            case R.id.poodle:
                Intent poodleIntent = new Intent(BreedsActivity.this, DOGActivity.class);
                poodleIntent.putExtra("breeds", "poodle");
                startActivity(poodleIntent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        switch (item.getItemId()) {
            case R.id.logout_menu:
                Intent intent = new Intent(BreedsActivity.this, LoginActivity.class);
                intent.putExtra("logout", true);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


