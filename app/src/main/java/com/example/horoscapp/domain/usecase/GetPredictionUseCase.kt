package com.example.horoscapp.domain.usecase

import com.example.horoscapp.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository) {

    //Se usa para sobreescribir metodos de una funcion
    suspend operator fun invoke(sign: String) = repository.getPrediction(sign)


}