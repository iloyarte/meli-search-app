package apps.iloyarte.melichallenge.ui.search

import apps.iloyarte.melichallenge.models.Item
import apps.iloyarte.melichallenge.ui.base.BaseContract

class SearchContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadItems(list: List<Item>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun doSearch(query: String)
    }
}