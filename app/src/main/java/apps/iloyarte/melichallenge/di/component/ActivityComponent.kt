package apps.iloyarte.melichallenge.di.component

import apps.iloyarte.melichallenge.di.module.ActivityModule
import apps.iloyarte.melichallenge.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}