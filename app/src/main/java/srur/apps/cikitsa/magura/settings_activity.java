package srur.apps.cikitsa.magura;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class settings_activity extends AppCompatActivity {

    private int ad_interval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);
        start_app();
    }

    private void start_app() {
        Button old = (Button) findViewById(R.id.old_list);
        Button mail = (Button) findViewById(R.id.mail_us);
        Button facebook = (Button) findViewById(R.id.facebook);
        Button donate = (Button) findViewById(R.id.donate);



        old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page_changer = new Intent();
                page_changer.setClass(getApplicationContext(), hospitals.class);
                startActivity(page_changer);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "srur.apps.1@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mail = new Intent();
                mail.setAction(Intent.ACTION_VIEW);
                mail.setData(Uri.parse("http://www.facebook.com/CIKITSA.MAGURA/"));
                startActivity(mail);
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent web = new Intent();
                web.setAction(Intent.ACTION_VIEW);
                web.setData(Uri.parse("https://play.google.com/store/apps/details?id=srur.apps.cikitsa.magura"));
                startActivity(web);
            }
        });

    }
    @Override
    public void onBackPressed(){
        finish();
    }
}
