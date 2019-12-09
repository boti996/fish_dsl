package boti996.dsl.proba.models.BonusProviders

import boti996.dsl.proba.models.Bonus
import boti996.dsl.proba.models.BonusProvider
import boti996.dsl.proba.models.BonusType
import boti996.dsl.proba.models.Game

/**
 * specifies additional [Environment]-dependent [Bonus]-s for fishes
 */
enum class Environment :
    BonusProvider<Environment> {
    TROPICAL {
        override fun getBonuses(modifierType: Environment?): List<Bonus> {
            return when(modifierType) {
                TROPICAL -> {
                    listOf(
                        Bonus(
                            BonusType.EXTRA_STRENGTH,
                            2.0f
                        )
                    )
                }
                RIVER -> {
                    listOf(Bonus(BonusType.ANOXIA))
                }
                else -> listOf()
            }
        }

    },
    RIVER {
        override fun getBonuses(modifierType: Environment?): List<Bonus> {
            return when(modifierType) {
                TROPICAL -> {
                    listOf(
                        Bonus(
                            BonusType.ANOXIA,
                            2.0f
                        )
                    )
                }
                RIVER -> {
                    listOf(
                        Bonus(
                            BonusType.EXTRA_STRENGTH,
                            2.0f
                        )
                    )
                }
                DEEP_SEA -> {
                    listOf(
                        Bonus(
                            BonusType.ANOXIA,
                            3.0f
                        )
                    )
                }
                else -> listOf()
            }
        }

    },
    DEEP_SEA {
        override fun getBonuses(modifierType: Environment?): List<Bonus> {
            return when (modifierType) {
                TROPICAL -> {
                    listOf(
                        Bonus(
                            BonusType.ANOXIA,
                            0.5f
                        ),
                        Bonus(
                            BonusType.EXTRA_STRENGTH,
                            -0.5f
                        )
                    )
                }
                RIVER -> {
                    listOf(
                        Bonus(
                            BonusType.ANOXIA,
                            2.0f
                        )
                    )
                }
                DEEP_SEA -> {
                    listOf(
                        Bonus(
                            BonusType.EXTRA_STRENGTH,
                            2.0f
                        )
                    )
                }
                else -> listOf()
            }
        }

    };
}

/**
 * position of something on map
 */
data class Position(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        return other is Position &&
                x == other.x &&
                y == other.y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

/**
 * object has [Environment]-dependent behaviour
 */
interface EnvironmentalBehaviour {

    val type: Environment

    fun setEnvironmentalBonuses(game: Game): List<Bonus> {
        val currLevel = game.getCurrentLevel()
        val currEnvironment = currLevel.environment
        return currEnvironment.getBonuses(type)
    }
}
