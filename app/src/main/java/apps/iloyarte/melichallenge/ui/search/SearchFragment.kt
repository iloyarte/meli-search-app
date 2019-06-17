package apps.iloyarte.melichallenge.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.utils.onSubmit
import apps.iloyarte.melichallenge.utils.toast
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : Fragment() {

    private var listener: SearchFragmentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_input.onSubmit {
            search_button.performClick()
        }

        search_button.setOnClickListener {
            if (!search_input.text.isNullOrEmpty()) {
                listener?.search(search_input.text.toString())
            } else {
                activity?.toast("Debe ingresar una búsqueda.")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchFragmentCallback) {
            listener = context
        } else {
            throw RuntimeException("$context must implement SearchFragmentCallback")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface SearchFragmentCallback {
        fun search(query: String)
    }

    companion object {
        const val TAG = "SearchFragment"
        fun newInstance() = SearchFragment()
    }

}
