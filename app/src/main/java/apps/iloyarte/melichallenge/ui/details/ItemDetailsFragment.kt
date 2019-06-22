package apps.iloyarte.melichallenge.ui.details

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.models.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_item_details.*
import android.content.DialogInterface



private const val ARG_ITEM = "item"

class ItemDetailsFragment : Fragment() {

    private lateinit var item: Item
    private var listener: ItemDetailsFragmentCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getSerializable(ARG_ITEM) as Item
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_details, container, false)
    }

    // Load item data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        Glide
            .with(activity!!)
            .load(item.thumbnail)
            .into(item_image)

        item_title.text = item.title

        // Launch view intent and open Mercado Libre / web explorer
        view_in_app.setOnClickListener {
            openConfirmDialog {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.permalink))
                startActivity(intent)
            }
        }
    }


    private fun openConfirmDialog(func: () -> Unit){
        val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    //Yes button clicked
                    func()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    //No button clicked
                    dialog.cancel()
                }
            }
        }

        val builder = AlertDialog.Builder(context)
        builder.setMessage("Desea abrir la publicación de Mercado Libre?")
            .setPositiveButton("Sí", dialogClickListener)
            .setNegativeButton("No", dialogClickListener)
            .show()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ItemDetailsFragmentCallback) {
            listener = context
        } else {
            throw RuntimeException("$context must implement ItemDetailsFragmentCallback")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface ItemDetailsFragmentCallback {
    }

    companion object {
        const val TAG = "ItemDetailsFragment"
        @JvmStatic
        fun newInstance(item: Item) =
            ItemDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ITEM, item)
                }
            }
    }
}
