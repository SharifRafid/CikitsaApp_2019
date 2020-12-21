package srur.apps.cikitsa.magura;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class hospitals_new extends AppCompatActivity {
    //Cikitsa app by SRUR ## No Copyrights
    //Code can be used by Authorised Permission Only

    private EditText edit1;
    private ListView list1;
    private String[] names, details, phones1, phones2, names2, details2, phones12, phones22;
    private Boolean click_state = false;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> detail = new ArrayList<>();
    ArrayList<String> phone1 = new ArrayList<>();
    ArrayList<String> phone2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_new);
        start_app();
    }

    private void start_app() {

        //Objects

        list1 = (ListView) findViewById(R.id.list1);
        edit1 = (EditText) findViewById(R.id.edit1);
        names = getResources().getStringArray(R.array.hospital_names);
        phones1 = getResources().getStringArray(R.array.phone1);
        phones2 = getResources().getStringArray(R.array.phone2);
        details = getResources().getStringArray(R.array.details);

        //Adapter For List

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.custom_list_new,R.id.text1,names);
        list1.setAdapter(adapter);

        //Text Change Listener For Edittext
         edit1.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 search_operation(edit1.getText().toString());
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });

         //List on object click listener

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int object, long l) {
                //click state decides weather the editext is clean or not
                if(click_state){
                    //dia is a alert dialogure that displays the info

                    final AlertDialog dia = new AlertDialog.Builder(hospitals_new.this).create();
                    LayoutInflater inflater = getLayoutInflater();

                    View convertView = (View) inflater.inflate(R.layout.custom_toast, null);
                    dia.setView(convertView);

                    TextView text1 = (TextView) convertView.findViewById(R.id.name);   //For the hospial names or heading
                    text1.setText(names2[object]);
                    TextView text2 = (TextView) convertView.findViewById(R.id.details);   // For the hospital details
                    text2.setText(details2[object]);


                    Button button1 = (Button) convertView.findViewById(R.id.phone1);
                    Button button2 = (Button) convertView.findViewById(R.id.phone2);

                    ImageView img = (ImageView) convertView.findViewById(R.id.exit);

                    if(phones12[object].length() > 4){       //tests if the hospital has a phone number or not
                        button1.setText(phones12[object]);
                    }else{
                        button1.setText("No Number");
                    }

                    button1.setOnClickListener(new View.OnClickListener(){      //Calls the number on the number button click
                        public void onClick(View v){
                            if(phones12[object].length() > 4) {
                                make_call(phones12[object]);
                            }

                        }
                    });

                    if(phones22[object].length() > 4){    //Same as Previous
                        button2.setText(phones22[object]);
                    }else{
                        button2.setText("No Number");
                    }
                    button2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {     //same
                            if (phones22[object].length() > 4) {
                                make_call(phones22[object]);
                            }


                        }
                    });




                    img.setOnClickListener(new View.OnClickListener() {   // this is for cancelling the dialogue
                        @Override
                        public void onClick(View view) {
                            dia.dismiss();
                        }
                    });


                    dia.show();

                }else{       //if the edittext is open or the user has not searched for anything
                    //same just shows different string lists
                    //this is the state  when the activity is launched
                    final AlertDialog dia = new AlertDialog.Builder(hospitals_new.this).create();
                    LayoutInflater inflater = getLayoutInflater();

                    View convertView = (View) inflater.inflate(R.layout.custom_toast, null);
                    dia.setView(convertView);

                    TextView text1 = (TextView) convertView.findViewById(R.id.name);
                    text1.setText(names[object]);
                    TextView text2 = (TextView) convertView.findViewById(R.id.details);
                    text2.setText(details[object]);


                    Button button1 = (Button) convertView.findViewById(R.id.phone1);
                    Button button2 = (Button) convertView.findViewById(R.id.phone2);

                    ImageView img = (ImageView) convertView.findViewById(R.id.exit);

                    if(phones1[object].length() > 4){
                        button1.setText(phones1[object]);
                    }else{
                        button1.setText("No Number");
                    }
                    button1.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            if(phones1[object].length() > 4) {
                                make_call(phones1[object]);
                            }

                        }
                    });



                    if(phones2[object].length() > 4){
                        button2.setText(phones2[object]);
                    }else{
                        button2.setText("No Number");
                    }
                    button2.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            if(phones2[object].length() > 4) {
                                make_call(phones2[object]);
                            }

                        }
                    });



                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dia.dismiss();
                        }
                    });


                    dia.show();

                }
            }
        });
    }

    private void search_operation(String key_word) {            //how the searching function works
                                                                // i made the searching function myself it may not be soo good ;)
        if(!key_word.equals("")) {      //checks the edittext is clear or not
            name.clear();
            detail.clear();
            phone1.clear();
            phone2.clear();
            for (int x = 0; x < names.length; x++) {
                if (names[x].contains(key_word) || details[x].contains(key_word)) {
                    name.add(names[x]);
                    detail.add(details[x]);
                    phone1.add(phones1[x]);
                    phone2.add(phones2[x]);
                }
            }
            names2 = name.toArray(new String[0]);
            details2 = detail.toArray(new String[0]);
            phones12 = phone1.toArray(new String[0]);
            phones22 = phone2.toArray(new String[0]);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.custom_list_new,R.id.text1,names2);
            adapter.notifyDataSetChanged();
            list1.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            click_state = true;

        }else{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.custom_list_new,R.id.text1,names);
            adapter.notifyDataSetChanged();
            list1.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            click_state = false;
        }




    }

    private void make_call(String number){
        Intent calling_intent = new Intent();
        calling_intent.setAction(Intent.ACTION_CALL);
        calling_intent.setData(Uri.parse("tel:".concat(number)));
        startActivity(calling_intent);
    }

}
