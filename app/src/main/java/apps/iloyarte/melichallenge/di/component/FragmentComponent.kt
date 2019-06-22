package apps.iloyarte.melichallenge.di.component

import apps.iloyarte.melichallenge.di.module.FragmentModule
import apps.iloyarte.melichallenge.ui.details.ItemDetailsFragment
import apps.iloyarte.melichallenge.ui.search.SearchFragment
import apps.iloyarte.melichallenge.ui.search.SearchResultsFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(searchFragment: SearchFragment)
    fun inject(searchResultsFragment: SearchResultsFragment)
    fun inject(itemDetailsFragment: ItemDetailsFragment)

}