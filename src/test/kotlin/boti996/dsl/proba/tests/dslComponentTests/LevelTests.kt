package boti996.dsl.proba.tests.dslComponentTests

import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.AccessoryType
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.Fish
import boti996.dsl.proba.models.Level
import boti996.dsl.proba.tests.getDefaultGameDSLObject
import boti996.dsl.proba.tests.getDefaultGameObject
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class LevelTests : Spek({
    given("A ${Level::class.simpleName} object") {
        on("Defining ${Fish::class.simpleName}-s and ${Accessory::class.simpleName}-s") {
            val gameObject = getDefaultGameObject(
                listOf(Level(
                    isSkippable = true,
                    isExtraAccessoriesEnabled = true,
                    extraMoneyBonus = 1.0f,
                    environment = Environment.values()[0],
                    fishes = Environment.values().map { Fish(it) }.toList(),
                    accessories = AccessoryType.values().map { Accessory(it) }.toList()
                ))
            )

            val gameDslObject = getDefaultGameDSLObject {
                level(
                    isSkippable = true,
                    isExtraAccessoriesEnabled = true,
                    extraMoneyBonus = 1.0f,
                    environment = Environment.values()[0]
                ) {
                    Environment.values().forEach { fish(it) }
                    AccessoryType.values().forEach { accessory(it) }
                }
            }

            it("Should add ${Fish::class.simpleName}-s and ${Accessory::class.simpleName}-s to the ${Level::class.simpleName}") {
                assertEquals(gameObject, gameDslObject)
                val levelObject = gameObject.levels[0]
                val levelDslObject = gameDslObject.levels[0]
                assert(levelDslObject == levelDslObject)

                for (idx in levelDslObject.fishes.indices) {
                    assert(levelDslObject.fishes[idx] == levelObject.fishes[idx])
                }

                for (idx in levelDslObject.accessories.indices) {
                    assert(levelDslObject.accessories[idx] == levelObject.accessories[idx])
                }
            }
        }
    }
})
