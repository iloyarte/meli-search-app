package apps.iloyarte.melichallenge.di.module

import apps.iloyarte.melichallenge.ui.search.SearchContract
import apps.iloyarte.melichallenge.ui.search.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideSearchPresenter(): SearchContract.Presenter {
        return SearchPresenter()
    }

//    @Provides
//    fun provideApiService(): ApiServiceInterface {
//        return ApiServiceInterface.create()
//    }
}