package apps.iloyarte.melichallenge.ui.search

import apps.iloyarte.melichallenge.models.Result
import apps.iloyarte.melichallenge.ui.base.BaseContract

class SearchContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadItems(list: List<Result>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun doSearch(query: String)
    }
}