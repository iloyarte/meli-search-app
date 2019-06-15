package apps.iloyarte.melichallenge.ui.base

abstract class BasePresenter<T: BaseContract> {

    protected lateinit var view: T

}