package com.example.starfood.common


import com.example.starfood.R
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber

class StarfoodExceptionMapper {
    companion object {
        fun map(throwable: Throwable): StarFoodException {
            if (throwable is HttpException) {
                try {
                    val errorJsonObject = JSONObject(throwable.response()?.errorBody()!!.string())
                    val errorMessage=errorJsonObject.getString("message")
                    return StarFoodException(if (throwable.code()==401) StarFoodException.Type.AUTH else StarFoodException.Type.SIMPLE,serverMessage = errorMessage)
                } catch (exception: Exception) {
                    Timber.e(exception)
                }
            }

            return StarFoodException(StarFoodException.Type.SIMPLE, R.string.unknown_error)
        }
    }
}