package udb.dsa.desafiopractico1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import udb.dsa.desafiopractico1.fragments.Candidate

class Votacion : AppCompatActivity() {
    lateinit var input: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_votacion)

        input = findViewById(R.id.input)
        button = findViewById(R.id.buttonCalcular)

        button.setOnClickListener { calculateVotes() }
    }

    private fun getVotes(str: String): Pair<MutableMap<Int, Int>, Int> {
        val tmp = mutableMapOf<Int, Int>()
        var total = 0

        // Extrayendo todas las cifras separadas por coma
        val arr = str.split(",").toTypedArray<String>()

        for (d in arr) {
            // Validando si el caracter es un entero
            val aux = d.toIntOrNull()
            when (aux) {
                // Si no es un entero, se pasa al siguiente
                null -> continue
                // si es un entero, se valida si ya existe en el MutableMap
                else -> {
                    // si ya existe, se incrementa sus aparicions por 1
                    if (tmp.containsKey(aux)) {
                        val currentValue = tmp[aux]!!
                        val newValue = currentValue + 1

                        tmp[aux] = newValue
                    } else {
                        // si no existe aun, significa que es la primera aparicion de este elemento
                        tmp[aux] = 1
                    }
                    // incrementing total by one
                    total += 1
                }
            }
        }

        return Pair(tmp, total)
    }

    private fun calculateVotes() {
        val string = input.text.toString()
        if (string.isEmpty()) {
            Toast.makeText(
                this,
                "Por favor ingrese los resultados de las votaciones",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val results = getVotes(string)

       // for (result in results.first){
       //     print("${result.key}, ${result.value}")
       // }

       val fragmentManager = supportFragmentManager
       val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        for (result in results.first) {
            val p = result.value * 100f / results.second
            val fragment = Candidate.newInstance(result.key, result.value, p)
            fragmentTransaction.add(R.id.fragmentContainerViewVotacion, fragment)
        }

        fragmentTransaction.commit()
    }
}