package apps.iloyarte.melichallenge.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.di.component.DaggerFragmentComponent
import apps.iloyarte.melichallenge.di.module.FragmentModule
import apps.iloyarte.melichallenge.models.Result
import apps.iloyarte.melichallenge.utils.toast
import kotlinx.android.synthetic.main.fragment_search_results.*
import javax.inject.Inject

class SearchResultsFragment : Fragment(), SearchContract.View, SearchAdapter.SearchResultsAdapterCallback {

    @Inject
    lateinit var presenter: SearchContract.Presenter

    private var listener: SearchResultsFragmentCallback? = null
    private lateinit var mData: List<Result>
    private var query: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        arguments?.let {
            query = it.getString("query")
        }

        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        // Lanza la búsqueda.
        presenter.doSearch(query!!)
    }

    override fun showProgress(show: Boolean) {
        progress_wheel.visibility = if (show) View.VISIBLE else View.GONE
        result_list.visibility = if (show) View.GONE else View.VISIBLE
        empty_list_message.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorMessage(error: String) {
        activity?.toast(error)
    }

    override fun loadItems(list: List<Result>) {
        // Cargo los resultados a la lista.
        mData = list
        if (list.isEmpty()) {
            result_list.visibility = View.GONE
            empty_list_message.visibility = View.VISIBLE
        } else {
            result_list.visibility = View.VISIBLE
            empty_list_message.visibility = View.GONE

            renderList()
        }
    }

    private fun renderList() {
        // Setup 2 column grid
        result_list.layoutManager = GridLayoutManager(activity, 2)
        result_list.adapter = SearchAdapter(activity!!, mData as ArrayList, this)
    }

    override fun itemDetail(item: Result) {
        listener?.showItemDetailsFragment(item)
    }

    override fun onResume() {
        super.onResume()
        if (!query.isNullOrEmpty())
            activity?.title = query
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchResultsFragmentCallback) {
            listener = context
        } else {
            throw RuntimeException("$context must implement SearchResultsFragmentCallback")
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter.unsubscribe()
        listener = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun injectDependency() {
        val fragmentComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        fragmentComponent.inject(this)
    }

    interface SearchResultsFragmentCallback {
        fun showItemDetailsFragment(item: Result)
    }

    companion object {
        const val TAG = "SearchResultsFragment"

        @JvmStatic
        fun newInstance(query: String) =
            SearchResultsFragment().apply {
                arguments = Bundle().apply {
                    putString("query", query)
                }
            }
    }
}
