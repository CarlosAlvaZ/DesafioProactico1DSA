package udb.dsa.desafiopractico1.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import udb.dsa.desafiopractico1.R

data class Candidate(val name: Int, val votes: Int, val percentage: Float)

class CandidateAdapter(context: Context, items: MutableList<Candidate>) : RecyclerView.Adapter<CandidateAdapter.ViewHolder>() {
    val context = context
    val items = items

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.candidateName)
        val votesText = view.findViewById<TextView>(R.id.votes)
        val percentTect =  view.findViewById<TextView>(R.id.percentage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.candidate_card, parent, false)

        return CandidateAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CandidateAdapter.ViewHolder, position: Int) {
        holder.nameText.text = "Candidato ${items.get(position).name} "
        holder.votesText.text = "Votos: ${items.get(position).votes} "
        holder.percentTect.text = "Porcentaje: ${items.get(position).percentage}% "
    }

    override fun getItemCount(): Int {
        return items.size
    }
}