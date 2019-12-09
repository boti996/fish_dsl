package boti996.dsl.proba

import boti996.dsl.proba.models.*
import boti996.dsl.proba.models.BonusProviders.*
import kotlin.test.assertNull

@DslMarker
annotation class GameDsl

@GameDsl
data class BonusBuilder(val type: BonusType,
                        val multiplier: Float) {

    fun build() : Bonus {
        return Bonus(type, multiplier)
    }
}

@GameDsl
data class AccessoryBuilder(val accessory: AccessoryType,
                            val position: Position) {

    private val bonuses = mutableListOf<Bonus>()

    operator fun Bonus.unaryPlus() {
        bonuses += this
    }

    fun bonus(type: BonusType,
              multiplier: Float = 1.0F,
              setup: BonusBuilder.() -> Unit = {}) {

        val bonusBuilder = BonusBuilder(type, multiplier)
        bonusBuilder.setup()
        bonuses += bonusBuilder.build()
    }

    fun build(): Accessory {
        return Accessory(accessory, position, bonuses)
    }
}

@GameDsl
data class EquipmentBuilder(val equipment: Equipment) {

    fun build() : Equipment {
        return equipment
    }
}

@GameDsl
data class FishBuilder(val type: Environment) {

    private val equipments = mutableListOf<Equipment>()

    operator fun Equipment.unaryPlus() {
        equipments += this
    }

    fun equipment(type: Equipment,
                  setup: EquipmentBuilder.() -> Unit = {}) {

        val equipmentBuilder = EquipmentBuilder(type)
        equipmentBuilder.setup()
        equipments += equipmentBuilder.build()
    }

    fun build(): Fish {
        return Fish(type, equipments)
    }
}

abstract class ShopEntryContainer {
    val fishes = mutableListOf<ShopEntry<Fish>>()
    val accessories = mutableListOf<ShopEntry<Accessory>>()

    operator fun ShopEntry<Any>.unaryPlus() {
        @Suppress("UNCHECKED_CAST")
        when (this.item) {
            is Fish -> fishes += this as ShopEntry<Fish>
            is Accessory -> accessories += this as ShopEntry<Accessory>
        }
    }

    fun fish(price: Int,
             type: Environment,
             setup: FishBuilder.() -> Unit = {}) {

        val fishBuilder = FishBuilder(type)
        fishBuilder.setup()
        fishes += ShopEntry(price, fishBuilder.build())
    }

    fun accessory(price: Int,
                  accessory: AccessoryType,
                  position: Position = Position(0, 0),
                  setup: AccessoryBuilder.() -> Unit = {}) {

        val accessoryBuilder = AccessoryBuilder(accessory, position)
        accessoryBuilder.setup()
        accessories += ShopEntry(price, accessoryBuilder.build())
    }
}

abstract class FishAccessoryContainer {
    val fishes = mutableListOf<Fish>()
    val accessories = mutableListOf<Accessory>()

    operator fun Fish.unaryPlus() {
        fishes += this
    }

    operator fun Accessory.unaryPlus() {
        accessories += this
    }

    fun fish(type: Environment,
             setup: FishBuilder.() -> Unit = {}) {

        val fishBuilder = FishBuilder(type)
        fishBuilder.setup()
        fishes += fishBuilder.build()
    }

    fun accessory(accessory: AccessoryType,
                  position: Position = Position(0, 0),
                  setup: AccessoryBuilder.() -> Unit = {}) {

        val accessoryBuilder = AccessoryBuilder(accessory, position)
        accessoryBuilder.setup()
        accessories += accessoryBuilder.build()
    }
}

@GameDsl
data class LevelBuilder(val isSkippable: Boolean,
                        val isExtraAccessoriesEnabled: Boolean,
                        val extraMoneyBonus: Float,
                        val environment: Environment) : FishAccessoryContainer() {

    fun build(): Level {
        return Level(isSkippable, isExtraAccessoriesEnabled, extraMoneyBonus, environment, fishes, accessories)
    }
}

@GameDsl
class StorageBuilder : FishAccessoryContainer() {

    fun build() : Storage {
        val storage = Storage()
        for (fish in fishes)
            storage.addFish(fish)
        for (accessory in accessories)
            storage.addAccessory(accessory)
        return storage
    }
}

@GameDsl
class ShopBuilder : ShopEntryContainer() {

    fun build() : Shop {
        val shop = Shop()
        for (fish in fishes)
            shop.addFish(fish)
        for (accessory in accessories)
            shop.addAccessory(accessory)
        return shop
    }
}

@GameDsl
data class GameBuilder(val initialMoney: Int,
                       val moneyBonusPerLevel: Float,
                       val maxSkippableLevels: Int,
                       val levelSize: Position) {

    private val levels = mutableListOf<Level>()
    private var storage : Storage? = null
    private var shop : Shop? = null

    operator fun Level.unaryPlus() {
        levels += this
    }

    fun storage(setup: StorageBuilder.() -> Unit = {}) {
        assertNull(storage, "Only one storage must be defined")

        val storageBuilder = StorageBuilder()
        storageBuilder.setup()
        storage = storageBuilder.build()
    }

    fun shop(setup: ShopBuilder.() -> Unit = {}) {
        assertNull(shop, "Only one shop must be defined")

        val shopBuilder = ShopBuilder()
        shopBuilder.setup()
        shop = shopBuilder.build()
    }

    fun level(isSkippable: Boolean  = false,
              isExtraAccessoriesEnabled: Boolean = true,
              extraMoneyBonus: Float = 0.0F,
              environment: Environment = Environment.RIVER,
              setup: LevelBuilder.() -> Unit = {}) {

        val levelBuilder = LevelBuilder(isSkippable, isExtraAccessoriesEnabled, extraMoneyBonus, environment)
        levelBuilder.setup()
        levels += levelBuilder.build()
    }

    fun build(): Game {
        val game = Game(initialMoney, moneyBonusPerLevel, maxSkippableLevels, levelSize, levels)
        storage?.let { game.storage = it }
        shop?.let { game.shop = it }
        return game
    }

    /**
     * This method shadows the [game] method when inside the scope
     * of a [GameBuilder], so that games can't be nested.
     */
    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR,
        message = "Games can't be nested.")
    fun game(param: () -> Unit = {}) {
    }
}


@GameDsl
fun game(initialMoney: Int = 100,
         moneyBonusPerLevel: Float = 0.1F,
         maxSkippableLevels: Int = 0,
         levelSize: Position  = Position(100, 50),
         setup: GameBuilder.() -> Unit): Game {

    val gameBuilder = GameBuilder(initialMoney, moneyBonusPerLevel, maxSkippableLevels, levelSize)
    gameBuilder.setup()
    return gameBuilder.build()
}

@GameDsl
fun note(message: String) {}

/* TODO: other:
 *      - mobs {}, fishes {}, accessories {}, levels {}, equipments {} (optional) visual containers
 */