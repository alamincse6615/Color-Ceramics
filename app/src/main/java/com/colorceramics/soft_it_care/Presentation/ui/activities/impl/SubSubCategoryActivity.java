package com.colorceramics.soft_it_care.Presentation.ui.activities.impl;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colorceramics.soft_it_care.Models.Category;
import com.colorceramics.soft_it_care.Models.SubCategory;
import com.colorceramics.soft_it_care.Models.SubSubCategory;
import com.colorceramics.soft_it_care.Presentation.presenters.SubSubCategoryPresenter;
import com.colorceramics.soft_it_care.Presentation.ui.activities.SubCategoryView;
import com.colorceramics.soft_it_care.Presentation.ui.activities.SubSubCategoryView;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.SubCategoryAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.SubSubCategoryClickListener;
import com.colorceramics.soft_it_care.R;
import com.colorceramics.soft_it_care.Threading.MainThreadImpl;
import com.colorceramics.soft_it_care.domain.executor.impl.ThreadExecutor;

import java.util.List;

public class SubSubCategoryActivity extends BaseActivity implements SubSubCategoryView, SubSubCategoryClickListener {
    SubCategoryView subSubCategoryView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_category);

        Category category = (Category) getIntent().getSerializableExtra("category");

        initializeActionBar();
        setTitle(category.getName());



        SubSubCategoryPresenter subSubCategoryPresenter = new SubSubCategoryPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), subSubCategoryView);
        subSubCategoryPresenter.getSubSubCategories(category.getLinks().getSubCategories());
    }

    @Override
    public void setSubSubCategories(List<SubCategory> subCategories) {
        RecyclerView recyclerView = findViewById(R.id.subcategory_list);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        SubCategoryAdapter adapter = new SubCategoryAdapter(this, subCategories);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSubSubCategoryClick(SubSubCategory subSubCategory) {
        Intent intent = new Intent(this, ProductListingActivity.class);
        intent.putExtra("title", subSubCategory.getName());
        intent.putExtra("url", subSubCategory.getLinks().getProducts());
        startActivity(intent);
    }
}
