package apps.iloyarte.melichallenge.ui.main

import apps.iloyarte.melichallenge.ui.base.BaseContract


class MainContract {

    interface View : BaseContract.View {
        fun showAboutFragment()
        fun showSearchFragment()
        fun showSearchResultsFragment()
    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}