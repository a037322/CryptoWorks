package pt.ismai.inf.ricardosousa.cryptoworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_buy.*
import pt.ismai.inf.ricardosousa.cryptoworks.Model.CoinModel

class BuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
        supportActionBar?.hide()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        switchActivities(bottomNavigationView)
        val coinModel = intent.getSerializableExtra("coin") as CoinModel

        val coinName = findViewById<TextView>(R.id.coin_buy_name)
        coinName.text = coinModel.name

    }
    private fun switchActivities(bottomNavigationView: BottomNavigationView) {


        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.my_coins_icon -> {
                    val intent0 = Intent(this, MyCoinsActivity::class.java)
                    startActivity(intent0)
                }
                R.id.home_icon -> {
                    val intent1 = Intent(this, HomeActivity::class.java)
                    startActivity(intent1)
                }
                R.id.profile_icon -> {
                    val intent2 = Intent(this, ProfileActivity::class.java)
                    startActivity(intent2)
                }
            }
            true
        }
    }
}