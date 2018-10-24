package at.guetz.pirates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import at.guetz.pirates.PirateShipListFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pirateship.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 *
 */
class PirateShipRecyclerViewAdapter(
        private val values: List<PirateShip>,
        private val listener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<PirateShipRecyclerViewAdapter.ViewHolder>() {

    private val onclickListener: View.OnClickListener

    init {
        onclickListener = View.OnClickListener { v ->
            val item = v.tag as PirateShip
            listener?.onPirateShipListInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_pirateship, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.price.text = item.price.toString()
        Picasso.get().load(item.image).resize(100, 100).into(holder.image)

        with(holder.view) {
            tag = item
            setOnClickListener(onclickListener)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.item_image
        val title: TextView = view.item_title
        val price: TextView = view.item_price

        override fun toString(): String {
            return super.toString() + " '" + title + "'"
        }
    }
}
