package pt.ismai.inf.ricardosousa.cryptoworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        switchActivities(bottomNavigationView)

        (application as CryptoWorksApplication).loggedUser?.let {
            val (email, lastName, firstName) = it

            val emailView = findViewById<TextView>(R.id.textEmail)
            emailView.text = email

            val lastNameView = findViewById<TextView>(R.id.textPersonLastName)
            lastNameView.text = lastName

            val firstNameView = findViewById<TextView>(R.id.textPersonFirstName)
            firstNameView.text = firstName
        }
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

                }
            }
            true
        }
    }
}