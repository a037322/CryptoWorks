package pt.ismai.inf.ricardosousa.cryptoworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginbutton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val registeredEmail = intent.getStringExtra("email")
        findViewById<EditText>(R.id.editTextEmailAddress).apply{
            this.setText(registeredEmail)
        }


        auth = Firebase.auth

        email = findViewById(R.id.editTextEmailAddress)

        password = findViewById(R.id.editTextPassword)

        loginbutton = findViewById(R.id.button_login)

        val register = findViewById<Button>(R.id.button_register)
        register.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
        }

        loginbutton.setOnClickListener {
            if(email.text.toString().isEmpty() || password.text.toString().isEmpty()){
                Toast.makeText(baseContext, "Insira um E-mail",
                    Toast.LENGTH_SHORT).show()
            }
            else {
                signIn(email.text.toString(), password.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun signIn(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this, "Está Logged", Toast.LENGTH_SHORT).show()
                    val myIntent = Intent(this, HomeActivity::class.java)
                    myIntent.putExtra("email",email)
                    startActivity(myIntent)
                    finish()
                }
                else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Autenticação Falhada",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(baseContext, "Insira um E-mail válido",
                    Toast.LENGTH_SHORT).show()
            }
    }

    private fun reload() {

    }

    fun updateUI(user: FirebaseUser?) {

    }
}