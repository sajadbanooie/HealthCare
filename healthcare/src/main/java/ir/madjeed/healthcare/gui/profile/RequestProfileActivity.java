package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.DoctorFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.ArrayList;


public class RequestProfileActivity extends BaseActivity {

    private DoctorFacade facade;
    private String status, srid;

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.detail) TextView detail;
    @InjectView(R.id.answer) BootstrapEditText answer;
    @InjectView(R.id.answerLayout) LinearLayout answerLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new DoctorFacade(this);
        super.onCreate(savedInstanceState);

        srid = getIntent().getExtras().getString("ID");
        ArrayList<String> info = facade.getSupervisionRequestDetail(Integer.valueOf(srid));
        title.setText(info.get(0));
        detail.setText(info.get(1));
        status = info.get(2);
        if(!status.equals("pending")){
            answer.setVisibility(View.GONE);
            answerLayout.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.approve)
    public void approve_btn(){
        String ansDetail = answer.getText().toString();
        if (ansDetail.equals("")){
            showMessage("error", "لطفا پاسخ خود را شرح دهید.");
        }else{
            facade.setSupervisionRequestAnswer(Integer.valueOf(srid), ansDetail, "accepted");
            ArrayList<String> info = facade.getSupervisionRequestDetail(Integer.valueOf(srid));
            detail.setText(info.get(1));
            answer.setVisibility(View.GONE);
            answerLayout.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.disapprove)
    public void disapprove_btn(){
        String ansDetail = answer.getText().toString();
        if (ansDetail.equals("")){
            showMessage("error", "لطفا دلیل رد درخواست را شرح دهید.");
        }else{
            facade.setSupervisionRequestAnswer(Integer.valueOf(srid), ansDetail, "rejected");
            ArrayList<String> info = facade.getSupervisionRequestDetail(Integer.valueOf(srid));
            detail.setText(info.get(1));
            answer.setVisibility(View.GONE);
            answerLayout.setVisibility(View.GONE);
        }
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_request;
    }


}
