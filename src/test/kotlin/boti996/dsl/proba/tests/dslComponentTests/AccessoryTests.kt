package boti996.dsl.proba.tests.dslComponentTests

import boti996.dsl.proba.models.Bonus
import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.AccessoryType
import boti996.dsl.proba.models.BonusProviders.Equipment
import boti996.dsl.proba.models.BonusType
import boti996.dsl.proba.tests.getDefaultGameDSLObject
import boti996.dsl.proba.tests.getDefaultGameObject
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.random.Random
import kotlin.test.assertEquals

class AccessoryTests : Spek({
    given("A ${Accessory::class.simpleName} object") {
        on("Defining ${Equipment::class.simpleName}-s") {

            val multipliers = List(AccessoryType.values().size) { Random.nextDouble(0.0, 10.0).toFloat() }

            var currentMultiplier = multipliers.iterator()

            val gameObject = getDefaultGameObject().apply {
                storage.apply {
                    addAccessory(Accessory(AccessoryType.values()[0],
                        additionalBonuses =
                    BonusType.values().map { Bonus(it, currentMultiplier.next()) }.toList()))
                }
            }

            currentMultiplier = multipliers.iterator()

            val gameDslObject = getDefaultGameDSLObject {
                storage {
                    accessory(AccessoryType.values()[0]) {
                        BonusType.values().forEach { bonus(it, currentMultiplier.next()) }
                    }
                }
            }

            it("Should add ${Equipment::class.simpleName}-s to the ${Accessory::class.simpleName}") {
                assertEquals(gameObject, gameDslObject)
                assert(gameObject.storage.accessories == gameDslObject.storage.accessories)

                val accessoryObjectBonuses = gameObject.storage.accessories[0].getBonuses()
                val accessoryDslObjectBonuses = gameDslObject.storage.accessories[0].getBonuses()

                for (idx in accessoryDslObjectBonuses.indices) {
                    assert(accessoryDslObjectBonuses[idx] == accessoryObjectBonuses[idx])
                    // multiplied bonuses
                    assert(accessoryDslObjectBonuses[idx].getBonus() == accessoryObjectBonuses[idx].getBonus())
                    println(accessoryObjectBonuses[idx].getBonus())
                }
            }
        }
    }
})
