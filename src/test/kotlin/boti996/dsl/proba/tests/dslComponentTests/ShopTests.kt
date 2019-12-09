package boti996.dsl.proba.tests.dslComponentTests

import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.AccessoryType
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.Fish
import boti996.dsl.proba.models.Shop
import boti996.dsl.proba.models.ShopEntry
import boti996.dsl.proba.tests.getDefaultGameDSLObject
import boti996.dsl.proba.tests.getDefaultGameObject
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class ShopTests : Spek({
    given("A ${Shop::class.simpleName} object") {
        on("Defining ${Fish::class.simpleName}-s") {
            val gameObject = getDefaultGameObject().apply {
                shop = Shop().apply {
                    Environment.values().forEach { addFish(ShopEntry(100, Fish(it))) }
                    AccessoryType.values().forEach { addAccessory(ShopEntry(100, Accessory(it))) }
                }
            }

            val gameDslObject = getDefaultGameDSLObject {
                shop {
                    Environment.values().forEach { fish(100, it) }
                    AccessoryType.values().forEach { accessory(100, it) }
                }
            }

            it("Should add ${Fish::class.simpleName}-s to the ${Shop::class.simpleName}") {
                assertEquals(gameObject, gameDslObject)
                assert(gameObject.shop == gameDslObject.shop)

                for (idx in gameDslObject.shop.fishes.indices) {
                    assert(gameDslObject.shop.fishes[idx].price == gameObject.shop.fishes[idx].price)
                    assert(gameDslObject.shop.fishes[idx].item == gameObject.shop.fishes[idx].item)
                }

                for (idx in gameDslObject.shop.accessories.indices) {
                    assert(gameDslObject.shop.accessories[idx].price == gameObject.shop.accessories[idx].price)
                    assert(gameDslObject.shop.accessories[idx].item == gameObject.shop.accessories[idx].item)
                }
            }
        }
    }
})
