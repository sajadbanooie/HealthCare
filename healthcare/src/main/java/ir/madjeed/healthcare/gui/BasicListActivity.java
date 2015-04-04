package ir.madjeed.healthcare.gui;

import android.app.Activity;
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
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.CustomRowObject;
import ir.madjeed.healthcare.gui.base.FakeDataProvider;
import ir.madjeed.healthcare.gui.base.ListOptions;
import org.parceler.Parcels;

import java.util.ArrayList;


public class BasicListActivity extends BaseActivity {

    private CustomAdapter mAdapter;
    private ArrayList<CustomRowObject> items;


    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.mainListView) ListView mListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListOptions listOptions = Parcels.unwrap(this.getIntent().getParcelableExtra("listOptions"));

        title.setText(listOptions.getType()+" list");
        items = FakeDataProvider.get_data_list(listOptions.getType(), listOptions.getCategory());

        mAdapter = new CustomAdapter(this, items, listOptions);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_entity_list;
    }


    private class CustomAdapter extends ArrayAdapter<CustomRowObject> {

        private Context mContext;
        private ListOptions listOptions;
        protected ArrayList<CustomRowObject> mItems;

        public CustomAdapter(Context context, ArrayList<CustomRowObject> items, ListOptions listOptions) {
            super(context, R.layout.entity_list_item, items);
            this.mContext = context;
            this.mItems = items;
            this.listOptions = listOptions;
        }

        public View getView(int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(this.mContext).inflate(R.layout.entity_list_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.name.setText(mItems.get(position).getColumn(0));

            // showing select button if needed
            if (!listOptions.getPurpose().contains("select")){
                holder.select.setVisibility(View.GONE);
            }
            else {
                holder.select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //it's dependent that holder name should be entity id
                                                  // i can change custom object to contain id too
                        Intent i = new Intent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        overridePendingTransition(0, 0);
                        i.putExtra("ID", holder.name.getText().toString());
                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
            }

            // on item click and show its detail
            if (listOptions.getPurpose().contains("view")){
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //it's dependent that holder name should be entity id
                        customStartActivity(listOptions.getListItemTargetClass(), holder.name.getText().toString());
                    }
                });

            }
            return view;
        }
    }

    class ViewHolder {
        @InjectView(R.id.name) TextView name;
        @InjectView(R.id.select) BootstrapButton select;
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
