package iot.lab.qrdetails.util

/**
 * This sealed Class contains all the States of the Teacher List Request of a API
 *
 * @property Idle is used to define the Initial State
 * @property Loading is used to define the state of the API call when it is in fetching Phase
 * @property Success is used to define when the API call is a Success
 * @property Failure is used to define when the API call is a Failure
 */
sealed interface UiState<out T> {
    object Idle : UiState<Nothing>
    object Loading : UiState<Nothing>
    class Success<T>(val data: T) : UiState<T>
    class Failure(val errorMessage: String) : UiState<Nothing>
}