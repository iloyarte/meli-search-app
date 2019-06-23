package apps.iloyarte.melichallenge.ui.search

import apps.iloyarte.melichallenge.api.dto.SearchResponse
import apps.iloyarte.melichallenge.api.SearchServiceAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter : SearchContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: SearchServiceAPI = SearchServiceAPI.create()
    private lateinit var view: SearchContract.View


    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: SearchContract.View) {
        this.view = view
    }

    override fun doSearch(query: String) {
        val subscribe = api.search(query).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: SearchResponse ->

                view.showProgress(false)
                view.loadItems(response.results)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }
}