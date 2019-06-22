package apps.iloyarte.melichallenge.ui.main


class MainPresenter : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun unsubscribe() {
    }

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showSearchFragment() // as default
    }

}