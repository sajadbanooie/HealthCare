package ir.madjeed.healthcare.gui.patient;

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
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.CustomRowObject;

import java.util.ArrayList;
import java.util.Random;


public class SicknessHistoryActivity extends BaseActivity {

    private CustomAdapter mAdapter;
    private ArrayList<CustomRowObject> items;

    @InjectView(R.id.mainListView) ListView mListView;
    @InjectView(R.id.add_sickness_record_btn) BootstrapButton add_sickness_btn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<CustomRowObject>();
        if (!(role.equals("پزشک عمومی") || role.equals("پزشک متخصص"))) { // or he is not my patient
            add_sickness_btn.setVisibility(View.GONE);
        }
        mAdapter = new CustomAdapter(this, items);
        mListView.setAdapter(mAdapter);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_sickness_history;
    }

    @OnClick(R.id.add_sickness_record_btn)
    public void add_sickness_btn() {
        customStartActivity(AddSicknessActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO get data from db
//        items.clear();

        items.add(new CustomRowObject("سابقه بیماری " + String.valueOf(new Random().nextInt(1000)+10)));

        mAdapter.notifyDataSetChanged();
    }

    private class CustomAdapter extends ArrayAdapter<CustomRowObject> {

        private Context mContext;
        protected ArrayList<CustomRowObject> mItems;

        public CustomAdapter(Context context, ArrayList<CustomRowObject> items) {
            super(context, R.layout.sickness_item, items);
            mContext = context;
            mItems = items;
        }

        public View getView(int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(this.mContext).inflate(R.layout.sickness_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.name.setText(mItems.get(position).getColumn(0));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customStartActivity(SicknessDetailActivity.class, holder.name.getText().toString());
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
