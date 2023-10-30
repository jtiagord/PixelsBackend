package pixels.errorHandlers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.sql.Timestamp
import java.time.LocalDateTime

@ControllerAdvice
class ErrorHandlingController {
    @ExceptionHandler(ApiException::class)
    fun handleTransitionException(ex: ApiException)=
        ResponseEntity
            .status(ex.statusCode)
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                ex.errorMessageObject
            )

}


@ResponseStatus(HttpStatus.BAD_REQUEST)
open class ApiException(val statusCode: HttpStatus, private val errorMessage : String) : RuntimeException(){
    companion object{
        class ErrorObject(
            val timestamp: Timestamp = Timestamp.valueOf(LocalDateTime.now()), val statusCode: Int,
                val message : String )
    }
    val errorMessageObject
        get() = ErrorObject(message = errorMessage,  statusCode = statusCode.value())
}


@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(errorMessage: String) : ApiException(HttpStatus.NOT_FOUND, errorMessage)