package apps.iloyarte.melichallenge.ui.search

import apps.iloyarte.melichallenge.api.SearchResponse
import apps.iloyarte.melichallenge.api.SearchServiceAPI
import apps.iloyarte.melichallenge.models.Address
import apps.iloyarte.melichallenge.models.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter : SearchContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: SearchServiceAPI = SearchServiceAPI.create()
    private lateinit var view: SearchContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attach(view: SearchContract.View) {
        this.view = view
    }

    override fun doSearch(query: String) {
        var subscribe = api.search(query).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: SearchResponse ->
                val items = ArrayList<Item>()

                response.results.forEach {
                    val item = Item(
                        it.id,
                        it.site_id,
                        it.title,
                        it.price,
                        it.currency_id,
                        it.condition,
                        it.permalink,
                        it.thumbnail,
                        it.accepts_mercadopago,
                        Address(
                            it.address.state_id,
                            it.address.state_name,
                            it.address.city_id,
                            it.address.city_name
                        ),
                        it.original_price,
                        it.tags
                    )

                    items.add(item)
                }

                view.loadItems(items)
                view.showProgress(false)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)



        view.loadItems(emptyList())
        view.showProgress(false)
    }
}