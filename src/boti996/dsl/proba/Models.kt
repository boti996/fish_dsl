package boti996.dsl.proba

data class Position(val x: Int, val y: Int)

//TODO: Game, Level, Accessory, Bonus, Fish, Equipment, Storage, Shop

enum class BonusParam(val literal: String) {
    LIFE_PER_ROUND("lifePerRound"),
    STRENGTH("strength"),
    DEFENSE("defense")
}


enum class BonusType {
    ANOXIA {
        override fun _getBonus(): Map<BonusParam, Float> = mutableMapOf(
            BonusParam.LIFE_PER_ROUND to 2.0f,
            BonusParam.STRENGTH to 4.0f
        )
    },
    EXTRA_STRENGTH {
        override fun _getBonus(): Map<BonusParam, Float> = mutableMapOf(
            BonusParam.STRENGTH to 6.0f
        )
    },
    EXTRA_DEFENSE {
        override fun _getBonus(): Map<BonusParam, Float> = mutableMapOf(
            BonusParam.DEFENSE to 2.0f
        )
    },
    EXTRA_HEAL {
        override fun _getBonus(): Map<BonusParam, Float> = mutableMapOf(
            BonusParam.LIFE_PER_ROUND to 1.5f
        )

    };

    abstract fun _getBonus(): Map<BonusParam, Float>

    fun getBonus(multiplier: Float = 1.0f): Map<BonusParam, Float> {
        val map = _getBonus() as MutableMap
        map.keys.forEach { key -> map[key] = map[key]!!.times(multiplier) }
        return map
    }
}

data class Bonus(val bonus: BonusType, var multiplier: Float = 1.0f) {
    fun getBonus(): Map<BonusParam, Float> = bonus.getBonus(multiplier)
}


// TODO: fishType parameter might not alway will be used
// TODO: genericity is not used in current version
interface BonusProvider<T : Enum<T>> {
    fun getBonuses(fishType: T): List<Bonus>
}

enum class Environment : BonusProvider<Environment> {
    TROPICAL {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            return when(fishType) {
                TROPICAL -> {
                    listOf(Bonus(BonusType.EXTRA_STRENGTH, 2.0f))
                }
                RIVER -> {
                    listOf(Bonus(BonusType.ANOXIA))
                }
                else -> listOf()
            }
        }

    },
    RIVER {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            return when(fishType) {
                TROPICAL -> {
                    listOf(Bonus(BonusType.ANOXIA, 2.0f))
                }
                RIVER -> {
                    listOf(Bonus(BonusType.EXTRA_STRENGTH, 2.0f))
                }
                DEEP_SEA -> {
                    listOf(Bonus(BonusType.ANOXIA, 3.0f))
                }
                else -> listOf()
            }
        }

    },
    DEEP_SEA {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            return when (fishType) {
                TROPICAL -> {
                    listOf(Bonus(BonusType.ANOXIA, 0.5f), Bonus(BonusType.EXTRA_STRENGTH, -0.5f))
                }
                RIVER -> {
                    listOf(Bonus(BonusType.ANOXIA, 2.0f))
                }
                DEEP_SEA -> {
                    listOf(Bonus(BonusType.EXTRA_STRENGTH, 2.0f))
                }
                else -> listOf()
            }
        }

    };
}



interface EnvironmentalBehaviour {
    val type: Environment
    fun setEnvironmentBonuses(game: Game): List<Bonus> {
        val currLevel = game.getCurrentLevel()
        val currEnvironment = currLevel.environment
        return currEnvironment.getBonuses(type)
    }
}

enum class Equipment : BonusProvider<Environment> {
    SHIELD {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            return listOf(Bonus(BonusType.EXTRA_DEFENSE, 0.5f))
        }
    },
    SHIELD_OF_HEALING {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            val list = Equipment.SHIELD.getBonuses(fishType) as MutableList
            list.addAll(listOf(Bonus(BonusType.EXTRA_HEAL, 0.5f)))
            return list
            //TODO test it! cast immutable list to mutable list ?
        }

    },
    WEAPON {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            return listOf(Bonus(BonusType.EXTRA_STRENGTH, 0.5f))
        }
    },
    WEAPON_OF_RIVER_FISH {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            val multiplier = if (fishType == Environment.RIVER) 1.5f else 1.0f
            return Equipment.WEAPON.getBonuses(fishType)
                .map { e -> e.multiplier *= multiplier; e }
            // TODO: test it! amplification of WEAPON's bonuses
        }

    };

}

enum class AccessoryType : BonusProvider<Environment> {
    OXYGEN_PUMP {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            return listOf(Bonus(BonusType.ANOXIA, -0.5f))
        }
    },
    STRENGTH_AMPLIFIER {
        override fun getBonuses(fishType: Environment): List<Bonus> {
            return listOf(Bonus(BonusType.EXTRA_STRENGTH, 1.0f))
        }
    }

}

data class Accessory(val accessory: AccessoryType, var position: Position) : BonusProvider<Environment>{
    override fun getBonuses(fishType: Environment): List<Bonus> = accessory.getBonuses(fishType)
}

data class Fish(override val type: Environment,
                val equipments: List<Equipment>) : EnvironmentalBehaviour {
    // TODO: add equipments
}

data class Level(val is_skippable: Boolean,
                 val is_extra_accessories_enabled: Boolean,
                 val extra_money_bonus: Float,
                 val environment: Environment,
                 val fishes: List<Fish>,
                 val accessories: List<Accessory>) {
    //TODO: add fishes and equipments
}

data class ShopEntry<T>(val price: Int, val item: T)

class Shop : AbstractStorage<ShopEntry<Fish>, ShopEntry<Accessory>>()

class Storage : AbstractStorage<Fish, Accessory>()


abstract class AbstractStorage<F, A> {
    val fishes: List<F> = mutableListOf()

    fun addFish(fish: F) {
        fishes as MutableList
        fishes.add(fish)
    }

    fun removeFish(fish: F) {
        fishes as MutableList
        fishes.remove(fish)
    }

    val accessories: List<A> = mutableListOf()

    fun addAccessory(accessory: A) {
        accessories as MutableList
        accessories.add(accessory)
    }

    fun removeAccessory(accessory: A) {
        accessories as MutableList
        accessories.remove(accessory)
    }



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

//TODO: categorize enemies into " enemy waves"

//TODO: support serialization

//data class Person(val name: String, val age: Int)
//
//data class House(val people: List<Person>)
//
//data class Village(val houses: List<House>)
