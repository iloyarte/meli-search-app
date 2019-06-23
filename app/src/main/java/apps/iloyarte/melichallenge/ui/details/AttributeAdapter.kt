package apps.iloyarte.melichallenge.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.models.Attribute
import apps.iloyarte.melichallenge.models.Result
import kotlinx.android.synthetic.main.item_attribute.view.*

class AttributeAdapter(
    private val mContext: Context,
    private val item: Result
) : RecyclerView.Adapter<AttributeAdapter.AttributeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributeViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_attribute, parent, false)
        return AttributeViewHolder(itemView)
    }

    override fun getItemCount(): Int = item.attributes.size

    override fun onBindViewHolder(holder: AttributeViewHolder, position: Int) {
        holder.bind(item.attributes[position])
    }

    class AttributeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(attribute: Attribute){
            view.attribute_label.text = attribute.name
            view.attribute_value.text = attribute.value_name
        }
    }
}