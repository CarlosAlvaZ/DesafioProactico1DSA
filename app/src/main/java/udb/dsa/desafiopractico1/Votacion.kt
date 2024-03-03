package udb.dsa.desafiopractico1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import udb.dsa.desafiopractico1.components.Candidate
import udb.dsa.desafiopractico1.components.CandidateAdapter

class Votacion : AppCompatActivity() {
    lateinit var input: EditText
    lateinit var button: Button

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_votacion)

        input = findViewById(R.id.input)
        button = findViewById(R.id.buttonCalcular)

        recyclerView = findViewById(R.id.recyclerViewVotacion)

        button.setOnClickListener { calculateVotes() }
    }

    private fun getVotes(str: String): Pair<Map<Int, Int>, Int> {
        val tmp = str.split(",").mapNotNull { it.trim().toIntOrNull() }

        var total = tmp.size

        return Pair(tmp.groupingBy { it }.eachCount(), total)
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

        val (results, total) = getVotes(string)

        val candidateList = parseMapToCandidateList(results, total)

        printResults(candidateList)
    }

    private fun parseMapToCandidateList(map: Map<Int, Int>, total: Int): MutableList<Candidate> {
        val list = mutableListOf<Candidate>()

        for ((candidate, votes) in map) {
            val perc = votes * 100f / total
            val tmp = Candidate(candidate, votes, perc)
            list.add(tmp)
        }

        return list
    }

    private fun printResults(list: MutableList<Candidate>) {
        val adapter = CandidateAdapter(this, list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}