package ir.madjeed.healthcare.gui.authentication;

import android.widget.Spinner;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.InjectViews;
import ir.madjeed.healthcare.facade.AuthenticationFacade;
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

    private AuthenticationFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facade = new AuthenticationFacade(this);
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
            if (facade.userExists(editTexts.get(0).getText().toString())){
                showMessage("error", getString(R.string.invalid_username));
            }else{
                facade.registerUser(editTexts.get(0).getText().toString(), editTexts.get(1).getText().toString(),
                        editTexts.get(3).getText().toString(), editTexts.get(4).getText().toString(),
                        editTexts.get(5).getText().toString(), role.getSelectedItem().toString());
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
