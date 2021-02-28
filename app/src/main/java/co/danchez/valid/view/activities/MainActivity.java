package co.danchez.valid.view.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import co.danchez.valid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_option1)
    public void traineeOption() {
        startActivity(new Intent(MainActivity.this, TraineeActivity.class));
    }

    @OnClick(R.id.btn_option2)
    public void level2Option() {
        startActivity(new Intent(MainActivity.this, Level2Activity.class));
    }

}