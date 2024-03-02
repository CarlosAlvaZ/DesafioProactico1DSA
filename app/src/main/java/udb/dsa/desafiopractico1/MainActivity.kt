package udb.dsa.desafiopractico1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    // Variables para los botones de la vista principal
    lateinit var buttonEcuacion: Button
    lateinit var buttonVotacion: Button
    lateinit var buttonPago: Button

    // Cree un enum para facilitarme no tener que crear una funcion de navegacion para cada boton
    enum class ButtonType {
        BUTTON_ECUACION,
        BUTTON_VOTACION,
        BUTTON_PAGO,
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Incialización de los botones de la vista principal
        buttonEcuacion = findViewById(R.id.buttonEcuacion)
        buttonVotacion = findViewById(R.id.buttonVotacion)
        buttonPago = findViewById(R.id.buttonPago)

        // Estableciendo los clickListener
        buttonEcuacion.setOnClickListener { navigateTo(ButtonType.BUTTON_ECUACION) }
        buttonVotacion.setOnClickListener { navigateTo(ButtonType.BUTTON_VOTACION) }
        buttonPago.setOnClickListener { navigateTo(ButtonType.BUTTON_PAGO) }
    }

    private fun navigateTo(type: ButtonType) {
        lateinit var intent: Intent
        when (type) {
            ButtonType.BUTTON_ECUACION -> intent =
                Intent(this@MainActivity, EcuacionCuadratica::class.java)

            ButtonType.BUTTON_VOTACION -> intent =
                Intent(this@MainActivity, Votacion::class.java)

            ButtonType.BUTTON_PAGO -> intent = Intent(this@MainActivity, PagoLiquido::class.java)
            else -> {}
        }
        startActivity(intent)
    }
}