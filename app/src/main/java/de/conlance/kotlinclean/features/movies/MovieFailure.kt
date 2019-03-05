package de.conlance.kotlinclean.features.movies

import de.conlance.kotlinclean.core.exception.Failure

class MovieFailure {
    class ListNotAvailable: Failure.FeatureFailure()
    class NonExistantMovie: Failure.FeatureFailure()
}