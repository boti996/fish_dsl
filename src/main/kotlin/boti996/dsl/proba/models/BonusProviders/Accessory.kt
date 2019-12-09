package boti996.dsl.proba.models.BonusProviders

import boti996.dsl.proba.models.Bonus
import boti996.dsl.proba.models.BonusProvider
import boti996.dsl.proba.models.BonusType

/**
 * specifies additional [AccessoryType]-dependent [Bonus]-s for fishes
 */
enum class AccessoryType : BonusProvider<Environment> {
    OXYGEN_PUMP {
        override fun getBonuses(modifierType: Environment?): List<Bonus> {
            return listOf(Bonus(BonusType.ANOXIA, -0.5f))
        }
    },
    STRENGTH_AMPLIFIER {
        override fun getBonuses(modifierType: Environment?): List<Bonus> {
            return listOf(Bonus(BonusType.EXTRA_STRENGTH, 1.0f))
        }
    },
    POISON {
        override fun getBonuses(modifierType: Environment?): List<Bonus> {
            return listOf(Bonus(BonusType.EXTRA_STRENGTH, -0.2f),
                          Bonus(BonusType.EXTRA_HEAL, -0.1f)
            )
        }
    },
    TROPICAL_AMPLIFIER {
        override fun getBonuses(modifierType: Environment?): List<Bonus> {
            modifierType?.let {
                if (modifierType == Environment.TROPICAL)
                    return listOf(Bonus(BonusType.EXTRA_STRENGTH, 2.0f))
            }
            return listOf(Bonus(BonusType.EXTRA_STRENGTH, 0.0f))
        }
    }
}

/**
 * wrapper for [AccessoryType] with [Position] data
 * @param accessory is the type of accessory
 * @param position is the position of accessory on the map
 */
data class Accessory(val accessory: AccessoryType,
                     var position: Position = Position(0, 0),
                     val additionalBonuses: List<Bonus> = listOf()) : BonusProvider<Environment> {

    override fun getBonuses(modifierType: Environment?): List<Bonus> {
        val bonuses = accessory.getBonuses(modifierType)
        return if (additionalBonuses.isEmpty()) bonuses
        else {
            val extendedBonuses = bonuses.toMutableList()
            extendedBonuses.addAll(additionalBonuses)
            extendedBonuses
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is Accessory &&
                accessory == other.accessory &&
                position == other.position &&
                additionalBonuses == other.additionalBonuses
    }

    override fun hashCode(): Int {
        var result = accessory.hashCode()
        result = 31 * result + position.hashCode()
        result = 31 * result + additionalBonuses.hashCode()
        return result
    }
}
