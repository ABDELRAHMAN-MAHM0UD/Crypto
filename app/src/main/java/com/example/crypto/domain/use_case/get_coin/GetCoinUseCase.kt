package com.example.crypto.domain.use_case.get_coin

import com.example.crypto.common.Resources
import com.example.crypto.data.remote.dto.toCoin
import com.example.crypto.data.remote.dto.toCoinDetails
import com.example.crypto.domain.model.Coin
import com.example.crypto.domain.model.CoinDetails
import com.example.crypto.domain.repository.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(val repo: CoinRepo) {

    operator fun invoke(coinId: String): Flow<Resources<CoinDetails>> = flow {
        try{
        emit(Resources.Loading())
           val coinDetails = repo.getCoinById(coinId)
            emit(Resources.Success(coinDetails.toCoinDetails()))
    }
    catch (e: HttpException){
        emit(Resources.Failed(e.localizedMessage?: "an error occured ( HTTP )"))
    }
        catch(e: IOException){
            emit(Resources.Failed(e.localizedMessage?: "Internet Error"))
        }
}
    }
