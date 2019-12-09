package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.Position

data class Level(val isSkippable: Boolean,
                 val isExtraAccessoriesEnabled: Boolean,
                 val extraMoneyBonus: Float,
                 val environment: Environment,
                 val fishes: List<Fish>,
                 val accessories: List<Accessory>)

data class Game(val initialMoney: Int,
                val moneyBonusPerLevel: Float,
                val maxSkippableLevels: Int,
                val levelSize: Position,
                val levels: List<Level>) {

    var storage = Storage()
    var shop = Shop()

    private var currentLevelIdx = 0

    fun getCurrentLevel() : Level {
        return levels[currentLevelIdx]
    }

    fun setCurrentLevel(levelIdx : Int, isSkipping: Boolean = false) : Boolean {
        if (levels.size <= levelIdx) return false   // non-existent level
        if (isSkipping) {
            if (levelIdx - currentLevelIdx > maxSkippableLevels) return false   // too much skipped level
            for (idx in currentLevelIdx until levelIdx)
                if (!levels[idx].isSkippable) return false  // non-skippable level
        }
        currentLevelIdx = levelIdx  // otherwise
        return true
    }
}
