package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.Position

data class Level(val is_skippable: Boolean,
                 val is_extra_accessories_enabled: Boolean,
                 val extra_money_bonus: Float,
                 val environment: Environment,
                 val fishes: List<Fish>,
                 val accessories: List<Accessory>) {
    //TODO: add fishes and equipments
}




data class Game(val initial_money: Int,
                val money_bonus_per_level: Float,
                val max_skippable_levels: Int,
                val level_size: Position,
                val levels: List<Level>
) {

    fun getCurrentLevel() : Level {
        //TODO:
    }

    // TODO: add levels
}
