package at.guetz.pirates


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pirate_details.view.*
import android.content.DialogInterface


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PirateShipDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PirateShipDetailsFragment : Fragment(), View.OnClickListener {

    override fun onClick(p0: View?) {
        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog.setTitle(pirateShip.getFormattedGreeting())
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.dialog_button))
        { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }

    private lateinit var pirateShip: PirateShip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val pirateShipJson = it.getString(ARG_PARAM1)
            pirateShip = Gson().fromJson(pirateShipJson, PirateShip::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pirate_details, container, false)
        Picasso.get().load(pirateShip.image).into(view.image_ship)
        view.text_description.text = pirateShip.description
        view.text_title.text = pirateShip.title
        view.text_price.text = String.format("Price: %s ducats", pirateShip.price)
        view.button_greeting.setOnClickListener(this)
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: PirateShip) =
                PirateShipDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, Gson().toJson(param1))
                    }
                }
    }
}
