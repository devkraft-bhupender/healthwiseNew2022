package com.hwi.health.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwi.health.Models.ExcerciseModel;
import com.hwi.health.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PAWAN on 23-05-2017.
 */

public class ExcerciesAdapter extends BaseAdapter {
    ArrayList<ExcerciseModel> elist = new ArrayList<>();
    Context ctx;
    Dialog dialog;
    public ExcerciesAdapter(ArrayList<ExcerciseModel> elist, Context ctx) {
        this.elist = elist;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return elist.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vb = inflater.inflate(R.layout.excercisebase, null);
        ImageView icon = (ImageView) vb.findViewById(R.id.icon);
        TextView heading = (TextView) vb.findViewById(R.id.heading);
        TextView desc = (TextView) vb.findViewById(R.id.desc);
        //ImageView info = (ImageView) vb.findViewById(R.id.info);
        ExcerciseModel em = elist.get(i);
        heading.setText(em.getExcerciseName());
       // desc.setText(em.getDesc());
        Picasso.get().load(em.getIcon()).error(R.drawable.excer).into(icon);

       /* info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              AlertDialog();
            }
        });*/
        return vb;
    }

    void AlertDialog() {
        dialog = new Dialog(ctx, R.style.CustomDialog);
        dialog.setContentView(R.layout.excercies_popup);
        dialog.setCancelable(true);
        ImageView back = (ImageView) dialog.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

}
