package com.example.crypto.domain.use_case.get_coins

import com.example.crypto.common.Resources
import com.example.crypto.data.remote.dto.toCoin
import com.example.crypto.domain.model.Coin
import com.example.crypto.domain.repository.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(val repo: CoinRepo) {

     operator fun invoke(): Flow<Resources<List<Coin>>> = flow {
        try{
        emit(Resources.Loading())
           val coins = repo.getCoins().map{it.toCoin()}
            emit(Resources.Success(coins))
    }
    catch (e: HttpException){
        emit(Resources.Failed(e.localizedMessage?: "an error occured ( HTTP )"))
    }
        catch(e: IOException){
            emit(Resources.Failed(e.localizedMessage?: "Internet Error"))
        }
}
    }
