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
    }
}

/**
 * wrapper for [AccessoryType] with [Position] data
 * @param accessory is the type of accessory
 * @param position is the position of accessory on the map
 */
data class Accessory(val accessory: AccessoryType,
                     var position: Position,
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
}