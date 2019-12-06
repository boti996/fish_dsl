package boti996.dsl.proba

import boti996.dsl.proba.models.*
import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.AccessoryType
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.Position

@DslMarker
annotation class GameDsl


@GameDsl
class AccessoryBuilder(
    accessory: AccessoryType,
    position: Position
) {
    fun build(): Accessory {
        TODO()
    }

}


@GameDsl
class FishBuilder(type: Environment) {
    fun build(): Fish {
        TODO()
    }

}

@GameDsl
data class LevelBuilder(val isSkippable: Boolean,
                        val isExtraAccessoriesEnabled: Boolean,
                        val extraMoneyBonus: Float,
                        val environment: Environment) {

    val fishes = mutableListOf<Fish>()
    val accessories = mutableListOf<Accessory>()

    operator fun Fish.unaryPlus() {
        fishes += this
    }

    operator fun Accessory.unaryPlus() {
        accessories += this
    }

    fun fish(type: Environment,
             setup: FishBuilder.() -> Unit = {}) {

        val fishBuilder = FishBuilder(type)
        fishBuilder.setup()
        fishes += fishBuilder.build()
    }

    fun accessory(accessory: AccessoryType,
                  position: Position,
                  setup: AccessoryBuilder.() -> Unit = {}) {

        val accessoryBuilder = AccessoryBuilder(accessory, position)
        accessoryBuilder.setup()
        accessories += accessoryBuilder.build()
    }

    fun build(): Level {
        return Level(isSkippable, isExtraAccessoriesEnabled, extraMoneyBonus, environment, fishes, accessories)
    }

}


@GameDsl
data class GameBuilder(val initialMoney: Int,
                       val moneyBonusPerLevel: Float,
                       val maxSkippableLevels: Int,
                       val levelSize: Position) {

    val levels = mutableListOf<Level>()
    //TODO: game must contain 1! storage and shop
    val storage = Storage()
    val shop = Shop()

    /**
     * add new levels to the game
     */
    operator fun Level.unaryPlus() {
        levels += this
    }

    fun level(isSkippable: Boolean = false,
              isExtraAccessoriesEnabled: Boolean = true,
              extraMoneyBonus: Float = 0.0F,
              environment: Environment = Environment.RIVER,
              setup: LevelBuilder.() -> Unit = {}) {

        val levelBuilder = LevelBuilder(isSkippable, isExtraAccessoriesEnabled, extraMoneyBonus, environment)
        levelBuilder.setup()
        levels += levelBuilder.build()
    }

    fun build(): Game {
        return Game(initialMoney, moneyBonusPerLevel, maxSkippableLevels, levelSize, levels)
    }

    /**
     * This method shadows the [game] method when inside the scope
     * of a [GameBuilder], so that games can't be nested.
     */
    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR,
        message = "Games can't be nested.")
    fun game(param: () -> Unit = {}) {
    }

}


@GameDsl
fun game(initialMoney: Int = 100,
         moneyBonusPerLevel: Float = 0.1F,
         maxSkippableLevels: Int = 0,
         levelSize: Position = Position(100, 50),
         setup: GameBuilder.() -> Unit): Game {

    val gameBuilder = GameBuilder(initialMoney, moneyBonusPerLevel, maxSkippableLevels, levelSize)
    gameBuilder.setup()
    return gameBuilder.build()
}



/*
Kell: game, levels, level, entities of level, accessories of level, entity, equioments of entity, equipment,
catalog: accessories, , placement_restriction, entities, my entites, boss, enemy, aggro, etc.,
collectable bonus money

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