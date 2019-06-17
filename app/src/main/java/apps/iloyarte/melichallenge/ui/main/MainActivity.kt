package apps.iloyarte.melichallenge.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.di.component.DaggerActivityComponent
import apps.iloyarte.melichallenge.di.module.ActivityModule
import apps.iloyarte.melichallenge.ui.search.SearchFragment
import apps.iloyarte.melichallenge.ui.search.SearchResultsFragment
import javax.inject.Inject
import android.view.MenuItem


class MainActivity : AppCompatActivity(), MainContract.View, SearchFragment.SearchFragmentCallback,
    SearchResultsFragment.SearchResultsFragmentCallback {
    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        presenter.attach(this)
        // A splash or some intro may be added here.
    }

    override fun showAboutFragment() {

    }

    override fun showSearchFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
//            .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
            .add(R.id.container_main, SearchFragment.newInstance(), SearchFragment.TAG)
            .commitNow()
    }

    override fun showSearchResultsFragment(query: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportFragmentManager.beginTransaction()
//            .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
            .replace(R.id.container_main, SearchResultsFragment.newInstance(query), SearchResultsFragment.TAG)
            .addToBackStack(SearchResultsFragment.TAG)
            .commit()
    }

    override fun search(query: String) {
        showSearchResultsFragment(query)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0){
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }

        super.onBackPressed()
    }

}
