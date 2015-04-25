package com.example.parokshsaxena.myntrahack.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.parokshsaxena.myntrahack.R;
import com.example.parokshsaxena.myntrahack.model.Item;

import java.util.List;

/**
 * Created by paroksh.saxena on 25/04/15.
 */
public class CustomAdapter extends ArrayAdapter<Item> {
    List<Item> items;
    Context context;
    public CustomAdapter(Context context, List<Item> objects) {
        super(context, R.layout.my_list_view, objects);
        this.items = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.my_list_view, parent, false);
        final Item item = items.get(position);

        TextView name = (TextView) view.findViewById(R.id.userName);
        name.setText(item.getName());
        //name.setText("default");

        Button phoneButton = (Button) view.findViewById(R.id.phoneCall);
        phoneButton.setText(item.getPhoneNum());

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                try {
                    callIntent.setData(Uri.parse("tel:+91" + item.getPhoneNum()));
                    context.startActivity(callIntent);
                } catch (Exception e){

                }

            }

        });
        return view;
    }
}
