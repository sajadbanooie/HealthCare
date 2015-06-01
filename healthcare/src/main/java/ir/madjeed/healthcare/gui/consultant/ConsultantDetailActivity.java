package ir.madjeed.healthcare.gui.consultant;

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
import ir.madjeed.healthcare.facade.ConsultantFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.CustomRowObject;

import java.util.ArrayList;


public class ConsultantDetailActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.mainListView) ListView mListView;
    @InjectView(R.id.your_message) TextView your_message;

    private CustomAdapter mAdapter;
    private ArrayList<CustomRowObject> items;
    private String consultant_id;

    private ConsultantFacade facade;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new ConsultantFacade(this);
        super.onCreate(savedInstanceState);

        items = new ArrayList<CustomRowObject>();

        consultant_id = getIntent().getExtras().getString("ID");
        title.setText(consultant_id);

        ArrayList<CustomRowObject> consultantCaseMessages = facade.getConsultantCaseMessages(consultant_id);
        items.addAll(consultantCaseMessages);
        mAdapter = new CustomAdapter(this, items);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_consultant_detail;
    }


    @OnClick(R.id.send_message)
    public void addMessage() {
        facade.addConsultantMessage(consultant_id, your_message.getText().toString(), username);
        items.clear();
        ArrayList<CustomRowObject> consultantCaseMessages = facade.getConsultantCaseMessages(consultant_id);
        items.addAll(consultantCaseMessages);
        mAdapter.notifyDataSetChanged();
        your_message.setText("");
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
