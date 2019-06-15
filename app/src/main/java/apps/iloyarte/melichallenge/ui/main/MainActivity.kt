package apps.iloyarte.melichallenge.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.di.module.ActivityModule
import apps.iloyarte.melichallenge.ui.search.SearchFragment
import apps.iloyarte.melichallenge.ui.search.SearchResultsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View  {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        injectDependency()

        presenter.attach(this)
        // A splash or some intro may be added here.


    }

    override fun showAboutFragment() {

    }

    override fun showSearchFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
//            .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
            .replace(R.id.container_main, SearchFragment.newInstance(), SearchFragment.TAG)
            .commit()
    }

    override fun showSearchResultsFragment() {
        supportFragmentManager.beginTransaction()
//            .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
            .replace(R.id.container_main, SearchResultsFragment.newInstance(), SearchFragment.TAG)
            .commit()
    }

//    private fun injectDependency() {
//        val activityComponent = DaggerActivityComponent.builder()
//            .activityModule(ActivityModule(this))
//            .build()
//
//        activityComponent.inject(this)
//    }


}
