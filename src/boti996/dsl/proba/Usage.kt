package boti996.dsl.proba

import boti996.dsl.proba.models.BonusProviders.AccessoryType
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.Position

fun main() {
    val game = game(500, 0.5F, 1, Position(60, 40)) {

        note("Level 1")
        level(extraMoneyBonus = 0.1F, environment = Environment.TROPICAL) {
            note("Mobs")
            fish(Environment.TROPICAL) {
            }
        }
        note("Level 2")
        level(true, false, 0.2F, Environment.DEEP_SEA) {
            TODO()
        }
        note("Level 3")
        level {
            TODO()
        }

        storage {
            note("Fishes")
            fish(Environment.RIVER) {
                TODO()
            }
            fish(Environment.RIVER) {
                TODO()
            }
            fish(Environment.DEEP_SEA) {
                TODO()
            }
            fish(Environment.TROPICAL) {
                TODO()
            }
            fish(Environment.TROPICAL) {
                TODO()
            }
            note("Accessories")
            accessory(AccessoryType.OXYGEN_PUMP, Position(0, 0)) {
                TODO()
            }
            accessory(AccessoryType.STRENGTH_AMPLIFIER, Position(30, 40)) {
                TODO()
            }
        }

        shop {
            note("Fishes")
            fish(100, Environment.RIVER) {
                note("Guppy")
                TODO()
            }
            fish(300, Environment.TROPICAL) {
                note("Barracuda")
                TODO()
            }
            fish(800, Environment.DEEP_SEA) {
                note("Fangtooth")
                TODO()
            }
            note("Accessories")
            accessory(AccessoryType.OXYGEN_PUMP, Position(0, 0)) {
                TODO()
            }
            accessory(AccessoryType.STRENGTH_AMPLIFIER, Position(30, 40)) {
                TODO()
            }
        }
    }

    print(game)
}

/*
    Game {
        Properties {
            initial_money: $ int
            money_bonus_per_level: % float
            max_skippable_levels: n int
            level_size: x y POS_TYPE
        }
        Levels: [
            Level {
                Properties {
                    is_skippable: bool
                    is_extra_accessories_enabled: bool
                    extra_money_bonus: % float
                    environment: enum ENV_TYPE
                }
                Accessories [
                    Accessory {
                        position: x y POS_TYPE
                        type: enum ACCESSORY_TYPE
                        Bonuses [
                            Bonus {
                                type: enum BONUS_TYPE
                                rate: % float
                            }
                        ]
                    }
                    ...
                ]
                Mobs [
                    Fish {
                        type: enum FISH_TYPE
                        Equipments [
                            Equipment {
                                type: enum EQUIPMENT_TYPE
                                Bonuses [
                                    Bonus {
                                        type: enum BONUS_TYPE
                                        rate % float
                                    }
                                    ...
                                ]
                            }
                            ...
                        ]
                    }
                    ...
                ]
            }
            ...
        ]
    }


    Storage {
        Fishes [
            Fish {
                -||-
            }
            ...
        ]
        Accessories [
            Accessory {
                -||-
            }
        ]
    }


    Shop {
        Fishes [
            Fish {
                price: $ int
                -||-
            }
            ...
        ]
        Accessories [
            Accessory {
                price: $ int
                -||-
            }
            ...
        ]
    }

*/