package boti996.dsl.proba.tests.dslComponentTests

import boti996.dsl.proba.models.*
import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.AccessoryType
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.tests.getDefaultGameDSLObject
import boti996.dsl.proba.tests.getDefaultGameObject
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class GameTests : Spek({

    given("A ${Game::class.simpleName} object") {
        on("Defining it with only constructor parameters") {
            it("Should create a valid object") {
                assertEquals(
                    getDefaultGameDSLObject {},
                    getDefaultGameObject()
                )
            }
        }

        on("Defining a ${Storage::class.simpleName} explicitly") {

            val gameObject = getDefaultGameObject().apply {
                storage = Storage().apply {
                    addFish(Fish(Environment.values()[0]))
                    addAccessory(Accessory(AccessoryType.values()[0]))
                }
            }

            val gameDslObject = getDefaultGameDSLObject {
                storage {
                    fish(Environment.values()[0])
                    accessory(AccessoryType.values()[0])
                }
            }

            it("Should set the ${Storage::class.simpleName} of the ${Game::class.simpleName}") {
                assertEquals(gameObject, gameDslObject)
            }
        }

        on("Defining a ${Shop::class.simpleName} explicitly") {

            val gameObject = getDefaultGameObject().apply {
                shop = Shop().apply {
                    addFish(ShopEntry(100, Fish(Environment.values()[0])))
                    addAccessory(ShopEntry(100, Accessory(AccessoryType.values()[0])))
                }
            }

            val gameDslObject = getDefaultGameDSLObject {
                shop {
                    fish(100, Environment.values()[0])
                    accessory(100, AccessoryType.values()[0])
                }
            }

            it("Should set the ${Shop::class.simpleName} of the ${Game::class.simpleName}") {
                assertEquals(gameObject, gameDslObject)
            }
        }

        on("Defining ${Level::class.simpleName}-s") {

            val gameObject = getDefaultGameObject(
                levels = listOf(
                    Level(
                        isSkippable = true,
                        isExtraAccessoriesEnabled = true,
                        extraMoneyBonus = 1.0f,
                        environment = Environment.values()[0],
                        fishes = listOf(),
                        accessories = listOf()
                    ),
                    Level(
                        isSkippable = true,
                        isExtraAccessoriesEnabled = true,
                        extraMoneyBonus = 1.0f,
                        environment = Environment.values()[0],
                        fishes = listOf(Fish(Environment.values()[0])),
                        accessories = listOf(Accessory(AccessoryType.values()[0]))
                    )
                )
            )

            val gameDslObject = getDefaultGameDSLObject {
                level(
                    isSkippable = true,
                    isExtraAccessoriesEnabled = true,
                    extraMoneyBonus = 1.0f,
                    environment = Environment.values()[0]
                )
                level(
                    isSkippable = true,
                    isExtraAccessoriesEnabled = true,
                    extraMoneyBonus = 1.0f,
                    environment = Environment.values()[0]
                ) {
                    fish(Environment.values()[0])
                    accessory(AccessoryType.values()[0])
                }
            }

            it("Should set the ${Level::class.simpleName}-s of the ${Game::class.simpleName}") {
                assertEquals(gameObject, gameDslObject)
            }
        }
    }
})
