package com.khusainov.rinat.mvpdemo.presentation.presenter;

import com.khusainov.rinat.mvpdemo.data.model.InstalledPackageModel;
import com.khusainov.rinat.mvpdemo.data.repository.PackageInstalledRepository;
import com.khusainov.rinat.mvpdemo.presentation.view.IMainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private IMainActivity mIMainActivity;

    @Mock
    private PackageInstalledRepository mPackageInstalledRepository;

    private MainPresenter mMainPresenter;

    @Before
    public void setUp() throws Exception {
        mMainPresenter = new MainPresenter(mIMainActivity, mPackageInstalledRepository);
    }

    @Test
    public void testLoadData() {
        mMainPresenter.loadData();

        when(mPackageInstalledRepository.getData(true)).thenReturn(createTestData());

        verify(mIMainActivity).showProgress();
        verify(mIMainActivity).showData(createTestData());
    }

    public List<InstalledPackageModel> createTestData() {
        List<InstalledPackageModel> testData = new ArrayList<>();
        testData.add(new InstalledPackageModel("Sberbank", "ru.sbernakmobile", null));
        return testData;
    }
}