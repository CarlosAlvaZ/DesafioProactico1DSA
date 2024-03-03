package udb.dsa.desafiopractico1.pagoLIquido

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import udb.dsa.desafiopractico1.R

class EmpleadoViewAdapter(context: Context, empleados: MutableList<Empleado>): RecyclerView.Adapter<EmpleadoViewAdapter.ViewHolder>(){
    val context = context
    val empleados = empleados

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameTarget = view.findViewById<TextView>(R.id.nameTarget)
        val rolTarget = view.findViewById<TextView>(R.id.roleTarget)
        val issTqarget = view.findViewById<TextView>(R.id.issTarget)
        val afpTarget = view.findViewById<TextView>(R.id.afpTarget)
        val rentaTarget = view.findViewById<TextView>(R.id.rentaTarget)
        val sueldoTarget = view.findViewById<TextView>(R.id.sueldoTarget)
        val commentsTarget = view.findViewById<TextView>(R.id.commentsTarget)
        val affirmationTarget = view.findViewById<TextView>(R.id.affirmationTarget)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.empleado_item, parent, false)
        return EmpleadoViewAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return empleados.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val empleado = empleados.get(position)
        holder.nameTarget.text = "${empleado.name} ${empleado.lastName}"
        holder.rolTarget.text = empleado.position
        holder.issTqarget.text = "$ ${"%.2f".format(empleado.issDiscount)}"
        holder.afpTarget.text = "$ ${"%.2f".format(empleado.afpDiscount)}"
        holder.rentaTarget.text = "$ ${"%.2f".format(empleado.rentaDiscount)}"
        holder.sueldoTarget.text = "$ ${"%.2f".format(empleado.sueldo)}"

        if (empleado.bestPaid){
            holder.commentsTarget.text = "Mejor pagado"
        } else if(empleado.worstPaid) {
            holder.commentsTarget.text = "Peor pagado"
        }
        if(empleado.sueldo > 300) {
            holder.affirmationTarget.text = "Mas de $300"
        }
    }
}