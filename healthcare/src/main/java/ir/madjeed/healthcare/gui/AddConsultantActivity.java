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


public class AddConsultantActivity extends BaseActivity {

//    protected CustomAdapter mAdapter;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        LayoutInflater mInflater = LayoutInflater.from(this);
//        ListView mListView = (ListView) findViewById(R.id.mainListView);
//
//        ArrayList<CustomRowObject> items = new ArrayList<CustomRowObject>();
//        items.add(new CustomRowObject("باز", "آی دلم ...", "2 روز پیش"));
//        items.add(new CustomRowObject("بسته", "شب ادراری", "1 ماه پیش"));
//
//        mAdapter = new CustomAdapter(this, items);
//        mListView.setAdapter(mAdapter);
//
//    }
//
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_consultant;
    }
//
//
//    private class CustomAdapter extends ArrayAdapter<CustomRowObject> {
//
//        protected Context mContext;
//        protected ArrayList<CustomRowObject> mItems;
//
//        public CustomAdapter(Context context, ArrayList<CustomRowObject> items) {
//            super(context, R.layout.consultant_item, items); // Use a custom layout file
//            mContext = context;
//            mItems = items;
//        }
//
//        public View getView(int position, View view, ViewGroup parent) {
//            ViewHolder holder;
//            if (view != null) {
//                holder = (ViewHolder) view.getTag();
//            } else {
//                view = LayoutInflater.from(this.mContext).inflate(R.layout.consultant_item, null);
//                holder = new ViewHolder(view);
//                view.setTag(holder);
//            }
//            holder.state.setText(mItems.get(position).getColumn(0));
//            holder.subject.setText(mItems.get(position).getColumn(1));
//            holder.time.setText(mItems.get(position).getColumn(2));
//
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    customStartActivity(AddConsultantActivity.class);
//                }
//            });
//            return view;
//        }
//    }
//
//    class ViewHolder {
//        @InjectView(R.id.state) TextView state;
//        @InjectView(R.id.time) TextView time;
//        @InjectView(R.id.subject) TextView subject;
//        public ViewHolder(View view) {
//            ButterKnife.inject(this, view);
//        }
//    }
}
