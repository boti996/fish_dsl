package boti996.dsl.proba.tests

import boti996.dsl.proba.game
import boti996.dsl.proba.models.*
import boti996.dsl.proba.models.BonusProviders.*
import boti996.dsl.proba.note
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class ComplexDslTest : Spek({

    given("A ${Game::class.simpleName} object") {
        on("Defining a complex, lifelike structure") {

            val gameDslObject = game(500, 0.5F, 1, Position(60, 40)) {

                note("Level 1")
                level(extraMoneyBonus = 0.1F, environment = Environment.TROPICAL) {
                    note("Mobs")
                    fish(Environment.TROPICAL)
                    fish(Environment.TROPICAL)
                    fish(Environment.TROPICAL)
                    fish(Environment.TROPICAL)
                    fish(Environment.TROPICAL)
                }
                note("Level 2")
                level(
                    isSkippable = true,
                    isExtraAccessoriesEnabled = false,
                    extraMoneyBonus = 0.2F,
                    environment = Environment.DEEP_SEA
                ) {
                    note("Mobs")
                    fish(Environment.DEEP_SEA)
                    fish(Environment.DEEP_SEA)
                    fish(Environment.DEEP_SEA)
                    fish(Environment.DEEP_SEA) {
                        equipment(Equipment.SHIELD)
                    }

                    fish(Environment.DEEP_SEA) {
                        equipment(Equipment.SHIELD)
                    }

                    fish(Environment.DEEP_SEA) {
                        equipment(Equipment.SHIELD)
                        equipment(Equipment.WEAPON)
                    }

                    note("Map accessories")
                    accessory(AccessoryType.POISON) {
                        bonus(BonusType.ANOXIA)
                    }

                    accessory(AccessoryType.STRENGTH_AMPLIFIER) {
                        bonus(BonusType.EXTRA_STRENGTH, 1.2f)
                        bonus(BonusType.EXTRA_DEFENSE)
                    }
                }
                note("Level 3")
                level() {
                    note("Mobs")
                    fish(Environment.RIVER)
                    fish(Environment.RIVER)
                    fish(Environment.RIVER)
                    fish(Environment.RIVER)
                    fish(Environment.RIVER) {
                        equipment(Equipment.WEAPON)
                    }

                    fish(Environment.RIVER) {
                        equipment(Equipment.WEAPON)
                    }

                    fish(Environment.RIVER) {
                        equipment(Equipment.WEAPON)
                    }

                    fish(Environment.RIVER) {
                        equipment(Equipment.SHIELD_OF_HEALING)
                    }

                    fish(Environment.RIVER) {
                        equipment(Equipment.SHIELD_OF_HEALING)
                    }

                    fish(Environment.RIVER) {
                        equipment(Equipment.SHIELD_OF_HEALING)
                        equipment(Equipment.WEAPON_OF_RIVER_FISH)
                    }

                    note("Map accessories")
                    accessory(AccessoryType.OXYGEN_PUMP)
                }


                storage {
                    note("Fishes")
                    fish(Environment.RIVER)
                    fish(Environment.RIVER) {
                        equipment(Equipment.WEAPON_OF_RIVER_FISH)
                    }

                    fish(Environment.DEEP_SEA) {
                        equipment(Equipment.SHIELD_OF_HEALING)
                    }

                    fish(Environment.TROPICAL)
                    fish(Environment.TROPICAL) {
                        equipment(Equipment.SHIELD)
                        equipment(Equipment.WEAPON)
                    }

                    note("Accessories")
                    accessory(AccessoryType.OXYGEN_PUMP, Position(0, 0)) {
                        bonus(BonusType.EXTRA_DEFENSE, 1.2f)
                        bonus(BonusType.EXTRA_STRENGTH, 1.05f)
                    }

                    accessory(AccessoryType.STRENGTH_AMPLIFIER, Position(30, 40)) {
                        bonus(BonusType.EXTRA_HEAL)
                    }
                }


                shop {
                    note("Fishes")
                    fish(100, Environment.RIVER) {
                        note("Guppy")
                    }

                    fish(300, Environment.TROPICAL) {
                        note("Barracuda")
                        equipment(Equipment.SHIELD)
                    }

                    fish(800, Environment.DEEP_SEA) {
                        note("Fangtooth")
                        equipment(Equipment.SHIELD)
                        equipment(Equipment.WEAPON)
                    }

                    note("Accessories")
                    accessory(500, AccessoryType.OXYGEN_PUMP, Position(0, 0)) {
                        note("Basic oxygen pump")
                        bonus(BonusType.ANOXIA, -0.5f)
                    }

                    accessory(1000, AccessoryType.STRENGTH_AMPLIFIER, Position(30, 40)) {
                        note("Basic amplifier")
                    }
                }
            }

            val gameObject = Game(500, 0.5F, 1, Position(60, 40), listOf(
                // Level 1
                Level(isSkippable = false,
                    isExtraAccessoriesEnabled = true,
                    extraMoneyBonus = 0.1F,
                    environment = Environment.TROPICAL,
                    fishes = listOf(
                        Fish(Environment.TROPICAL),
                        Fish(Environment.TROPICAL),
                        Fish(Environment.TROPICAL),
                        Fish(Environment.TROPICAL),
                        Fish(Environment.TROPICAL)
                    )),
                // Level 2
                Level(isSkippable = true,
                    isExtraAccessoriesEnabled = false,
                    extraMoneyBonus = 0.2F,
                    environment = Environment.DEEP_SEA,
                    // Mobs
                    fishes = listOf(
                        Fish(Environment.DEEP_SEA),
                        Fish(Environment.DEEP_SEA),
                        Fish(Environment.DEEP_SEA),
                        Fish(Environment.DEEP_SEA, listOf(
                            Equipment.SHIELD
                        )),
                        Fish(Environment.DEEP_SEA, listOf(
                            Equipment.SHIELD
                        )),
                        Fish(Environment.DEEP_SEA, listOf(
                            Equipment.SHIELD,
                            Equipment.WEAPON
                        ))
                    ),
                    // Map accessories
                    accessories = listOf(
                        Accessory(AccessoryType.POISON, additionalBonuses = listOf(
                            Bonus(BonusType.ANOXIA)
                        )),
                        Accessory(AccessoryType.STRENGTH_AMPLIFIER, additionalBonuses =  listOf(
                            Bonus(BonusType.EXTRA_STRENGTH, 1.2f),
                            Bonus(BonusType.EXTRA_DEFENSE)
                        ))
                    )),
                // Level 3
                Level(isSkippable = false,
                    isExtraAccessoriesEnabled = true,
                    extraMoneyBonus = 0.0F,
                    environment = Environment.RIVER,
                    // Mobs
                    fishes = listOf(
                        Fish(Environment.RIVER),
                        Fish(Environment.RIVER),
                        Fish(Environment.RIVER),
                        Fish(Environment.RIVER),
                        Fish(Environment.RIVER, listOf(
                            Equipment.WEAPON
                        )),
                        Fish(Environment.RIVER, listOf(
                            Equipment.WEAPON
                        )),
                        Fish(Environment.RIVER, listOf(
                            Equipment.WEAPON
                        )),
                        Fish(Environment.RIVER, listOf(
                            Equipment.SHIELD_OF_HEALING
                        )),
                        Fish(Environment.RIVER, listOf(
                            Equipment.SHIELD_OF_HEALING
                        )),
                        Fish(Environment.RIVER, listOf(
                            Equipment.SHIELD_OF_HEALING,
                            Equipment.WEAPON_OF_RIVER_FISH
                        ))

                    ),
                    // Map accessories
                    accessories = listOf(
                        Accessory(AccessoryType.OXYGEN_PUMP)
                    ))
            ))

            gameObject.storage = Storage()
            // Fishes
            val storageFishes = listOf(
                Fish(Environment.RIVER),
                Fish(Environment.RIVER, listOf(
                    Equipment.WEAPON_OF_RIVER_FISH
                )),
                Fish(Environment.DEEP_SEA, listOf(
                    Equipment.SHIELD_OF_HEALING
                )),
                Fish(Environment.TROPICAL),
                Fish(Environment.TROPICAL, listOf(
                    Equipment.SHIELD,
                    Equipment.WEAPON
                ))
            )
            // Accessories
            val storageAccessories = listOf(
                Accessory(AccessoryType.OXYGEN_PUMP, Position(0, 0), listOf(
                    Bonus(BonusType.EXTRA_DEFENSE, 1.2f),
                    Bonus(BonusType.EXTRA_STRENGTH, 1.05f)
                )),
                Accessory(AccessoryType.STRENGTH_AMPLIFIER, Position(30, 40), listOf(
                    Bonus(BonusType.EXTRA_HEAL)
                ))
            )

            gameObject.shop = Shop()
            // Fishes
            val shopFishes = listOf(
                // Guppy
                ShopEntry(100, Fish(Environment.RIVER)),
                // Barracuda
                ShopEntry(300, Fish(Environment.TROPICAL, listOf(
                    Equipment.SHIELD
                ))),
                // Fangtooth
            ShopEntry(800, Fish(Environment.DEEP_SEA, listOf(
                Equipment.SHIELD,
                Equipment.WEAPON
            )))
            )
            // Accessories
            val shopAccessories = listOf(
                // Basic oxygen pump
                ShopEntry(500, Accessory(AccessoryType.OXYGEN_PUMP, Position(0, 0), listOf(
                    Bonus(BonusType.ANOXIA, -0.5f)
                ))),
                // Basic amplifier
                ShopEntry(1000, Accessory(AccessoryType.STRENGTH_AMPLIFIER, Position(30, 40)))
            )

            gameObject.storage.addFishes(storageFishes)
            gameObject.storage.addAccessories(storageAccessories)
            gameObject.shop.addFishes(shopFishes)
            gameObject.shop.addAccessories(shopAccessories)

            it("Should be a well-defined ${Game::class.simpleName} object") {
                assertEquals(gameObject, gameDslObject)
            }
        }
    }
})
