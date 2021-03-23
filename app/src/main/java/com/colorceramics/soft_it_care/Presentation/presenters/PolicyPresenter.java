package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.PolicyContent;
import com.colorceramics.soft_it_care.Presentation.ui.activities.PolicyView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.PolicyInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.PolicyInteractorImpl;

public class PolicyPresenter extends AbstractPresenter implements PolicyInteractor.CallBack {
    private PolicyView policyView;
    private int type = 0;

    public PolicyPresenter(Executor executor, MainThread mainThread, PolicyView policyView) {
        super(executor, mainThread);
        this.policyView = policyView;
    }

    public void getPolicy(String url){
        new PolicyInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    @Override
    public void onPolicyLoaded(PolicyContent policyContent) {
        if (policyView != null){
            policyView.onPolicyContentLoaded(policyContent);
        }
    }

    @Override
    public void onPolicyLoadError() {

    }
}
