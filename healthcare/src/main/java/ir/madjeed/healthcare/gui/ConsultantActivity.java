package ir.madjeed.healthcare.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.CustomRowObject;

import java.util.ArrayList;


public class ConsultantActivity extends BaseActivity {

    private CustomAdapter mAdapter;
    private ArrayList<CustomRowObject> items;

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.mainListView) ListView mListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<CustomRowObject>();
        if (role.equals("بیمار")) {
            title.setText(R.string.consultantWithDoctor);
        }else{
            title.setText(R.string.consultantWithPatient);
        }
        mAdapter = new CustomAdapter(this, items);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_consultant;
    }

    @OnClick(R.id.addConsultant)
    public void addConsultant() {
        customStartActivity(AddConsultantActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO get data from db
//        items.clear();

        if (role.equals("بیمار")){
            items.add(new CustomRowObject("باز", "دکتر 1", "موضوع 1", "2 روز پیش"));
            items.add(new CustomRowObject("بسته", "دکتر 1", "موضوع 2", "1 ماه پیش"));
        }else{
            items.add(new CustomRowObject("باز", "بیمار 1", "موضوع 1", "2 روز پیش"));
            items.add(new CustomRowObject("بسته", "بیمار 33", "موضوع 2", "1 ماه پیش"));
        }

        mAdapter.notifyDataSetChanged();
    }

    private class CustomAdapter extends ArrayAdapter<CustomRowObject> {

        private Context mContext;
        protected ArrayList<CustomRowObject> mItems;

        public CustomAdapter(Context context, ArrayList<CustomRowObject> items) {
            super(context, R.layout.consultant_item, items);
            mContext = context;
            mItems = items;
        }

        public View getView(int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(this.mContext).inflate(R.layout.consultant_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.state.setText(mItems.get(position).getColumn(0));
            holder.audience.setText(mItems.get(position).getColumn(1));
            holder.subject.setText(mItems.get(position).getColumn(2));
            holder.time.setText(mItems.get(position).getColumn(3));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customStartActivity(ConsultantDetailActivity.class, holder.subject.getText().toString());
                }
            });
            return view;
        }
    }

    class ViewHolder {
        @InjectView(R.id.state) TextView state;
        @InjectView(R.id.time) TextView time;
        @InjectView(R.id.subject) TextView subject;
        @InjectView(R.id.audience) TextView audience;
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
