package ir.madjeed.healthcare.gui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.PatientFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.BaseListOptions;
import ir.madjeed.healthcare.gui.list.DoctorListActivity;
import ir.madjeed.healthcare.gui.list.MessageListActivity;
import ir.madjeed.healthcare.gui.profile.DoctorProfileActivity;
import ir.madjeed.healthcare.gui.profile.MessageProfileActivity;


public class SelectDoctorActivity extends BaseActivity {

    @InjectView(R.id.current_doctor) TextView current_doctor;
    @InjectView(R.id.selected_doctor) TextView selected_doctor;
    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.note) TextView note;
    @InjectView(R.id.detail) BootstrapEditText detail;

    private PatientFacade facade;
    private String selected_doctor_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facade = new PatientFacade(this);

        if (facade.getMyDoctorName(username)==null){
            title.setText("انتخاب پزشک عمومی");
            current_doctor.setText("دکتر عمومی ناظر: "+"ندارد.");
        }else{
            title.setText("تغییر پزشک عمومی");
            note.setText("دقت کنید که با انتخاب پزشک جدید، نظارت پزشک فعلی به پایان می رسد"+"\n"+
                    "زیرا شما فقط یک پزشک عمومی ناظر در هر زمان می توانید داشته باشید."+"\n");
            current_doctor.setText("دکتر عمومی ناظر: "+facade.getMyDoctorName(username));
        }

        selected_doctor.setText("پزشک انتخاب شده: --");
        selected_doctor_id = "";
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_select_doctor;
    }

    @OnClick(R.id.approve)
    public void approve() {
        if (selected_doctor_id.equals("")){
            showMessage("error", "لطفا یک پزشک انتخاب کنید.");
        }else if (detail.getText().toString().equals("")){
            showMessage("error", "شرح درخواست را بنویسید.");
        }else if (facade.getMyDoctorName(username)!=null &&
                        facade.getMyDoctorName(username).equals(facade.getDoctorName(selected_doctor_id))){
            showMessage("error", "برای تغییر پزشک ناظر باید یک پزشک جدید انتخاب کنید.");
        } else{
            facade.makeSupervisionRequest(username, selected_doctor_id, detail.getText().toString());
            customStartActivity(MessageListActivity.class, new BaseListOptions(MessageProfileActivity.class, null, "view", "mine"));
        }
    }

    @OnClick(R.id.back)
    public void back_btn() {
        super.onBackPressed();
    }

    @OnClick(R.id.select)
    public void select() {
        customStartActivity(DoctorListActivity.class, new BaseListOptions(DoctorProfileActivity.class, null, "select", "all"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null && data.hasExtra("ID")){
            selected_doctor_id = data.getStringExtra("ID");
            String name = facade.getDoctorName(selected_doctor_id);
            selected_doctor.setText("پزشک انتخاب شده: "+name);
        }
    }
}
