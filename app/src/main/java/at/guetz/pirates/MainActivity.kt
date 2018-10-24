package at.guetz.pirates

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), PirateShipListFragment.OnListFragmentInteractionListener, Manager.PirateShipCallback {

    override fun shipsLoaded(ships: List<PirateShip>) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, PirateShipListFragment()).commit()
    }

    override fun onPirateShipListInteraction(item: PirateShip?) {
        if (item == null) return
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, PirateShipDetailsFragment.newInstance(item))
                .addToBackStack("")
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Manager.init()
    }

    override fun onResume() {
        super.onResume()
        Manager.loadPirateShips(this)
    }
}
