package boti996.dsl.proba.models.BonusProviders

import boti996.dsl.proba.models.Bonus
import boti996.dsl.proba.models.BonusProvider
import boti996.dsl.proba.models.BonusType

/**
 * specifies additional [Equipment]-dependent [Bonus]-s for fishes
 */
enum class Equipment : BonusProvider<Environment> {
    SHIELD {
        override fun getBonuses(fishType: Environment?) : List<Bonus> {
            return listOf(
                Bonus(
                    BonusType.EXTRA_DEFENSE,
                    0.5f
                )
            )
        }
    },
    SHIELD_OF_HEALING {
        override fun getBonuses(fishType: Environment?): List<Bonus> {
            val list = SHIELD.getBonuses(fishType) as MutableList
            list.addAll(listOf(
                Bonus(
                    BonusType.EXTRA_HEAL,
                    0.5f
                )
            ))
            return list
            //TODO test it! cast immutable list to mutable list ?
        }

    },
    WEAPON {
        override fun getBonuses(fishType: Environment?): List<Bonus> {
            return listOf(
                Bonus(
                    BonusType.EXTRA_STRENGTH,
                    0.5f
                )
            )
        }
    },
    WEAPON_OF_RIVER_FISH {
        override fun getBonuses(fishType: Environment?): List<Bonus> {
            val multiplier = if (fishType == Environment.RIVER) 1.5f else 1.0f
            return WEAPON.getBonuses(fishType)
                .map { e -> e.multiplier *= multiplier; e }
            // TODO: test it! amplification of WEAPON's bonuses
        }

    };

}
