package srur.apps.cikitsa.magura;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class emergency_call extends AppCompatActivity {

    private Button main, sadar, ambulance, peerles, nari, pourasava, fire, thana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        start_app();
    }

    private void start_app() {
        main = (Button) findViewById(R.id.hotline);
        sadar = (Button) findViewById(R.id.sadar_hosp);
        ambulance = (Button) findViewById(R.id.ambulance);
        nari = (Button) findViewById(R.id.nari);
        pourasava = (Button) findViewById(R.id.pourosova);
        fire = (Button) findViewById(R.id.fireservice);
        thana = (Button) findViewById(R.id.thana);

        final Animation fade = AnimationUtils.loadAnimation(this,R.anim.fade);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.startAnimation(fade);
                call_now("999");
            }
        });
        sadar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sadar.startAnimation(fade);
                call_now("048862209");

            }
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambulance.startAnimation(fade);
                call_now("048862209");
            }
        });


        nari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nari.startAnimation(fade);
                call_now("109");
            }
        });

        pourasava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pourasava.startAnimation(fade);
                call_now("048862290");
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fire.startAnimation(fade);
                call_now("048862222");
            }
        });
        thana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thana.startAnimation(fade);
                call_now("048862234");
            }
        });



    }
    private void call_now(String number){
        if(number.length() > 2){
            Intent page_changer = new Intent();
            page_changer.setAction(Intent.ACTION_CALL);
            page_changer.setData(Uri.parse("tel:".concat(number)));
            startActivity(page_changer);
        }else{
            Toast.makeText(getApplicationContext(),"No Number Is Currently Added",Toast.LENGTH_SHORT).show();
        }
    }

}
