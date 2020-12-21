package srur.apps.cikitsa.magura;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class hospitals extends AppCompatActivity {

    private ListView list1;
    private String[] names, details, phones1, phones2, names_1, details_1, phones_1, phones2_1;
    private EditText edit1;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> detail = new ArrayList<>();
    ArrayList<String> phone1 = new ArrayList<>();
    ArrayList<String> phone2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        objects_declaration();
        start_app();

    }

    private void objects_declaration() {
        list1 = (ListView) findViewById(R.id.list1);
        names = getResources().getStringArray(R.array.hospital_names);
        phones1 = getResources().getStringArray(R.array.phone1);
        phones2 = getResources().getStringArray(R.array.phone2);
        details = getResources().getStringArray(R.array.details);
        edit1 = (EditText) findViewById(R.id.edit1);


    }

    private void start_app() {

        newAdapter adapt = new newAdapter(this, names, phones1, details, phones2);
        list1.setAdapter(adapt);

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



    }

    private void search_operation(String search_text) {
        name.clear();
        detail.clear();
        phone1.clear();
        phone2.clear();

        for(int i = 0; i < names.length; i++){
            if (names[i].contains(search_text) || details[i].contains(search_text)) {
                name.add(names[i]);
                detail.add(names[i]);
                phone1.add(phones1[i]);
                phone2.add(phones2[i]);
            }
        }

        names_1 = name.toArray(new String[0]);
        details_1 = detail.toArray(new String[0]);
        phones_1 = phone1.toArray(new String[0]);
        phones2_1 = phone2.toArray(new String[0]);

        newAdapter adapt = new newAdapter(this, names_1, phones_1, details_1, phones2_1);
        adapt.notifyDataSetChanged();
        list1.setAdapter(adapt);

    }
}
