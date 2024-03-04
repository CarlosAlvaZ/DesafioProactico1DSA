package udb.dsa.desafiopractico1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var email: TextView
    private lateinit var password1: TextInputEditText
    private lateinit var button: Button
    private lateinit var hook: TextView

    private lateinit var firebase: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        email = findViewById(R.id.emailLogin)
        password1 = findViewById(R.id.passwordLogin)
        button = findViewById(R.id.loginButton)
        hook = findViewById(R.id.signupHook)

        firebase = FirebaseAuth.getInstance()

        button.setOnClickListener{signUp()}

        hook.setOnClickListener{ navigateToSignup() }
    }


    private fun signUp() {
        val emailText = email.text
        val passOText = password1.text

        if(emailText == null || passOText == null) {
            Toast.makeText(this, "Por favor, llene los cammpos", Toast.LENGTH_SHORT).show()
            return
        }

        if(emailText.isEmpty() || passOText.isEmpty()) {
            Toast.makeText(this, "Por favor, llene los cammpos", Toast.LENGTH_SHORT).show()
            return
        }

        firebase.signInWithEmailAndPassword(emailText.toString(), passOText.toString()).addOnCompleteListener {
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

    private fun navigateToSignup() {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }
}