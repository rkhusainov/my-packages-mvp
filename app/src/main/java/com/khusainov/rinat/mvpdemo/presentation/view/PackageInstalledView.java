package com.khusainov.rinat.mvpdemo.presentation.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khusainov.rinat.mvpdemo.R;
import com.khusainov.rinat.mvpdemo.data.model.InstalledPackageModel;
import com.khusainov.rinat.mvpdemo.data.repository.PackageInstalledRepository;
import com.khusainov.rinat.mvpdemo.presentation.presenter.PackageInstalledPresenter;

import java.util.List;

public class PackageInstalledView extends AppCompatActivity implements IPackageInstalledView {

    private static final String TAG = "TAG";
    private RecyclerView mRecyclerView;
    private View mProgressFrameLayout;
    private PackageInstalledPresenter mPackageInstalledPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        providePresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPackageInstalledPresenter.loadDataAsync();
    }

    void providePresenter() {
        PackageInstalledRepository packageInstalledRepository = new PackageInstalledRepository(this);
        mPackageInstalledPresenter = new PackageInstalledPresenter(this, packageInstalledRepository);
    }

    private void initView() {

        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mProgressFrameLayout = findViewById(R.id.progress_frame_layout);
    }

    @Override
    public void showProgress() {
        mProgressFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressFrameLayout.setVisibility(View.GONE);
    }

    @Override
    public void showData(@NonNull List<InstalledPackageModel> modelList) {
        PackageInstalledRecyclerAdapter adapter = new PackageInstalledRecyclerAdapter(modelList);
        mRecyclerView.setAdapter(adapter);
    }
}
