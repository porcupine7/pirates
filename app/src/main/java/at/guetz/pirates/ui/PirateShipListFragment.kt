package at.guetz.pirates.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import at.guetz.pirates.model.Manager
import at.guetz.pirates.data.PirateShip
import at.guetz.pirates.R

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PirateShipListFragment.OnListFragmentInteractionListener] interface.
 */
class PirateShipListFragment : Fragment() {

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pirateship_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = PirateShipRecyclerViewAdapter(Manager.shipList, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {

        fun onPirateShipListInteraction(item: PirateShip?)
    }
}
