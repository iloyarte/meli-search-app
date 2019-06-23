package apps.iloyarte.melichallenge.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.di.component.DaggerActivityComponent
import apps.iloyarte.melichallenge.di.module.ActivityModule
import apps.iloyarte.melichallenge.models.Result
import apps.iloyarte.melichallenge.ui.details.ItemDetailsFragment
import apps.iloyarte.melichallenge.ui.search.SearchFragment
import apps.iloyarte.melichallenge.ui.search.SearchResultsFragment
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
    MainContract.View,
    SearchFragment.SearchFragmentCallback,
    SearchResultsFragment.SearchResultsFragmentCallback,
    ItemDetailsFragment.ItemDetailsFragmentCallback {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var actualFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            injectDependency()
            presenter.attach(this)
        }
    }

    override fun showSearchFragment() {
        actualFragment = SearchFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .add(R.id.container_main, actualFragment, SearchFragment.TAG)
            .commitNow()
    }

    override fun showSearchResultsFragment(query: String) {
        actualFragment = SearchResultsFragment.newInstance(query)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, actualFragment, SearchResultsFragment.TAG)
            .addToBackStack(SearchResultsFragment.TAG)
            .commit()
    }


    override fun showItemDetailsFragment(item: Result) {
        actualFragment = ItemDetailsFragment.newInstance(item)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, actualFragment, ItemDetailsFragment.TAG)
            .addToBackStack(ItemDetailsFragment.TAG)
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
        when (actualFragment) {
            is SearchResultsFragment -> {
                actualFragment = supportFragmentManager.findFragmentByTag(SearchFragment.TAG)!!
                title = getString(R.string.app_name)
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
            }
            is ItemDetailsFragment -> {
                actualFragment = supportFragmentManager.findFragmentByTag(SearchResultsFragment.TAG)!!
            }
        }
        super.onBackPressed()
    }

}
