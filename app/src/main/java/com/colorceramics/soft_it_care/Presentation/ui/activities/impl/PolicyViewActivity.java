package com.colorceramics.soft_it_care.Presentation.ui.activities.impl;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.colorceramics.soft_it_care.Models.PolicyContent;
import com.colorceramics.soft_it_care.Presentation.presenters.PolicyPresenter;
import com.colorceramics.soft_it_care.Presentation.ui.activities.PolicyView;
import com.colorceramics.soft_it_care.R;
import com.colorceramics.soft_it_care.Threading.MainThreadImpl;
import com.colorceramics.soft_it_care.domain.executor.impl.ThreadExecutor;

public class PolicyViewActivity extends BaseActivity implements PolicyView {
    private String title, url;
    private TextView policy_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");

        initializeActionBar();
        setTitle(title);

        policy_content = findViewById(R.id.policy_content);

        new PolicyPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getPolicy(url);
    }

    @Override
    public void onPolicyContentLoaded(PolicyContent policyContent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            policy_content.setText(Html.fromHtml(policyContent.getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            policy_content.setText(Html.fromHtml(policyContent.getContent()));
        }
    }
}
