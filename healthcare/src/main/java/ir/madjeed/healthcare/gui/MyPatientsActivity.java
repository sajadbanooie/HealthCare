package ir.madjeed.healthcare.gui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.CustomRowObject;

import java.util.ArrayList;


public class MyPatientsActivity extends BaseActivity {

    private CustomAdapter mAdapter;
    private ArrayList<CustomRowObject> items;

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.mainListView) ListView mListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<CustomRowObject>();

        if (role.equals(R.string.admin)){
            title.setText(R.string.doctors_list);
        }else{
            title.setText(R.string.my_doctors_list);
        }

        //TODO get data from db
        items.add(new CustomRowObject("بیمار 32"));
        items.add(new CustomRowObject("بیمار 423"));

        mAdapter = new CustomAdapter(this, items);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_mypatients;
    }


    private class CustomAdapter extends ArrayAdapter<CustomRowObject> {

        private Context mContext;
        protected ArrayList<CustomRowObject> mItems;

        public CustomAdapter(Context context, ArrayList<CustomRowObject> items) {
            super(context, R.layout.mypatients_item, items);
            mContext = context;
            mItems = items;
        }

        public View getView(int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(this.mContext).inflate(R.layout.mypatients_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.name.setText(mItems.get(position).getColumn(0));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customStartActivity(ProfileActivity.class, holder.name.getText().toString());
                }
            });
            return view;
        }
    }

    class ViewHolder {
        @InjectView(R.id.name) TextView name;
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
