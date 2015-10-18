package com.experiences.projects.booktable;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AccountActivity extends BaseActivity {

    private LinearLayout llAccount;
    private EditText edtFirstName,edtLastName,edtEmail,edtPhone;
    @Override
    public void createActivity() {
        llAccount = (LinearLayout) layoutInflater.inflate(R.layout.activity_account, null);
        llContent.addView(llAccount, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tvTitle.setText("Account Details");
        btnSave.setVisibility(View.VISIBLE);
        defineControls();
    }

    private void defineControls()
    {
        edtFirstName   =  (EditText)findViewById(R.id.edtFirstName);
        edtLastName    =  (EditText)findViewById(R.id.edtLastName);
        edtEmail       =  (EditText)findViewById(R.id.edtEmail);
        edtPhone       =  (EditText)findViewById(R.id.edtPhone);
    }
}
