package pt.ismai.inf.ricardosousa.cryptoworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    //private val userDatabase: DatabaseReference = Firebase.database.reference

    lateinit var email: EditText
    lateinit var lastName: EditText
    lateinit var  firstName: EditText
    lateinit var password: EditText
    lateinit var confirm_password: EditText
    lateinit var register_button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.editTextEmail)

        lastName = findViewById(R.id.editTextPersonLastName)

        firstName = findViewById(R.id.editTextPersonFirstName)

        password = findViewById(R.id.editTextPassword)

        confirm_password = findViewById(R.id.editTextConfirmPassword)

        register_button = findViewById(R.id.button_register)

        register_button.setOnClickListener {
            when {
                email.text.toString().isEmpty() -> {
                    Toast.makeText(baseContext, "Insira um E-mail",
                        Toast.LENGTH_SHORT).show()
                }
                password.text.toString().isEmpty() -> {
                    Toast.makeText(baseContext, "Insira uma Password válida",
                        Toast.LENGTH_SHORT).show()
                }
                password.text.toString() != confirm_password.text.toString() -> {
                    Toast.makeText(baseContext, "As Passwords são diferentes",
                        Toast.LENGTH_SHORT).show()
                }
                else -> {
                    registerUser(email.text.toString(), lastName.text.toString(), firstName.text.toString(), password.text.toString())
                }
            }
        }
    }

    private fun registerUser(email: String,lastName: String, firstName: String, password: String)
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){

                    val database = Firebase
                        .database(
                            "https://cryptoworks-79b04-default-rtdb.europe-west1.firebasedatabase.app"
                        ).reference

                    val user = User(email, lastName, firstName)
                    task.result?.user?.uid?.let { database.child("Users").child(it).setValue(user) }

                    Toast.makeText(this, "Registo Completo com sucesso", Toast.LENGTH_SHORT).show()
                    val myIntent = Intent(this, LoginActivity::class.java)
                    myIntent.putExtra("email", email)
                    startActivity(myIntent)
                    finish()
                }
                else{
                    println(task.exception)
                    Toast.makeText(baseContext, "Registo falhado",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}