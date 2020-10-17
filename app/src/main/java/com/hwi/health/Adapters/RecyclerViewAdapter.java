package com.hwi.health.Adapters;

import android.content.Context;
import android.graphics.Color;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.Models.RecycleModel;
import com.hwi.health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanay on 05-12-2016.
 */

public class RecyclerViewAdapter extends Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context ctx;
    private List<RecycleModel> alist = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView one, two, three, four, five, six;
        public LinearLayout back;


        public MyViewHolder(View view) {
            super(view);
            one = (TextView) view.findViewById(R.id.one);
            two = (TextView) view.findViewById(R.id.two);
            three = (TextView) view.findViewById(R.id.three);
            four = (TextView) view.findViewById(R.id.four);
            five = (TextView) view.findViewById(R.id.five);
            six = (TextView) view.findViewById(R.id.six);

            back = (LinearLayout) view.findViewById(R.id.back);
        }
    }

    public RecyclerViewAdapter(List<RecycleModel> alist, Context ctx) {
        this.alist = alist;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recbase, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        RecycleModel b = alist.get(position);

        holder.one.setText(b.getOne());
        holder.two.setText(b.getTwo());
        holder.three.setText(b.getThree());
        holder.four.setText(b.getFour());
        holder.five.setText(b.getFive());
        holder.six.setText(b.getSix());
        holder.back.setBackgroundColor(Color.parseColor(b.getColor()));


    }

    @Override
    public int getItemCount() {
        return alist.size();
    }

}

