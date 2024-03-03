package udb.dsa.desafiopractico1.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import udb.dsa.desafiopractico1.R
import udb.dsa.desafiopractico1.databinding.FragmentAddEmpleadoModalBinding
import udb.dsa.desafiopractico1.pagoLIquido.Empleado
import udb.dsa.desafiopractico1.pagoLIquido.EmpleadoViewModel

class AddEmpleadoModal(context: Context) : BottomSheetDialogFragment() {
    private val context = context
    private lateinit var binding: FragmentAddEmpleadoModalBinding
    private lateinit var empleadoViewModel: EmpleadoViewModel
    private var selectedItem: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        empleadoViewModel = ViewModelProvider(activity).get(EmpleadoViewModel::class.java)
        binding.addEmpleadoButton.setOnClickListener {
            saveAction()
        }

        val items = listOf("Gerente", "Asistente", "Secretaria", "Jardinero", "Conserje")

        val autocompleteView = binding.cargo

        val adapter = ArrayAdapter(context, R.layout.list_item, items)

        autocompleteView.setAdapter(adapter)

        autocompleteView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            selectedItem = adapterView.getItemAtPosition(i).toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEmpleadoModalBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction() {
        val name = binding.empleadoName.text.toString()
        val lastName = binding.empleadoLastName.text.toString()
        val position = selectedItem
        val hours = binding.empleadoHoras.text.toString().toFloatOrNull()
        if(name.isEmpty() || lastName.isEmpty() || position == null || hours == null || hours == 0f) {
           Toast.makeText(context, "LLena los campos", Toast.LENGTH_LONG).show()
            return
        }

        val newEmpleado = Empleado(name, lastName, position, hours, false, false)

        empleadoViewModel.addEmpleado(newEmpleado)

        clean()
        dismiss()
    }

    private fun clean() {
        binding.empleadoName.setText("")
        binding.empleadoLastName.setText("")
        binding.empleadoHoras.setText("")
        selectedItem = null
    }
}