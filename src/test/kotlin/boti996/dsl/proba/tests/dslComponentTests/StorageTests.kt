package boti996.dsl.proba.tests.dslComponentTests

import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.AccessoryType
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.Fish
import boti996.dsl.proba.models.Storage
import boti996.dsl.proba.tests.getDefaultGameDSLObject
import boti996.dsl.proba.tests.getDefaultGameObject
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class StorageTests : Spek({
    given("A ${Storage::class.simpleName} object") {
        on("Defining ${Fish::class.simpleName}-s and ${Accessory::class.simpleName}-s") {
            val gameObject = getDefaultGameObject().apply {
                storage = Storage().apply {
                    Environment.values().forEach { addFish(Fish(it)) }
                    AccessoryType.values().forEach { addAccessory(Accessory(it)) }
                }
            }

            val gameDslObject = getDefaultGameDSLObject {
                storage {
                    Environment.values().forEach { fish(it) }
                    AccessoryType.values().forEach { accessory(it) }
                }
            }

            it("Should add ${Fish::class.simpleName}-s and ${Accessory::class.simpleName}-s to the ${Storage::class.simpleName}") {
                assertEquals(gameObject, gameDslObject)
                assert(gameObject.storage == gameDslObject.storage)

                for (idx in gameDslObject.storage.fishes.indices) {
                    assert(gameDslObject.storage.fishes[idx] == gameObject.storage.fishes[idx])
                }

                for (idx in gameDslObject.storage.accessories.indices) {
                    assert(gameDslObject.storage.accessories[idx] == gameObject.storage.accessories[idx])
                }
            }
        }
    }
})
