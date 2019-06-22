package apps.iloyarte.melichallenge.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.di.component.DaggerFragmentComponent
import apps.iloyarte.melichallenge.di.module.FragmentModule
import apps.iloyarte.melichallenge.models.Item
import apps.iloyarte.melichallenge.utils.toast
import kotlinx.android.synthetic.main.fragment_search_results.*
import javax.inject.Inject

class SearchResultsFragment : Fragment(), SearchContract.View, SearchAdapter.SearchResultsAdapterCallback {

    @Inject
    lateinit var presenter: SearchContract.Presenter

    private var listener: SearchResultsFragmentCallback? = null
    private lateinit var mData: List<Item>
    private var query: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        presenter.doSearch(query!!)
    }

    override fun showProgress(show: Boolean) {
        progress_wheel.visibility = if (show) View.VISIBLE else View.GONE
        item_list.visibility = if (show) View.GONE else View.VISIBLE
        empty_list_message.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorMessage(error: String) {
        activity?.toast(error)
    }

    override fun loadItems(list: List<Item>) {
        mData = list
        if (list.isEmpty()) {
            item_list.visibility = View.GONE
            empty_list_message.visibility = View.VISIBLE
        } else {
            item_list.visibility = View.VISIBLE
            empty_list_message.visibility = View.GONE

            renderList()
        }
    }

    private fun renderList() {
        // Setup 2 column grid
        item_list.layoutManager = GridLayoutManager(activity, 2)
        item_list.adapter = SearchAdapter(activity!!, mData as ArrayList, this)
    }

    override fun itemDetail(item: Item) {
        listener?.showItemDetailsFragment(item)
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

    private fun injectDependency() {
        val fragmentComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        fragmentComponent.inject(this)
    }


    interface SearchResultsFragmentCallback {
        fun showItemDetailsFragment(item: Item)
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
