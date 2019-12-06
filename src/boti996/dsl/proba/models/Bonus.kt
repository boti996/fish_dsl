package boti996.dsl.proba.models

/**
 * this defines bonus-effect ID-s
 */
enum class BonusEffect(val literal: String) {
    REGENERATION("regeneration"),
    STRENGTH("strength"),
    DEFENSE("defense")
}

/**
 * bonus type: this defines a set of [BonusEffect]-s
 */
enum class BonusType {
    ANOXIA {
        override fun _getBonus(): Map<BonusEffect, Float> = mutableMapOf(
            BonusEffect.REGENERATION to 2.0f,
            BonusEffect.STRENGTH to 4.0f
        )
    },
    EXTRA_STRENGTH {
        override fun _getBonus(): Map<BonusEffect, Float> = mutableMapOf(
            BonusEffect.STRENGTH to 6.0f
        )
    },
    EXTRA_DEFENSE {
        override fun _getBonus(): Map<BonusEffect, Float> = mutableMapOf(
            BonusEffect.DEFENSE to 2.0f
        )
    },
    EXTRA_HEAL {
        override fun _getBonus(): Map<BonusEffect, Float> = mutableMapOf(
            BonusEffect.REGENERATION to 1.5f
        )

    };

    protected abstract fun _getBonus(): Map<BonusEffect, Float>

    /**
     * return a set of [BonusEffect]-s
     * @param multiplier optional, multiplies [BonusEffect]-s
     */
    fun getBonus(multiplier: Float = 1.0f): Map<BonusEffect, Float> {
        val map = _getBonus() as MutableMap
        map.keys.forEach { key -> map[key] = map[key]!!.times(multiplier) }
        return map
    }
}

/**
 * [Bonus] can be set on object to take effect
 */
interface BonusAcceptor {
    fun setBonus(bonus: Bonus)
}

/**
 * wrapper for [BonusType] with a multiplier parameter
 * @param multiplier multiplies [BonusEffect]-s
 */
data class Bonus(val bonus: BonusType, var multiplier: Float = 1.0f) {
    fun getBonus(): Map<BonusEffect, Float> = bonus.getBonus(multiplier)
    fun //TODO
}

/**
 * object has accessible [Bonus]-s
 */
// TODO: fishType parameter might not alway will be used
// TODO: genericity is not used in current version
interface BonusProvider<T : Enum<T>> {
    fun getBonuses(fishType: T? = null): List<Bonus>
}
