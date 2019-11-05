package com.khusainov.rinat.mvpdemo.presentation.presenter;

import androidx.annotation.NonNull;

import com.khusainov.rinat.mvpdemo.data.model.InstalledPackageModel;
import com.khusainov.rinat.mvpdemo.data.repository.PackageInstalledRepository;
import com.khusainov.rinat.mvpdemo.presentation.view.IPackageInstalledView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Presenter главного экрана приложения.
 */
public class PackageInstalledPresenter {

    private WeakReference<IPackageInstalledView> mMainActivityWeakReference;
    private PackageInstalledRepository mPackageInstalledRepository;

    public PackageInstalledPresenter(@NonNull IPackageInstalledView mainActivity,
                                     @NonNull PackageInstalledRepository packageInstalledRepository) {
        mMainActivityWeakReference = new WeakReference<>(mainActivity);
        mPackageInstalledRepository = packageInstalledRepository;
    }

    /**
     * Метод для получения данных в синхронном режиме.
     */
    // Данный метод нужен исключительно для понимания работы Unit-тестов.
    public void loadDataSync() {
        if (mMainActivityWeakReference.get() != null) {
            mMainActivityWeakReference.get().showProgress();
        }

        List<InstalledPackageModel> data = mPackageInstalledRepository.getData(true);

        if (mMainActivityWeakReference.get() != null) {
            mMainActivityWeakReference.get().hideProgress();

            mMainActivityWeakReference.get().showData(data);
        }
    }

    /**
     * Метод для загрузки данных в ассинхронном режиме.
     */
    public void loadDataAsync() {
        if (mMainActivityWeakReference.get() != null) {
            mMainActivityWeakReference.get().showProgress();
        }

        PackageInstalledRepository.OnLoadingFinishListener onLoadingFinishListener = packageModels -> {
            if (mMainActivityWeakReference.get() != null) {
                mMainActivityWeakReference.get().hideProgress();
                mMainActivityWeakReference.get().showData(packageModels);
            }
        };

        mPackageInstalledRepository.loadDataAsync(true, onLoadingFinishListener);
    }

    /**
     * Метод для отвязки прикрепленной View.
     */
    public void detachView() {
        mMainActivityWeakReference.clear();
    }
}
