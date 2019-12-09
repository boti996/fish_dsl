package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Accessory
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.Position

data class Level(val isSkippable: Boolean,
                 val isExtraAccessoriesEnabled: Boolean,
                 val extraMoneyBonus: Float,
                 val environment: Environment,
                 val fishes: List<Fish>,
                 val accessories: List<Accessory>) {
    //TODO: add fishes and equipments
}




data class Game(val initialMoney: Int,
                val moneyBonusPerLevel: Float,
                val maxSkippableLevels: Int,
                val levelSize: Position,
                val levels: List<Level>) {

    private var storage = Storage()
    fun getStorage() = storage
    fun setStorage(newStorage : Storage) { storage  = newStorage }

    private var shop = Shop()
    fun getShop() = shop
    fun setShop(newShop : Shop) { shop = newShop }

    fun getCurrentLevel() : Level {
        TODO()
    }
}
