package apps.iloyarte.melichallenge.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.di.component.DaggerFragmentComponent
import apps.iloyarte.melichallenge.di.module.FragmentModule
import apps.iloyarte.melichallenge.models.Item
import apps.iloyarte.melichallenge.utils.toast
import kotlinx.android.synthetic.main.fragment_search_results.*
import javax.inject.Inject

class SearchResultsFragment : Fragment(), SearchContract.View {

    @Inject
    lateinit var presenter: SearchContract.Presenter

    private var listener: SearchResultsFragmentCallback? = null
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
        presenter.subscribe()
        presenter.doSearch(query!!)
    }

    override fun showProgress(show: Boolean) {
        progress_wheel.visibility = if (show) View.VISIBLE else View.GONE
        item_list.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorMessage(error: String) {
        activity?.toast(error)
    }

    override fun loadItems(list: List<Item>) {
        if (list.isEmpty()) {
            item_list.visibility = View.GONE
            empty_list_message.visibility = View.VISIBLE
        } else {
            item_list.visibility = View.VISIBLE
            empty_list_message.visibility = View.GONE

            loadList(list)
        }
    }

    fun loadList(list: List<Item>) {

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
        listener = null
    }

    private fun injectDependency() {
        val fragmentComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        fragmentComponent.inject(this)
    }


    interface SearchResultsFragmentCallback

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
