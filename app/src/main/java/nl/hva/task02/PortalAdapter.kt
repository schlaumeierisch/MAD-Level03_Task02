package nl.hva.task02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nl.hva.task02.databinding.ItemPortalBinding

class PortalAdapter(private val portals: List<Portal>): RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    // function to open portal url; implemented in PortalsFragment
    var onItemClicked : ((Portal) -> Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPortalBinding.bind(itemView)

        fun databind(portal: Portal) {
            binding.tvTitle.text = portal.title
            binding.tvUrl.text = portal.url
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return portals.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val portal = portals[position]
        holder.databind(portal)

        // bind onItemClicked function
        holder.itemView.setOnClickListener {
            onItemClicked?.invoke(portal)
        }
    }
}