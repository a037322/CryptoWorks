package pt.ismai.inf.ricardosousa.cryptoworks

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        val coinName = findViewById<TextView>(R.id.coin_name)
        coinName.text = coinModel.name

        val coinSymbol = findViewById<TextView>(R.id.coin_symbol)
        coinName.text = coinModel.symbol

        val coinPrice = findViewById<TextView>(R.id.price_usd)
        coinPrice.text = String.format("%.2f", coinModel.quote.quoteUsd.price)

        val coinPercentOneHour = findViewById<TextView>(R.id.one_hour)
        coinPercentOneHour.text = String.format("%.2f%%", coinModel.quote.quoteUsd.percent_change_1h)

        val coinPercentTwentyFourHour = findViewById<TextView>(R.id.twenty_four_hour)
        coinPercentTwentyFourHour.text = String.format("%.2f%%", coinModel.quote.quoteUsd.percent_change_24h)

        val coinPercentSevenDays = findViewById<TextView>(R.id.seven_days)
        coinPercentSevenDays.text = String.format("%.2f%%", coinModel.quote.quoteUsd.percent_change_7d)

        val percent_change_1h = coinModel.quote.quoteUsd.percent_change_1h
        val percent_change_24h = coinModel.quote.quoteUsd.percent_change_24h
        val percent_change_7d = coinModel.quote.quoteUsd.percent_change_7d

        coinPercentOneHour.setTextColor(
            if (percent_change_1h < 0) Color.parseColor("#FF0000") else Color.parseColor(
                "#32CD32"
            )
        )

        coinPercentTwentyFourHour.setTextColor(
            if (percent_change_24h < 0) Color.parseColor("#FF0000") else Color.parseColor(
                "#32CD32"
            )
        )

        coinPercentSevenDays.setTextColor(
            if (percent_change_7d < 0) Color.parseColor("#FF0000") else Color.parseColor(
                "#32CD32"
            )
        )

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