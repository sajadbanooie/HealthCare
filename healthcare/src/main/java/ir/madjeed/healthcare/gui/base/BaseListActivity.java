package ir.madjeed.healthcare.gui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import org.parceler.Parcels;

import java.util.ArrayList;


public abstract class BaseListActivity extends BaseActivity {

    private CustomAdapter mAdapter;
    private ArrayList<BaseRowObject> items;
    private ArrayList<BaseRowObject> items_original;
    protected BaseListOptions listOptions;

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.mainListView) ListView mListView;
    @InjectView(R.id.filter) BootstrapEditText filter_edit_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listOptions = Parcels.unwrap(this.getIntent().getParcelableExtra("listOptions"));

        title.setText(getListTitle());
        items = getListItems();
        items_original = getListItems();

        mAdapter = new CustomAdapter(this, items, listOptions);
        mListView.setAdapter(mAdapter);


        filter_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                items.clear();
                for (int i = items_original.size()-1; i >= 0; i--) {
                    if (items_original.get(i).getColumn(0).contains(filter_edit_text.getText().toString()))
                        items.add(items_original.get(i));
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    protected abstract String getListTitle();

    protected abstract  ArrayList<BaseRowObject> getListItems();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_entity_list;
    }


    private class CustomAdapter extends ArrayAdapter<BaseRowObject> {

        private Context mContext;
        private BaseListOptions listOptions;
        protected ArrayList<BaseRowObject> mItems;

        public CustomAdapter(Context context, ArrayList<BaseRowObject> items, BaseListOptions listOptions) {
            super(context, R.layout.entity_list_item, items);
            this.mContext = context;
            this.mItems = items;
            this.listOptions = listOptions;
        }

        public View getView(final int position, View view, ViewGroup parent) {
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
                    public void onClick(View v) {
                        Intent i = new Intent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        overridePendingTransition(0, 0);
                        //it's dependent that holder name should be entity id
                        // i can change custom object to contain id too
                        //i.putExtra("ID", holder.name.getText().toString());
                        i.putExtra("ID", mItems.get(position).getPk());
                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
            }

            // on item click and show its detail
            if (listOptions.getPurpose().contains("view")){
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customStartActivity(listOptions.getListItemTargetClass(), mItems.get(position).getPk());
                        //it's dependent that holder name should be entity id
//                        customStartActivity(listOptions.getListItemTargetClass(), holder.name.getText().toString());
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
