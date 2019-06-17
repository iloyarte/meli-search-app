package apps.iloyarte.melichallenge

import android.app.Application
import apps.iloyarte.melichallenge.di.component.ApplicationComponent
import apps.iloyarte.melichallenge.di.component.DaggerApplicationComponent
import apps.iloyarte.melichallenge.di.module.ApplicationModule

class BaseApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}