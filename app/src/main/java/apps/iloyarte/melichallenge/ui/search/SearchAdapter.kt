package apps.iloyarte.melichallenge.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.models.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class SearchAdapter(
    private val context: Context, private val list: MutableList<Item>,
    fragment: Fragment
) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    private val listener: SearchResultsAdapterCallback

    init {
        this.listener = fragment as SearchResultsAdapterCallback
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
//        holder!!.title!!.setText(post.title)
//        holder.body!!.setText(post.body)
//
        holder.itemView.setOnClickListener {
            listener.itemDetail(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(itemView)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            with(itemView) {
                // Load image asynchronously
                Glide
                    .with(context)
                    .load(item.thumbnail)
                    .into(item_thumbnail)

                item_title.text = item.title
                item_price.text = "$${(item.original_price ?: item.price)}"

            }
        }
    }

    interface SearchResultsAdapterCallback {
        fun itemDetail(item: Item)
    }
}