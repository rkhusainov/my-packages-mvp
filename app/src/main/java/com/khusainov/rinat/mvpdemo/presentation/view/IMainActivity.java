package com.khusainov.rinat.mvpdemo.presentation.view;

import androidx.annotation.NonNull;

import com.khusainov.rinat.mvpdemo.data.model.InstalledPackageModel;

import java.util.List;

public interface IMainActivity {
    void showProgress();

    void hideProgress();

    void showData(@NonNull List<InstalledPackageModel> modelList);
}
