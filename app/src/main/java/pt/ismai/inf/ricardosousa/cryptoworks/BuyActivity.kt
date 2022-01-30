package pt.ismai.inf.ricardosousa.cryptoworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_buy.*
import pt.ismai.inf.ricardosousa.cryptoworks.Model.CoinModel

class BuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        val coinModel = intent.getSerializableExtra("coin") as CoinModel

        val coinName = findViewById<TextView>(R.id.coin_buy_name)
        coinName.text = coinModel.name

    }
}