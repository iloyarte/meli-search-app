package apps.iloyarte.melichallenge.ui.base

class BaseContract {
    interface Presenter<in T> {
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View
}