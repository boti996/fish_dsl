package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.Position

data class Level(val isSkippable: Boolean,
                 val isExtraAccessoriesEnabled: Boolean,
                 val extraMoneyBonus: Float,
                 val environment: Environment,
                 val fishes: List<Fish> = listOf(),
                 val accessories: List<Accessory> = listOf()) {

    override fun equals(other: Any?): Boolean {
        return other is Level &&
                isSkippable == other.isSkippable &&
                isExtraAccessoriesEnabled == other.isExtraAccessoriesEnabled &&
                extraMoneyBonus == other.extraMoneyBonus &&
                environment == other.environment &&
                fishes == other.fishes &&
                accessories == other.accessories
    }

    override fun hashCode(): Int {
        var result = isSkippable.hashCode()
        result = 31 * result + isExtraAccessoriesEnabled.hashCode()
        result = 31 * result + extraMoneyBonus.hashCode()
        result = 31 * result + environment.hashCode()
        result = 31 * result + fishes.hashCode()
        result = 31 * result + accessories.hashCode()
        return result
    }
}

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

    override fun equals(other: Any?): Boolean {
        return other is Game &&
                initialMoney == other.initialMoney &&
                moneyBonusPerLevel == other.moneyBonusPerLevel &&
                maxSkippableLevels == other.maxSkippableLevels &&
                levelSize == other.levelSize &&
                levels == other.levels &&
                storage == other.storage &&
                shop == other.shop
    }

    override fun hashCode(): Int {
        var result = initialMoney
        result = 31 * result + moneyBonusPerLevel.hashCode()
        result = 31 * result + maxSkippableLevels
        result = 31 * result + levelSize.hashCode()
        result = 31 * result + levels.hashCode()
        result = 31 * result + storage.hashCode()
        result = 31 * result + shop.hashCode()
        result = 31 * result + currentLevelIdx
        return result
    }
}
