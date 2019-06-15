package apps.iloyarte.melichallenge.di.component

import apps.iloyarte.melichallenge.BaseApp
import apps.iloyarte.melichallenge.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}