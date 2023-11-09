package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.example.horoscapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscapp.domain.model.HoroscopeInfo.Capricorn
import com.example.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscapp.domain.model.HoroscopeInfo.Pisces
import com.example.horoscapp.domain.model.HoroscopeInfo.Sagittarius
import com.example.horoscapp.domain.model.HoroscopeInfo.Scorpio
import com.example.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.example.horoscapp.domain.model.HoroscopeInfo.Virgo
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    //_ el guion abajo es cuando queremos definir variables a las que no deberian acceder de fuera de la clase , le colocamos el guion abajo

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    //Cuando la vista a sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            val type = when (it) {
                Aquarius -> HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricorn
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Pisces
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio -> HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
            //Toast.makeText(context, getString(it.name), Toast.LENGTH_LONG).show()
        })
        //Si estas en un fragment necesitas el "RequireContext" o "context"
        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
        // binding.rvHoroscope.layoutManager = LinearLayoutManager(context)
        //binding.rvHoroscope.adapter = adapter
    }


    private fun initUIState() {
        //Courutine especial
        //Porque esta courutine se engancha al ciclo de vida del fragmento
        //Si el framgneto muere , la courutina muere tambien
        lifecycleScope.launch {
            //
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //Suscribete a este viewModel
                //Siempre que en el viewModel haya un "_horoscope.value" se va llamar a lo que haya adentro de los corchetes
                horoscopeViewModel.horoscope.collect {
                    //Log.i("ARIS", getString(it[0].name))
                    //CAMBIOS HOROSCOPE
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }

    //onCreateView -> cuando la vista es creada
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}