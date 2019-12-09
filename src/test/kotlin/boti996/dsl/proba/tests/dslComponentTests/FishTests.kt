package boti996.dsl.proba.tests.dslComponentTests

import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.Equipment
import boti996.dsl.proba.models.Fish
import boti996.dsl.proba.tests.getDefaultGameDSLObject
import boti996.dsl.proba.tests.getDefaultGameObject
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class FishTests : Spek({
    given("A ${Fish::class.simpleName} object") {
        on("Defining ${Equipment::class.simpleName}-s") {
            val gameObject = getDefaultGameObject().apply {
                storage.apply {
                    addFish(Fish(Environment.values()[0],
                        Equipment.values().map { it }.toList()))
                }
            }

            val gameDslObject = getDefaultGameDSLObject {
                storage {
                    fish(Environment.values()[0]) {
                        Equipment.values().forEach { equipment(it) }
                    }
                }
            }

            it("Should add ${Equipment::class.simpleName}-s to the ${Fish::class.simpleName}") {
                assertEquals(gameObject, gameDslObject)
            }
        }
    }
})
