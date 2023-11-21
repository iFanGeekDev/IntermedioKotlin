package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue

class HoroscopeViewModelTest {

    //ponerle al MocKK ( una respuesta para cualquier metodo por default si es que el metodo a probar lo requiriera) relaxed = true.
    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewmodel is created then horoscopes are loaded`() {
        // Si usara corutinas se colocar "coEvery"+

        //Cada vez que llamen a este provider mockeado retorname una lista.
        every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList
        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.horoscope.value

        assertTrue(horoscopes.isNotEmpty())

    }
}