package udb.dsa.desafiopractico1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var email: TextView
    private lateinit var password1: TextInputEditText
    private lateinit var password2: TextInputEditText
    private lateinit var button: Button
    private lateinit var hook: TextView

    private lateinit var firebase: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sgin_up)

        email = findViewById(R.id.emailSignUp)
        password1 = findViewById(R.id.passwordOneSignUp)
        password2 = findViewById(R.id.passwordTwoSignUp)
        button = findViewById(R.id.signUpButton)
        hook = findViewById(R.id.loginHook)

        firebase = FirebaseAuth.getInstance()

        button.setOnClickListener{signUp()}

        hook.setOnClickListener{ navigateToLogin() }
    }

    private fun signUp() {
        val emailText = email.text
        val passOText = password1.text
        val passTText = password2.text

        if(emailText == null || passOText == null || passTText == null) {
            Toast.makeText(this, "Por favor, llene los cammpos", Toast.LENGTH_SHORT).show()
            return
        }

        if(emailText.isEmpty() || passOText.isEmpty() || passTText.isEmpty()) {
            Toast.makeText(this, "Por favor, llene los cammpos", Toast.LENGTH_SHORT).show()
            return
        }

        if (passOText.toString() != passTText.toString()) {
            Toast.makeText(this, "Ambas contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
            return
        }

        firebase.createUserWithEmailAndPassword(emailText.toString(), passOText.toString()).addOnCompleteListener {
            if(it.isSuccessful) {
                navigateToMain()
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
    }
}