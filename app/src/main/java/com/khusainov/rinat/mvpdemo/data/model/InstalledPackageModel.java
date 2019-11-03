package com.khusainov.rinat.mvpdemo.data.model;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public class InstalledPackageModel {
    private String mAppName;

    private String mAppPackageName;

    private Drawable mAppICon;

    public InstalledPackageModel(@NonNull String appName,
                                 @NonNull String appPackageName,
                                 @NonNull Drawable appICon) {
        mAppName = appName;
        mAppPackageName = appPackageName;
        mAppICon = appICon;
    }

    public String getAppName() {
        return mAppName;
    }

    public void setAppName(String appName) {
        mAppName = appName;
    }

    public String getAppPackageName() {
        return mAppPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        mAppPackageName = appPackageName;
    }

    public Drawable getAppICon() {
        return mAppICon;
    }

    public void setAppICon(Drawable appICon) {
        mAppICon = appICon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstalledPackageModel)) return false;

        InstalledPackageModel that = (InstalledPackageModel) o;

        if (getAppName() != null ? !getAppName().equals(that.getAppName()) : that.getAppName() != null)
            return false;
        if (getAppPackageName() != null ? !getAppPackageName().equals(that.getAppPackageName()) : that.getAppPackageName() != null)
            return false;
        return getAppICon() != null ? getAppICon().equals(that.getAppICon()) : that.getAppICon() == null;
    }

    @Override
    public int hashCode() {
        int result = getAppName() != null ? getAppName().hashCode() : 0;
        result = 31 * result + (getAppPackageName() != null ? getAppPackageName().hashCode() : 0);
        result = 31 * result + (getAppICon() != null ? getAppICon().hashCode() : 0);
        return result;
    }
}
