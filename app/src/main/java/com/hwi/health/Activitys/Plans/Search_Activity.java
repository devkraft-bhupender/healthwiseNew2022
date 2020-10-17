package com.hwi.health.Activitys.Plans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.hwi.health.Activitys.Logs.DietLog_Details;
import com.hwi.health.Models.DietLog_Models;
import com.hwi.health.Models.Eat_out_Beverage_models;
import com.hwi.health.Models.Eat_out_Plan_models;

import com.hwi.health.R;
import com.hwi.health.sqlite_database.DBHelper;
import com.hwi.health.sqlite_database.TestAdapter;

import java.util.ArrayList;
import java.util.List;

public class Search_Activity extends AppCompatActivity {

    ListView list_view;
    SearchView search;
    CustAdapter adapter;
    CustAdapter2 adapter2;
    CustAdapter3 adapter3;
    CustAdapter4 adapter4;
    ArrayList<String> clist = new ArrayList<>();
    ArrayList<String> categoryList =new ArrayList<>();
    int clistSize;
    int categoryListSize;
    public int isFiltered=0;

    ArrayList<Eat_out_Plan_models> list_food = new ArrayList<>();
    ArrayList<Eat_out_Beverage_models> list_bevg = new ArrayList<>();
    ArrayList<DietLog_Models> listdite = new ArrayList<>();
    TestAdapter mDbHelper = null;
    ProgressDialog pd=null;

    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Search");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seaarch_);
        search = (SearchView) findViewById(R.id.search);
        search.setQueryHint("Search");
        list_view = (ListView) findViewById(R.id.listview);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        Intent in = getIntent();
        code = in.getIntExtra("code", 0);
        if (code == 101) {
            clist = (ArrayList<String>) in.getSerializableExtra("data");
            clistSize=clist.size();

           // categoryList=(ArrayList<String>) in.getSerializableExtra("categories");
           // categoryListSize=categoryList.size();

           // clist.addAll(categoryList);
            Log.e("list ", clist.size() + "");
            adapter = new CustAdapter(clist,getApplicationContext());
            list_view.setAdapter(adapter);

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });

        }
        else if (code==105)
        {

            categoryList=(ArrayList<String>) in.getSerializableExtra("categories");
            categoryListSize=categoryList.size();


            Log.e("list ", categoryList.size() + "");
            adapter = new CustAdapter(categoryList,getApplicationContext());
            list_view.setAdapter(adapter);

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });
        }

        else if (code == 102) {

            DBHelper helper= new DBHelper(getApplicationContext(), DBHelper.DataBaseName, null,
                    DBHelper.Version);

            list_food =helper.getlogFood3("");
            adapter2 = new CustAdapter2(list_food, this);
            list_view.setAdapter(adapter2);
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter2.getFilter().filter(newText);
                    return false;
                }
            });


        } else if (code == 103) {

            list_bevg = (ArrayList<Eat_out_Beverage_models>) in.getSerializableExtra("data");
            adapter3 = new CustAdapter3(list_bevg, this);
            list_view.setAdapter(adapter3);
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter3.getFilter().filter(newText);
                    return false;
                }
            });
        } else if (code == 104) {
            DBHelper helper = new DBHelper(getApplicationContext(), DBHelper.DataBaseName, null,
                    DBHelper.Version);

            listdite = helper.getlogFoodDiet();
            //   listdite = (ArrayList<DietLog_Models>) in.getSerializableExtra("data");
            adapter4 = new CustAdapter4(listdite, this);
            list_view.setAdapter(adapter4);
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter4.getFilter().filter(newText);
                    return false;
                }
            });
        }
        else if (code == 106){

            DBHelper helper= new DBHelper(getApplicationContext(), DBHelper.DataBaseName, null,
                    DBHelper.Version);

            list_food =helper.getAlchoholicBeverages("");
            adapter2 = new CustAdapter2(list_food, this);
            list_view.setAdapter(adapter2);
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter2.getFilter().filter(newText);
                    return false;
                }
            });

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (code == 101 || code == 102 || code == 103) {
               // Intent intent = new Intent(Search_Activity.this, Eat_out_Plan_Details.class);
                //startActivity(intent);
                finish();
            }else if (code == 104){
                Intent intent = new Intent(Search_Activity.this, DietLog_Details.class);
                startActivity(intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if (code == 101 || code == 102 || code == 103) {
           // Intent intent = new Intent(Search_Activity.this, Eat_out_Plan_Details.class);
            //startActivity(intent);
            finish();
        }else if (code == 104){
            Intent intent = new Intent(Search_Activity.this, DietLog_Details.class);
            startActivity(intent);
            finish();
        }


    }

    class CustAdapter extends BaseAdapter implements Filterable {

        ArrayList<String> mData = new ArrayList<>();
        ArrayList<String> mStringFilterList = new ArrayList<>();
        ValueFilter valueFilter;
        Context ctx;
        int clistSize;
        int clistCategorySize;


        public CustAdapter(ArrayList<String> mData, Context ctx) {
            this.mData = mData;
            Log.e("mData", mData + "");
            Log.e("mData.this", this.mData + "");
            mStringFilterList = mData;
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return String.valueOf(mData.get(i));
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            View v = getLayoutInflater().inflate(R.layout.all_items, null);
            TextView name_of_business = (TextView) v.findViewById(R.id.text_item);

            String cb = mData.get(i);
            name_of_business.setText(cb);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = mData.get(i);
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            return v;
        }

        @Override
        public Filter getFilter() {
            if (valueFilter == null) {
                valueFilter = new CustAdapter.ValueFilter();
            }
            return valueFilter;
        }

        private class ValueFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    List filterList = new ArrayList();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).toString().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                            filterList.add(mStringFilterList.get(i));
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = mStringFilterList.size();
                    results.values = mStringFilterList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                mData = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        }
    }

    class CustAdapter2 extends BaseAdapter implements Filterable {

        ArrayList<Eat_out_Plan_models> mData = new ArrayList<>();
        ArrayList<Eat_out_Plan_models> mStringFilterList = new ArrayList<>();
        ValueFilter valueFilter;
        Context ctx;

        public CustAdapter2(ArrayList<Eat_out_Plan_models> mData, Context ctx) {
            this.mData = mData;
            mStringFilterList = mData;
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return String.valueOf(mData.get(i));
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View v = getLayoutInflater().inflate(R.layout.all_items, null);
            TextView name_of_business = (TextView) v.findViewById(R.id.text_item);

            Eat_out_Plan_models cb = mData.get(i);
            name_of_business.setText(cb.getFoodname());

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Eat_out_Plan_models name = mData.get(i);
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            return v;
        }


        @Override
        public Filter getFilter() {
            if (valueFilter == null) {
                valueFilter = new CustAdapter2.ValueFilter();
            }
            return valueFilter;
        }

        private class ValueFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    List filterList = new ArrayList();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).toString().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                            filterList.add(mStringFilterList.get(i));
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = mStringFilterList.size();
                    results.values = mStringFilterList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                mData = (ArrayList<Eat_out_Plan_models>) results.values;
                notifyDataSetChanged();
            }
        }
    }

    class CustAdapter3 extends BaseAdapter implements Filterable {

        ArrayList<Eat_out_Beverage_models> mData = new ArrayList<>();
        ArrayList<Eat_out_Beverage_models> mStringFilterList = new ArrayList<>();
        ValueFilter valueFilter;
        Context ctx;

        public CustAdapter3(ArrayList<Eat_out_Beverage_models> mData, Context ctx) {
            this.mData = mData;
            mStringFilterList = mData;
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return String.valueOf(mData.get(i));
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View v = getLayoutInflater().inflate(R.layout.all_items, null);
            TextView name_of_business = (TextView) v.findViewById(R.id.text_item);

            Eat_out_Beverage_models cb = mData.get(i);
            name_of_business.setText(cb.getFoodname());

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Eat_out_Beverage_models name = mData.get(i);
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            return v;
        }


        @Override
        public Filter getFilter() {
            if (valueFilter == null) {
                valueFilter = new CustAdapter3.ValueFilter();
            }
            return valueFilter;
        }

        private class ValueFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    List filterList = new ArrayList();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).toString().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                            filterList.add(mStringFilterList.get(i));
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = mStringFilterList.size();
                    results.values = mStringFilterList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                mData = (ArrayList<Eat_out_Beverage_models>) results.values;
                notifyDataSetChanged();
            }
        }
    }

    class CustAdapter4 extends BaseAdapter implements Filterable {

        ArrayList<DietLog_Models> mData = new ArrayList<>();
        ArrayList<DietLog_Models> mStringFilterList = new ArrayList<>();
        ValueFilter valueFilter;
        Context ctx;

        public CustAdapter4(ArrayList<DietLog_Models> mData, Context ctx) {
            this.mData = mData;
            mStringFilterList = mData;
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return String.valueOf(mData.get(i));
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View v = getLayoutInflater().inflate(R.layout.all_items, null);
            TextView name_of_business = (TextView) v.findViewById(R.id.text_item);

            DietLog_Models cb = mData.get(i);
            name_of_business.setText(cb.getFoodname());

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DietLog_Models name = mData.get(i);
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            return v;
        }

        @Override
        public Filter getFilter() {
            if (valueFilter == null) {
                valueFilter = new CustAdapter4.ValueFilter();
            }
            return valueFilter;
        }

        private class ValueFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    List filterList = new ArrayList();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).toString().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                            filterList.add(mStringFilterList.get(i));
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = mStringFilterList.size();
                    results.values = mStringFilterList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                mData = (ArrayList<DietLog_Models>) results.values;
                notifyDataSetChanged();
            }
        }

    }

}
