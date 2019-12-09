package boti996.dsl.proba.tests

import boti996.dsl.proba.game
import boti996.dsl.proba.models.Game
import boti996.dsl.proba.models.Shop
import boti996.dsl.proba.models.Storage
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFailsWith

class ExceptionTests : Spek({
    given("A ${Game::class.simpleName} object") {
        on("Defining multiple ${Storage::class.simpleName} objects") {
            it("Should raise exception") {
                assertFailsWith<AssertionError> {
                    val game = game {
                        storage()
                        storage()
                    }
                }
            }
        }
        on("Defining multiple ${Shop::class.simpleName} objects") {
            it("Should raise exception") {
                assertFailsWith<AssertionError> {
                    val game = game {
                        shop()
                        shop()
                    }
                }
            }
        }
    }
})
