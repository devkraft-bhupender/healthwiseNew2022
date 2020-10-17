package com.hwi.health.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import androidx.appcompat.app.AlertDialog;
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

import com.hwi.health.Models.RecycleModel;
import com.hwi.health.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanay on 05-12-2016.
 */

public class RecyclerViewAdapter2 extends Adapter<RecyclerViewAdapter2.MyViewHolder> {
    Context ctx;
    private List<RecycleModel> alist = new ArrayList<>();
    String val;
    Point p;
    ImageView icon_info;
    Dialog dialog;
    AppCompatActivity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cat_name, base,servings;
        public ImageView icon_info;
        public ImageView icon;
        public LinearLayout back;


        public MyViewHolder(View view) {
            super(view);
            cat_name = (TextView) view.findViewById(R.id.Grains_name);
            base = (TextView) view.findViewById(R.id.Grains_bas);
            servings = (TextView) view.findViewById(R.id.Servings);
            icon_info = (ImageView) view.findViewById(R.id.Grains_icon);
            icon = (ImageView) view.findViewById(R.id.icon);


        }
    }

    public RecyclerViewAdapter2(List<RecycleModel> alist, Context ctx, String val,AppCompatActivity activity) {
        this.alist = alist;
        this.ctx = ctx;
        this.val = val;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ideal_diat_child_item, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final RecycleModel b = alist.get(position);
        Log.e("valuuu",val+"....");
        if (val.equals("1")){

            holder.base.setText(b.getThree());
        }else if (val.equals("2")){

            holder.base.setText(b.getFour());
        }else if (val.equals("3")){

            holder.base.setText(b.getFive());
        }

        holder.cat_name.setText(b.getOne());
        Picasso.get().load(b.getIcon()).into(holder.icon);
      //  holder.icon.setImageResource(b.getIcon());
        holder.servings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog(b.getTwo());
            }
        });
        holder.icon_info.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            /*   AlertDialog.Builder builder1 = new AlertDialog.Builder(ctx);
               builder1.setMessage(b.getSix());
               builder1.setCancelable(true);
               AlertDialog alert11 = builder1.create();
               alert11.show();*/
              /* onWindowFocusChanged(true);
               if (p != null)
                   showPopup(activity, p);*/
               AlertDialog(b.getSix());
           }
       });


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

