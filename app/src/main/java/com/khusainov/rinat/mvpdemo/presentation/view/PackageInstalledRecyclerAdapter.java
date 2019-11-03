package com.khusainov.rinat.mvpdemo.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khusainov.rinat.mvpdemo.R;
import com.khusainov.rinat.mvpdemo.data.model.InstalledPackageModel;

import java.util.List;

public class PackageInstalledRecyclerAdapter extends RecyclerView.Adapter<PackageInstalledRecyclerAdapter.PackageInstalledViewHolder> {

    private List<InstalledPackageModel> mInstalledPackageModels;


    public PackageInstalledRecyclerAdapter(List<InstalledPackageModel> installedPackageModels) {
        mInstalledPackageModels = installedPackageModels;
    }

    @NonNull
    @Override
    public PackageInstalledViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PackageInstalledViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.package_installed_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PackageInstalledViewHolder holder, int position) {
        holder.bindView(mInstalledPackageModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mInstalledPackageModels.size();
    }

    static class PackageInstalledViewHolder extends RecyclerView.ViewHolder {

        private TextView mAppTextView;
        private TextView mPackageNameTextView;
        private ImageView mIconImageView;

        public PackageInstalledViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppTextView = itemView.findViewById(R.id.app_name_text_view);
            mPackageNameTextView = itemView.findViewById(R.id.app_package_text_view);
            mIconImageView = itemView.findViewById(R.id.app_icon_image_view);
        }

        void bindView(@NonNull InstalledPackageModel installedPackageModel) {
            mAppTextView.setText(installedPackageModel.getAppName());
            mPackageNameTextView.setText(installedPackageModel.getAppPackageName());
            mIconImageView.setImageDrawable(installedPackageModel.getAppICon());
        }
    }

}
