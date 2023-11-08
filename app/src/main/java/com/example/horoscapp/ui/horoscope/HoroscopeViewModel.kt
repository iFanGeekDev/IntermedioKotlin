package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//Desde el "init" o desde variable no necesitas colocar val dentro del constructor para acceder a la clase que tengas alli
// si vas a accceder desde una funcion , si necesitas colocar el val para usar lo que este dentro del constructor
@HiltViewModel
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) :
    ViewModel() {

    //La lista puede modificarse desde dentro de la clase
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())

    //Pero creamos otra variable que se va comunicar con el fragment y que esta no pueda modificar los valores , solo va leer los valores
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    init {
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }
}