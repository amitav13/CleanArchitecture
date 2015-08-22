package dagger.mockmodules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainMVPPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.MainMVPCIPresenter;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import mockimpl.MockMVPCIPview;
import mockimpl.MockMainActivityPview;
import mockimpl.MockTweeterApi;
import rx.Scheduler;

@Module
public class MockTestModule {

    @Provides
    @ActivityScope
    MainMVPPresenter providesMainPresenter(TweeterApi api, Retrofit retrofit){
        return new MainMVPPresenterImpl(api, retrofit);
    }

    @ActivityScope
    @Provides
    public MockMainActivityPview providesMockMainPview(MainMVPPresenter presenter){
        MockMainActivityPview mockMainActivityPview = new MockMainActivityPview();
        presenter.registerView(mockMainActivityPview);
        return mockMainActivityPview;
    }

    @ActivityScope
    @Provides
    public TweeterApi providesMockTweeter(Retrofit retrofit, LocalDataCache dataCache, @Named(Constants.MAIN_THREAD) Scheduler mainScheduler){
        return new MockTweeterApi(retrofit, dataCache, mainScheduler);
    }

    @ActivityScope
    @Provides
    public MockMVPCIPview providesMockMVPCCIView(){
        return new MockMVPCIPview();
    }
}
