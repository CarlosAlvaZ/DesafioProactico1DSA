package udb.dsa.desafiopractico1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import udb.dsa.desafiopractico1.fragments.AddEmpleadoModal
import udb.dsa.desafiopractico1.pagoLIquido.EmpleadoViewAdapter
import udb.dsa.desafiopractico1.pagoLIquido.EmpleadoViewModel

class PagoLiquido : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var addButton : FloatingActionButton
    private lateinit var empleadoViewModel: EmpleadoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago_liquido)

        empleadoViewModel = ViewModelProvider(this).get(EmpleadoViewModel::class.java)

        recyclerView = findViewById(R.id.empleadosRecyclerView)
        addButton = findViewById(R.id.floatingActionButton)

        addButton.setOnClickListener {
            AddEmpleadoModal(this).show(supportFragmentManager, "newEmpleadoTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView(){
        val thisActivity = this
        empleadoViewModel.empleados.observe(this){
            recyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = EmpleadoViewAdapter(thisActivity, it)
            }
        }
    }
}