package ir.madjeed.healthcare.gui;

import android.widget.Spinner;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.InjectViews;
import ir.madjeed.healthcare.data.User;
import ir.madjeed.healthcare.data.impl.persistent.entity.UserPersistent;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import android.os.Bundle;
import ir.madjeed.healthcare.R;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import java.util.List;



public class RegisterActivity extends BaseActivity {

    @InjectViews({ R.id.username, R.id.password, R.id.passwordRepeat, R.id.name, R.id.family, R.id.nationalID})
    List<BootstrapEditText> editTexts;

    @InjectView(R.id.role)
    Spinner role;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_register;
    }

    protected void validateForm() {
        int correct = 0;
        for (BootstrapEditText item : editTexts) {
            if(item.getText().toString().equals("")){
                item.setState(BootstrapEditText.TextState.DANGER);
            }else{
                item.setState(BootstrapEditText.TextState.SUCCESS);
                correct++;
            }
        }
        if (correct!=editTexts.size()){
            showMessage("error", getString(R.string.invalid_form));
        }else if(!editTexts.get(1).getText().toString().equals(editTexts.get(2).getText().toString())){
            editTexts.get(1).setState(BootstrapEditText.TextState.DANGER);
            editTexts.get(2).setState(BootstrapEditText.TextState.DANGER);
            showMessage("error", getString(R.string.not_matched_password));
        }else{
            if (repo.getRepoUsers().getByID(editTexts.get(0).getText().toString()) != null){
                showMessage("error", getString(R.string.invalid_username));
            }else{
                User user = new UserPersistent(editTexts.get(0).getText().toString(), editTexts.get(1).getText().toString(),
                        editTexts.get(3).getText().toString(), editTexts.get(4).getText().toString(),
                        editTexts.get(5).getText().toString(), role.getSelectedItem().toString());

                user.save(repo);
                customStartActivity(LoginActivity.class);
            }
        }
    }

    @OnClick(R.id.register)
    public void on_register() {
        this.validateForm();
    }

    @OnClick(R.id.back)
    public void on_back() {
        super.onBackPressed();
    }

}
