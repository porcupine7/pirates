package at.guetz.pirates.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import at.guetz.pirates.model.Manager
import at.guetz.pirates.data.PirateShip
import at.guetz.pirates.R

class MainActivity : AppCompatActivity(), PirateShipListFragment.OnListFragmentInteractionListener, Manager.PirateShipCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Manager.init()
    }

    override fun onStart() {
        super.onStart()
        Manager.loadPirateShips(this)
    }

    override fun shipsLoaded(ships: List<PirateShip>) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, PirateShipListFragment()).commit()
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onPirateShipListInteraction(item: PirateShip?) {
        if (item == null) return
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, PirateShipDetailsFragment.newInstance(item))
                .addToBackStack("")
                .commit()
    }
}
