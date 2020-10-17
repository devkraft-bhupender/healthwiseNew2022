package com.hwi.health.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.Models.customized_model;
import com.hwi.health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanay on 05-12-2016.
 */

public class Ideal_Customized_Adapter extends Adapter<Ideal_Customized_Adapter.MyViewHolder> {
    Context ctx;
    private List<customized_model> alist = new ArrayList<>();

    String val;
    Point p;
    Dialog dialog;
    AppCompatActivity activity;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cat_name, base;
        public TextView icon_info;
        public ImageView icon;
        public LinearLayout back;


//        /home/julee/Setups/android/android-studio/jre

        public MyViewHolder(View view) {
            super(view);
            cat_name = (TextView) view.findViewById(R.id.Grains_name);
            base = (TextView) view.findViewById(R.id.Grains_bas);
            icon_info = (TextView) view.findViewById(R.id.Grains_icon);
            icon = (ImageView) view.findViewById(R.id.icon);

        }
    }

    public Ideal_Customized_Adapter(List<customized_model> alist, Context ctx, String val,AppCompatActivity activity) {
        this.alist = alist;
        this.ctx = ctx;
        this.val = val;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ideal_customize_reuslt, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final customized_model b = alist.get(position);

        Log.e("valuuu",val+"....");
        if (val.equals("1")){
            holder.base.setText(b.getThree());
        }else if (val.equals("2")){
            holder.base.setText(b.getFour());
        }else if (val.equals("3")){
            holder.base.setText(b.getFive());
        }

        holder.cat_name.setText(b.getOne());
        try{
            holder.icon.setImageResource(b.getIcon());

            holder.icon_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog(b.getTwo());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        /*holder.one.setText(b.getOne());
        holder.two.setText(b.getTwo());
        holder.three.setText(b.getThree());
        holder.four.setText(b.getFour());
        holder.five.setText(b.getFive());
*/

    }

    @Override
    public int getItemCount() {
        return alist.size();
    }


    void AlertDialog(String data) {
        dialog = new Dialog(ctx, R.style.CustomDialog);
        dialog.setContentView(R.layout.popup_page);
        dialog.setCancelable(true);
        TextView text_popup = (TextView) dialog.findViewById(R.id.text_popup);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        text_popup.setText(data);
        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


        dialog.show();
    }
}

