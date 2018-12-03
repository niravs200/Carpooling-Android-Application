package com.example.bhavneet.car;

import android.content.Context;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class UserAdapter extends ArrayAdapter<User> {

    private ArrayList<User> user_list;
    private String driverName;

    public UserAdapter(Context context, int resource,  ArrayList<User> user_list, String driverName) {
        super(context, resource, user_list);
        this.user_list = user_list;
        this.driverName = driverName;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v==null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.poolerlist,null);
        }

        final User u = user_list.get(position);

        if(u!=null){
            final TextView name = v.findViewById(R.id.name);
            TextView phone = v.findViewById(R.id.phone);
            TextView source_add = v.findViewById(R.id.address);
            TextView dest_add = v.findViewById(R.id.destinationAdd);

            Button accept = v.findViewById(R.id.accept);
            Button reject = v.findViewById(R.id.reject);

            name.setText(u.getName());
            phone.setText(u.getPhone());
            source_add.setText(u.getSource());
            dest_add.setText(u.getDestination());

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user_list.remove(position);
                    UpdateUserDetails u = new UpdateUserDetails(name,driverName);
                    u.execute();
                    notifyDataSetChanged();


                }
            });


        }

        return v;
    }
}
