package c4q.com.final_redux;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DOGActivity extends AppCompatActivity {

    private static String TAG = "Dog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);

        Intent intent = getIntent();
        Log.d(TAG, "onCreate: " + intent.getStringExtra("breedName"));
    }
}
