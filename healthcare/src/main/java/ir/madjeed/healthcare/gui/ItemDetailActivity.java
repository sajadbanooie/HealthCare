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


public class ItemDetailActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.detail) TextView detail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String id = getIntent().getExtras().getString("ID");
        title.setText(id);
        detail.setText("detail of "+ id);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_item_detail;
    }

}
