package udb.dsa.desafiopractico1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.sqrt

class EcuacionCuadratica : AppCompatActivity() {
    // Declaracion de elementos en layoute
    lateinit var aText: EditText
    lateinit var bText: EditText
    lateinit var cText: EditText
    lateinit var button: Button
    lateinit var target: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecuacion_cuadratica)

        aText = findViewById(R.id.inputA)
        bText = findViewById(R.id.inputB)
        cText = findViewById(R.id.inputC)

        button = findViewById(R.id.buttonCalcular)

        target = findViewById(R.id.resultTarget)

        button.setOnClickListener { calc() }
    }

    private fun calc() {
        if (aText.text.isEmpty() or bText.text.isEmpty() or cText.text.isEmpty()) {
            Toast.makeText(this, "Ingresa los datos de cada variable", Toast.LENGTH_LONG).show()
            return
        }

        val a = aText.text.toString().toDouble()
        val b = bText.text.toString().toDouble()
        val c = cText.text.toString().toDouble()

        // Validando determinante
        val det = (b * b) - (4 * a * c)

        if (det < 0) {
            printToTarget("No hay soluciones en los numeros reales")
            return
        }

        val firstAnswer = ((b * -1) + sqrt(det)) / (2 * a)
        val secondAnswer = ((b * -1) - sqrt(det)) / (2 * a)

        printToTarget("x1 = $firstAnswer, x2 = $secondAnswer")
    }

    private fun printToTarget(text: String) {
        target.text = text
    }
}