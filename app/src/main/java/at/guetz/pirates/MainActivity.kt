package at.guetz.pirates

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), PirateShipFragment.OnListFragmentInteractionListener, Manager.PirateShipCallback {

    override fun shipsLoaded(ships: List<PirateShip>) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, PirateShipFragment()).commit()
    }

    override fun onPirateShipListInteraction(item: PirateShip?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
