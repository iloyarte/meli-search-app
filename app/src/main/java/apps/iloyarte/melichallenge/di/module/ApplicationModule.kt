package apps.iloyarte.melichallenge.di.module

import android.app.Application
import apps.iloyarte.melichallenge.BaseApp
import apps.iloyarte.melichallenge.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}