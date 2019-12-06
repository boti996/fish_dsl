package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.EnvironmentalBehaviour
import boti996.dsl.proba.models.BonusProviders.Equipment

data class Fish(override val type: Environment,
                val equipments: List<Equipment>) : EnvironmentalBehaviour, BonusAcceptor {

    var defense: Int = 100
    var strength: Int = 150
    var life: Int = 500
    var regeneration: Float = 0.01f

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
            }
        }
    }

    // TODO: setBonuses(), collect bonuses from level environment, equipments and level accessories

}