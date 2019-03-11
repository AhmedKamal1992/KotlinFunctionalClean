package de.conlance.kotlinclean.core.functional

import de.conlance.kotlinclean.UnitTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class EitherTest: UnitTest() {

    @Test fun eitherRightShouldReturnCorrectType() {
        val result = Either.Right("ironman")

        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe true
        result.isLeft shouldBe false
        result.either({},
            { right ->
                right shouldBeInstanceOf String::class.java
                right shouldEqualTo "ironman"
            })
    }

    @Test fun eitherLeftShouldReturnTheCorrectType() {
        val result = Either.Left("ironman")

        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe false
        result.isLeft shouldBe true
        result.either({
            left -> left shouldBeInstanceOf String::class.java
            left shouldEqualTo "ironman"
        },{})
    }
}