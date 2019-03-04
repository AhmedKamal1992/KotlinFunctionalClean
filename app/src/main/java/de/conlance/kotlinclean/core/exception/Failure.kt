package de.conlance.kotlinclean.core.exception


/**Base class for handling errors/Failures/Exceptions
 * Every class should extent [FeatureFailure] class.
**/
sealed class Failure {
    object NetworkConnection: Failure()
    object serverError: Failure()

    abstract class FeatureFailure:Failure()
}