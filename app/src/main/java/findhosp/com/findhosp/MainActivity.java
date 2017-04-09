package findhosp.com.findhosp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView humanImageView, animalImageView;
    private int anInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View
        initialView();

        //Image Controller
        imageController();

    }   //Main Method

    private void imageController() {
        humanImageView.setOnClickListener(MainActivity.this);
        animalImageView.setOnClickListener(MainActivity.this);
    }

    private void initialView() {
        humanImageView = (ImageView) findViewById(R.id.imvHuman);
        animalImageView = (ImageView) findViewById(R.id.imvAnimal);
    }

    @Override
    public void onClick(View view) {

        if (view == humanImageView) {
            anInt = 0;
        }

        if (view == animalImageView) {
            anInt = 1;
        }

        Intent intent = new Intent(MainActivity.this,ChooseModeActivity.class);
        intent.putExtra("Index", anInt);
        startActivity(intent);


    }   //onClick
}       //Main Class
