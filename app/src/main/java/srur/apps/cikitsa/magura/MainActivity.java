package srur.apps.cikitsa.magura;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //Cikitsa app by SRUR ## No Copyrights
    //Code can be used by Authorised Permission Only


    private Button button1, button2, button3;
    private int code_permission = 1;
    private ImageView settings,img2,voice;
    private Boolean f_launch = true;
    private Timer tim = new Timer();
    private TimerTask ad_show;
    private SharedPreferences ad_data;
    private LinearLayout l1,l2,l3,edit_line;
    private EditText search;
    private Boolean search_phase = false;
    private ListView grid1;
    private String[] hosp_clues,disease_name,disease_cure,names,phones1,phones2,details,f_disease,f_cure,f_names,f_phones1,f_phones2,f_details,disease_clues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objects_declaration();
        start_app();
        AlertDialog.Builder caution = new AlertDialog.Builder(this);
        caution.setTitle("সতর্কতা !!!");
        caution.setMessage("অ্যাপটি পরিপুর্নো ভাবে তৈরি হয় নি ...তাই এটি সতর্কতাআর সাথে ব্যবহার করবেন...ধন্যবাদ");
        caution.setPositiveButton("ঠিক আছে", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    @Override
    public  void onStart(){
        super.onStart();
        search_cikitsa();
    }


    private void objects_declaration(){

        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        settings = (ImageView) findViewById(R.id.settings);
        img2 = (ImageView) findViewById(R.id.img2);
        voice = (ImageView) findViewById(R.id.voice);
        l1 = (LinearLayout) findViewById(R.id.mid_linear);
        l2 = (LinearLayout) findViewById(R.id.low_linear);
        l3 = (LinearLayout) findViewById(R.id.linear2);
        edit_line = (LinearLayout) findViewById(R.id.edit_linear);
        search = (EditText) findViewById(R.id.edit1);
        grid1 = (ListView) findViewById(R.id.grid1);

        disease_clues = getResources().getStringArray(R.array.disease_keywords);

        hosp_clues = getResources().getStringArray(R.array.hospital_clues);
        disease_name = getResources().getStringArray(R.array.disease_name);
        disease_cure = getResources().getStringArray(R.array.disease_cure);

        names = getResources().getStringArray(R.array.hospital_names);
        phones1 = getResources().getStringArray(R.array.phone1);
        phones2 = getResources().getStringArray(R.array.phone2);
        details = getResources().getStringArray(R.array.details);

    }

    private void start_app() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            int abcdefg = 0;
        } else {
            request_permission();
        }



        LayoutInflater i = getLayoutInflater();
        View v = i.inflate(R.layout.custom_toast_main, (ViewGroup) findViewById(R.id.linear3));
        Toast toast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG);
        toast.setView(v);
        toast.show();

        final Animation fade = AnimationUtils.loadAnimation(this,R.anim.fade);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.startAnimation(fade);
                Intent page_changer = new Intent();
                page_changer.setClass(getApplicationContext(), hospitals_new.class);
                startActivity(page_changer);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2.startAnimation(fade);
                Intent page_changer = new Intent();
                page_changer.setClass(getApplicationContext(), more.class);
                startActivity(page_changer);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3.startAnimation(fade);
                Intent page_changer = new Intent();
                page_changer.setClass(getApplicationContext(), emergency_call.class);
                startActivity(page_changer);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent web = new Intent();
                web.setAction(Intent.ACTION_VIEW);
                web.setData(Uri.parse("https://play.google.com/store/apps/details?id=srur.apps.cikitsa.magura"));
                startActivity(web);
                img2.startAnimation(fade);
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.startAnimation(fade);
                Intent page_changer = new Intent(getApplicationContext(), settings_activity.class);
                page_changer.setClass(getApplicationContext(), settings_activity.class);
                startActivity(page_changer);
            }
        });

    }

    private void request_permission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(this)
                    .setTitle("পারমিশন প্র্যোযন")
                    .setMessage("অ্যাপ থেকে ডাইরেক্ট কল করার জন্য এই পারমিশঅন টা প্রয়োজন । তাই পরবর্তী মেসেজে অ্যালাও করে দিন।")
                    .setPositiveButton("ঠিক আছে", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.CALL_PHONE}, code_permission);
                        }
                    })
                    .setNegativeButton("না, থাক ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, code_permission);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == code_permission)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                int abcd124 = 1234;
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public void onBackPressed(){
        if(!search_phase) {
            AlertDialog.Builder back_press = new AlertDialog.Builder(this);

            back_press.setTitle("আমাদের রেট করুন");
            back_press.setMessage("আমরা প্রচুর শ্রম এবং সময় দিয়ে অ্যাপটি তৈরি করেছি তাই আমদের রেট করলে আমাদের উপকার হবে।।\nধন্যবাদ");
            back_press.setPositiveButton("রেট করুন", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent web = new Intent();
                    web.setAction(Intent.ACTION_VIEW);
                    web.setData(Uri.parse("https://play.google.com/store/apps/details?id=srur.apps.cikitsa.magura"));
                    startActivity(web);

                }
            });
            back_press.setNegativeButton("না, থাক", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    tim.cancel();
                    finish();
                }
            });
            back_press.create().show();
        }else{
            search.setText("");
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            grid1.setVisibility(View.GONE);
            search_phase = false;
        }
    }


    private boolean search_state = false;



    private ArrayList<String> d_name = new ArrayList<String>();
    private ArrayList<String> d_cure = new ArrayList<String>();

    private ArrayList<String> d_names= new ArrayList<String>();
    private ArrayList<String> d_phones1 = new ArrayList<String>();
    private ArrayList<String> d_phones2 = new ArrayList<String>();
    private ArrayList<String> d_details = new ArrayList<String>();

    private void search_cikitsa(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.custom_list_new,R.id.text1,disease_name);
        grid1.setAdapter(adapter);

        final Animation a = AnimationUtils.loadAnimation(this, R.anim.push_up_in);

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!search_phase){
                    search_phase = true;
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);
                    grid1.setVisibility(View.VISIBLE);
                    search.startAnimation(a);
                    voice.startAnimation(a);
                    grid1.startAnimation(a);
                }

                Intent voice_intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                voice_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                voice_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                if (voice_intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(voice_intent, 10);
                } else {
                    Toast.makeText(MainActivity.this, "আপনার ফোনে এই ফিচার সাপোর্ট করে না", Toast.LENGTH_SHORT).show();
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!search_phase) {
                    search_phase = true;
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);
                    grid1.setVisibility(View.VISIBLE);
                    search.startAnimation(a);
                    voice.startAnimation(a);
                    grid1.startAnimation(a);
                }
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String kword = search.getText().toString();
                d_name.clear();
                if(!(kword.equals("") || kword.equals(" "))){
                    if(kword.matches("a-zA-Z0-9")){
                        kword = kword.toLowerCase();
                    }else{
                        kword = kword;
                    }

                    for(int x = 0; x < disease_name.length; x++){
                        if(disease_name[x].contains(kword) || disease_clues[x].contains(kword)){
                            d_name.add(disease_name[x]);
                            d_cure.add(disease_cure[x]);
                        }
                    }

                    f_disease = d_name.toArray(new String[0]);
                    f_cure = d_cure.toArray(new String[0]);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.custom_list_new,R.id.text1,f_disease);
                    adapter.notifyDataSetChanged();
                    grid1.setAdapter(adapter);
                    search_state = true;
                }else{
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.custom_list_new,R.id.text1,disease_name);
                    adapter.notifyDataSetChanged();
                    grid1.setAdapter(adapter);
                    search_state = false;
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                if(search_state) {
                    click_process(f_disease,f_cure,index);
                }else {
                    click_process(disease_name,disease_cure, index);
                }
            }
        });




    }

    private void click_process(String[] disease,String[] cure, int index) {

        final AlertDialog dia = new AlertDialog.Builder(MainActivity.this).create();
        LayoutInflater inflater = getLayoutInflater();

        View convertView = (View) inflater.inflate(R.layout.disease_view, null);
        dia.setView(convertView);

        TextView headline = (TextView) convertView.findViewById(R.id.name_disease);
        TextView p_cikitsa = (TextView) convertView.findViewById(R.id.cikitsa);
        ImageView img_back = (ImageView) convertView.findViewById(R.id.img_back);

        headline.setText(disease[index]);
        p_cikitsa.setText(cure[index]);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia.cancel();
            }
        });
        dia.show();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    search.setText(result.get(0));
                }
                break;
        }
    }

}
