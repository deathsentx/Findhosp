package findhosp.com.findhosp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ChooseModeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView nearImageView, filterImageView;
    private int indexAnInt;
    private String tag = "9AprilV1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        //Initial view
        initialView();

        //Get Value From Intent
        getValueFromIntent();

        //Image Controller
        imageController();

    }   //Main Method

    private void getValueFromIntent() {
        indexAnInt = getIntent().getIntExtra("Index", 0);
        Log.d(tag, "index ==> " + indexAnInt);
    }

    private void imageController() {
        nearImageView.setOnClickListener(ChooseModeActivity.this);
        filterImageView.setOnClickListener(ChooseModeActivity.this);
    }

    private void initialView() {
        nearImageView = (ImageView) findViewById(R.id.imvNear);
        filterImageView = (ImageView) findViewById(R.id.imvFilter);
    }

    @Override
    public void onClick(View v) {

        //For Near
        if (v == nearImageView) {
            Intent intent = new Intent(ChooseModeActivity.this, NearActivity.class);
            intent.putExtra("Index", indexAnInt);
            startActivity(intent);
        }

        //For filter
        if (v == filterImageView) {
        }


        }   //Onclick

}   //Main Class
