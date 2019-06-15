package apps.iloyarte.melichallenge.di.module

import android.app.Activity
import apps.iloyarte.melichallenge.ui.main.MainActivity
import apps.iloyarte.melichallenge.ui.main.MainContract
import apps.iloyarte.melichallenge.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: MainActivity) {

    @Provides
    fun provideMainActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}