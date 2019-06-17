package apps.iloyarte.melichallenge.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.models.Item
import kotlinx.android.synthetic.main.item_layout.view.*

class SearchAdapter(
    private val context: Context, private val list: MutableList<Item>,
    fragment: Fragment
) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = fragment as onItemClickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = list[position]
         holder.bind(item)
//        holder!!.title!!.setText(post.title)
//        holder.body!!.setText(post.body)
//
//        holder.layout!!.setOnClickListener {
//            listener.itemDetail(post.id.toString()!!)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(itemView)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            with(itemView){
//                item_id.text = item.id
                item_title.text = item.title

            }
        }
    }

    interface onItemClickListener {
        fun itemDetail(itemId: String)
    }
}