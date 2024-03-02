package udb.dsa.desafiopractico1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import udb.dsa.desafiopractico1.R

class Candidate : Fragment() {
    private var candidate: Int? = null
    private var votes: Int? = null
    private var percentage: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            candidate = it.getInt(CANDIDATE)
            votes = it.getInt(VOTES)
            percentage = it.getFloat(PERCENTAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_candidate, container, false)
        val name = view.findViewById<TextView>(R.id.candidateName)
        val percentageText = view.findViewById<TextView>(R.id.percentage)
        val votesText = view.findViewById<TextView>(R.id.votes)

        name.text = "Candidato $candidate"
        percentageText.text = "Porcentaje de votos: $percentage%"
        votesText.text = "Votos: $votes"

        return view
    }

    companion object {
        private const val CANDIDATE = "candidate"
        private const val VOTES = "votes"
        private const val PERCENTAGE = "percentage"
        @JvmStatic
        fun newInstance(candidate: Int, votes: Int, percentage: Float) =
            Candidate().apply {
                arguments = Bundle().apply {
                    putInt(CANDIDATE, candidate)
                    putInt(VOTES, votes)
                    putFloat(PERCENTAGE, percentage)
                }
            }
    }
}