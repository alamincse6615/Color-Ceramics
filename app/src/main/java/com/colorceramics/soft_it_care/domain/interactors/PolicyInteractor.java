package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.PolicyContent;

public interface PolicyInteractor {
    interface CallBack {

        void onPolicyLoaded(PolicyContent policyContent);

        void onPolicyLoadError();
    }
}
