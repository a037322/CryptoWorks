package pt.ismai.inf.ricardosousa.cryptoworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyCoinsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_coins)
        supportActionBar?.hide()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        switchActivities(bottomNavigationView)
    }
    private fun switchActivities(bottomNavigationView: BottomNavigationView) {


        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.my_coins_icon -> {

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