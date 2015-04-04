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
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.CustomRowObject;

import java.util.ArrayList;


public class ConsultantDetailActivity extends BaseActivity {

    private CustomAdapter mAdapter;
    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.mainListView) ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<CustomRowObject> items = new ArrayList<CustomRowObject>();

        String id = getIntent().getExtras().getString("ID");
        title.setText(id);

        items.add(new CustomRowObject( "بیمار 1", "درد دل دارم چه کار کنم"));
        items.add(new CustomRowObject( "دکتر 21", "برو دکتر ..."));
        items.add(new CustomRowObject( "بیمار 1", "عجب نصیحتی ...\nباشه حتما\n"));
        mAdapter = new CustomAdapter(this, items);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_consultant_detail;
    }

    private class CustomAdapter extends ArrayAdapter<CustomRowObject> {

        private Context mContext;
        private ArrayList<CustomRowObject> mItems;

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
                view = LayoutInflater.from(this.mContext).inflate(R.layout.consultant_detail_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.sender.setText(mItems.get(position).getColumn(0));
            holder.message.setText(mItems.get(position).getColumn(1));

            return view;
        }
    }

    class ViewHolder {
        @InjectView(R.id.sender) TextView sender;
        @InjectView(R.id.message) TextView message;
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
