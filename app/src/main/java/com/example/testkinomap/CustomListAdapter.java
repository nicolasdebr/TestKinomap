package com.example.testkinomap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<VehicleDetail> listVehicule;
    private LayoutInflater layoutInflater;
    private Context context;

    static class ViewHolder {
        ImageView icon;
        TextView name;
        TextView id;
    }

        /**
         * Constructeur à 2 paramètres
         * @param listVehicule
         * @param context
         */
        public CustomListAdapter(List<VehicleDetail> listVehicule, Context context) {
            this.listVehicule = listVehicule;
            layoutInflater = LayoutInflater.from((Context) context);
            this.context = (Context) context;
        }

        @Override
    public int getCount() {
        return listVehicule.size();
    }

    @Override
    public Object getItem(int position) {
        return listVehicule.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int positon, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item_layout,null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.imageView_icon);
            holder.name = (TextView) convertView.findViewById(R.id.textView_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        VehicleDetail vehicule = this.listVehicule.get(positon);
        holder.name.setText(vehicule.getName());
       try{
            URL imageUrl = new URL(vehicule.getImageurl());
            Glide.with(context).load(imageUrl).into( holder.icon);
        }catch (IOException e){
            e.printStackTrace();
        }

        return convertView;
    }

}

