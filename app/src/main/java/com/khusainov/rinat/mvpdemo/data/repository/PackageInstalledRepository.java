package com.khusainov.rinat.mvpdemo.data.repository;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.khusainov.rinat.mvpdemo.R;
import com.khusainov.rinat.mvpdemo.data.model.InstalledPackageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий - провайдер данных об установленных приложениях.
 */
public class PackageInstalledRepository {

    private Context mContext;
    private final PackageManager mPackageManager;

    public PackageInstalledRepository(@NonNull Context context) {
        mContext = context;
        mPackageManager = context.getPackageManager();
    }

    public void loadDataAsync(boolean isSystem, @NonNull OnLoadingFinishListener onLoadingFinishListener) {
        LoadingPackagesAsyncTask loadingPackagesAsyncTask = new LoadingPackagesAsyncTask(onLoadingFinishListener);
        loadingPackagesAsyncTask.execute(isSystem);
    }

    public List<InstalledPackageModel> getData(boolean isSystem) {
        List<InstalledPackageModel> installedPackageModels = new ArrayList<>();

        for (String packageName : getInstalledPackages(isSystem)) {
            InstalledPackageModel installedPackageModel = new InstalledPackageModel(
                    getAppName(packageName), packageName, getAppIcon(packageName)
            );

            installedPackageModels.add(installedPackageModel);
        }

        return installedPackageModels;
    }

    private List<String> getInstalledPackages(boolean isSystem) {
        List<String> apkPackageName = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfoList = mContext.getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resolveInfoList) {

            if (isSystem || !isSystemPackage(resolveInfo)) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                apkPackageName.add(activityInfo.applicationInfo.packageName);
            }
        }
        return apkPackageName;
    }

    public String getAppName(@NonNull String packageName) {
        String appName = "";
        ApplicationInfo applicationInfo;

        try {
            applicationInfo = mPackageManager.getApplicationInfo(packageName, 0);
            appName = (String) mPackageManager.getApplicationLabel(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    private Drawable getAppIcon(@NonNull String packageName) {
        Drawable drawable;
        try {
            drawable = mPackageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            drawable = ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher);
        }
        return drawable;
    }

    private boolean isSystemPackage(@NonNull ResolveInfo resolveInfo) {
        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }


    private class LoadingPackagesAsyncTask extends AsyncTask<Boolean, Void, List<InstalledPackageModel>> {

        private final OnLoadingFinishListener mOnLoadingFinishListener;

        LoadingPackagesAsyncTask(@NonNull OnLoadingFinishListener onLoadingFinishListener) {
            mOnLoadingFinishListener = onLoadingFinishListener;
        }

        @Override
        protected void onPostExecute(List<InstalledPackageModel> installedPackageModels) {
            super.onPostExecute(installedPackageModels);

            mOnLoadingFinishListener.onFinish(installedPackageModels);
        }

        @Override
        protected List<InstalledPackageModel> doInBackground(Boolean... booleans) {
            return getData(booleans[0]);
        }
    }

    public interface OnLoadingFinishListener {
        void onFinish(List<InstalledPackageModel> packageModels);
    }
}
