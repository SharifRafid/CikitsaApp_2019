package srur.apps.cikitsa.magura;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class newAdapter extends BaseAdapter {
    String[] phones1,names,details,phones2;
    Context context;

    private Intent call_intent;

    private LayoutInflater inflate;

    newAdapter(Context context, String[] names,String[] phones1, String[] details, String[] phones2){
        this.context = context;
        this.names = names;
        this.phones1 = phones1;
        this.details = details;
        this.phones2 = phones2;
    }


    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null){
            inflate = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.custom_list, viewGroup, false);
        }

        final TextView text1 = (TextView) view.findViewById(R.id.name1);
        final TextView text2 = (TextView) view.findViewById(R.id.name2);
        TextView text3 =  (TextView) view.findViewById(R.id.details1);
        Button phone1 = (Button) view.findViewById(R.id.phone1);
        Button phone2 = (Button) view.findViewById(R.id.phone2);
        final LinearLayout hide = (LinearLayout) view.findViewById(R.id.hideable_linear);
        call_intent = new Intent();

        text1.setText(names[i]);
        text2.setText(names[i]);
        text3.setText(details[i]);
        phone1.setText(phones1[i]);
        phone2.setText(phones2[i]);

        if(phones1[i].equals(" ")){
            phone1.setVisibility(View.GONE);
        }
        if(phones2[i].equals(" ")){
            phone2.setVisibility(View.GONE);
        }



        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                hide.setVisibility(View.VISIBLE);
                text2.setVisibility(View.VISIBLE);
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text2.setVisibility(View.GONE);
                text1.setVisibility(View.VISIBLE);
                hide.setVisibility(View.GONE);
            }
        });
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phones1[i].contains("0")){
                    call_intent.setAction(Intent.ACTION_CALL);
                    call_intent.setData(Uri.parse("tel:".concat(phones1[i])));
                    context.startActivity(call_intent);
                }
                else{
                    Toast.makeText(context,"No Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phones2[i].contains("0")){
                    call_intent.setAction(Intent.ACTION_CALL);
                    call_intent.setData(Uri.parse("tel:".concat(phones2[i])));
                    context.startActivity(call_intent);
                }
                else {
                    Toast.makeText(context,"No Number", Toast.LENGTH_SHORT).show();
                }
            }
        });








        return view;
    }
}
