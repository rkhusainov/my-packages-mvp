package com.khusainov.rinat.mvpdemo.presentation.presenter;

import androidx.annotation.NonNull;

import com.khusainov.rinat.mvpdemo.data.model.InstalledPackageModel;
import com.khusainov.rinat.mvpdemo.data.repository.PackageInstalledRepository;
import com.khusainov.rinat.mvpdemo.presentation.view.IMainActivity;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainPresenter {

    private WeakReference<IMainActivity> mMainActivityWeakReference;

    private PackageInstalledRepository mPackageInstalledRepository;

    public MainPresenter(@NonNull IMainActivity mainActivity,
                         @NonNull PackageInstalledRepository packageInstalledRepository) {
        mMainActivityWeakReference = new WeakReference<>(mainActivity);
        mPackageInstalledRepository = packageInstalledRepository;
    }



    public void loadData() {
        if (mMainActivityWeakReference.get() != null) {
            mMainActivityWeakReference.get().showProgress();
        }

        List<InstalledPackageModel> data = mPackageInstalledRepository.getData(true);

        if (mMainActivityWeakReference.get() != null) {
            mMainActivityWeakReference.get().hideProgress();

            mMainActivityWeakReference.get().showData(data);
        }
    }
}
