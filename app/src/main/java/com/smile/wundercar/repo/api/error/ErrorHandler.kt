package com.smile.wundercar.repo.api.error

import com.past3.ketro.api.ApiErrorHandler
import retrofit2.Response

class ErrorHandler : ApiErrorHandler() {

    override fun getExceptionType(response: Response<*>): Exception {
        return when (response.code()) {
            in 500..599 -> NoDataException()
            else -> BadRequestException()
        }
    }

    companion object ErrorConfig {
        class NoDataException : Exception() {
            override val message: String = "Unable to retrieve data at this time"
        }

        //Can handle this error internally
        class BadRequestException : Exception() {
            override val message: String = "Unable to complete request"
        }

    }
}