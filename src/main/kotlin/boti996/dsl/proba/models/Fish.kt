package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.EnvironmentalBehaviour
import boti996.dsl.proba.models.BonusProviders.Equipment

data class Fish(override val type: Environment,
                val equipments: List<Equipment> = listOf()) : EnvironmentalBehaviour, BonusAcceptor {

    private var defense: Int = 100
    private var strength: Int = 150
    private var life: Int = 500
    private var regeneration: Float = 0.01f

    override fun setBonus(bonus: Bonus) {
        for ((bonusParam, bonusValue) in bonus.getBonus()) {
            when(bonusParam) {
                BonusEffect.DEFENSE -> {
                    defense = (defense * bonusValue).toInt()
                }
                BonusEffect.STRENGTH -> {
                    strength = (strength * bonusValue).toInt()
                }
                BonusEffect.REGENERATION -> {
                    regeneration += bonusValue
                }
                //TODO: this can be extended later
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is Fish &&
                type == other.type &&
                equipments == other.equipments
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + equipments.hashCode()
        result = 31 * result + defense
        result = 31 * result + strength
        result = 31 * result + life
        result = 31 * result + regeneration.hashCode()
        return result
    }

    // TODO: setBonuses(), collect bonuses from level environment, equipments and level accessories

}