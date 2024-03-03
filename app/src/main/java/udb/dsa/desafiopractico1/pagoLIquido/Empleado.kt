package udb.dsa.desafiopractico1.pagoLIquido

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Empleado(
    val name: String,
    val lastName: String,
    val position: String,
    val hours: Float,
    var bestPaid: Boolean,
    var worstPaid: Boolean
) {

    var issDiscount = 0f
    var afpDiscount = 0f
    var rentaDiscount = 0f
    var sueldo = 0f
    private val THRESHOLD = 160

    init {
        var aux = 0f

        if (hours < THRESHOLD) {
            aux = hours * 9.75f
        } else {
            val first160 = 160f * 9.75f
            aux = (hours - THRESHOLD) * 11.5f + first160
        }

        issDiscount = aux * 0.0525f
        afpDiscount = aux * 0.0688f
        rentaDiscount = aux * 0.1f
        sueldo = aux - issDiscount - afpDiscount - rentaDiscount

        if (position == "Gerente") {
            sueldo += sueldo * 0.1f
        } else if (position == "Asistente") {
            sueldo += sueldo * 0.05f
        } else if (position == "Secretaria") {
            sueldo += sueldo * 0.03f
        } else {
            sueldo += sueldo * 0.02f
        }
    }
}

class EmpleadoViewModel() : ViewModel() {
    var empleados = MutableLiveData<MutableList<Empleado>>()

    init {
        empleados.value = mutableListOf()
    }

    fun addEmpleado(newEmpleado: Empleado) {
        val list = empleados.value
        if (list!!.size == 3) {
            return
        }
        list.add(newEmpleado)
        calculateBestAndWorst(list)
        empleados.postValue(list!!)
    }

    private fun calculateBestAndWorst(list: MutableList<Empleado>) {
        list.forEach{
            it.bestPaid = false
            it.worstPaid = false
        }
        val bestPaid = list.maxBy { it.sueldo }
        bestPaid.bestPaid = true
        val worstPaid = list.minBy { it.sueldo }
        worstPaid.worstPaid = true
    }
}