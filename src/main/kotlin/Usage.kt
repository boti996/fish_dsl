import boti996.dsl.proba.game
import boti996.dsl.proba.models.BonusProviders.AccessoryType
import boti996.dsl.proba.models.BonusProviders.Environment
import boti996.dsl.proba.models.BonusProviders.Equipment
import boti996.dsl.proba.models.BonusProviders.Position
import boti996.dsl.proba.models.BonusType
import boti996.dsl.proba.note

fun main() {
    val game = game(500, 0.5F, 1, Position(60, 40)) {

        note("Level 1")
        level(extraMoneyBonus = 0.1F, environment = Environment.TROPICAL) {
            note("Mobs")
            fish(Environment.TROPICAL)
            fish(Environment.TROPICAL)
            fish(Environment.TROPICAL)
            fish(Environment.TROPICAL)
            fish(Environment.TROPICAL)
        }
        note("Level 2")
        level(
            isSkippable = true,
            isExtraAccessoriesEnabled = false,
            extraMoneyBonus = 0.2F,
            environment = Environment.DEEP_SEA
        ) {
            note("Mobs")
            fish(Environment.DEEP_SEA)
            fish(Environment.DEEP_SEA)
            fish(Environment.DEEP_SEA)
            fish(Environment.DEEP_SEA) {
                equipment(Equipment.SHIELD)
            }

            fish(Environment.DEEP_SEA) {
                equipment(Equipment.SHIELD)
            }

            fish(Environment.DEEP_SEA) {
                equipment(Equipment.SHIELD)
                equipment(Equipment.WEAPON)
            }

            note("Map accessories")
            accessory(AccessoryType.POISON) {
                bonus(BonusType.ANOXIA)
            }

            accessory(AccessoryType.STRENGTH_AMPLIFIER) {
                bonus(BonusType.EXTRA_STRENGTH, 1.2f)
                bonus(BonusType.EXTRA_DEFENSE)
            }
        }
        note("Level 3")
        level {
            note("Mobs")
            fish(Environment.RIVER)
            fish(Environment.RIVER)
            fish(Environment.RIVER)
            fish(Environment.RIVER)
            fish(Environment.RIVER) {
                equipment(Equipment.WEAPON)
            }

            fish(Environment.RIVER) {
                equipment(Equipment.WEAPON)
            }

            fish(Environment.RIVER) {
                equipment(Equipment.WEAPON)
            }

            fish(Environment.RIVER) {
                equipment(Equipment.SHIELD_OF_HEALING)
            }

            fish(Environment.RIVER) {
                equipment(Equipment.SHIELD_OF_HEALING)
            }

            fish(Environment.RIVER) {
                equipment(Equipment.SHIELD_OF_HEALING)
                equipment(Equipment.WEAPON_OF_RIVER_FISH)
            }

            note("Map accessories")
            accessory(AccessoryType.OXYGEN_PUMP)
        }


        storage {
            note("Fishes")
            fish(Environment.RIVER)
            fish(Environment.RIVER) {
                equipment(Equipment.WEAPON_OF_RIVER_FISH)
            }

            fish(Environment.DEEP_SEA) {
                equipment(Equipment.SHIELD_OF_HEALING)
            }

            fish(Environment.TROPICAL)
            fish(Environment.TROPICAL) {
                equipment(Equipment.SHIELD)
                equipment(Equipment.WEAPON)
            }

            note("Accessories")
            accessory(AccessoryType.OXYGEN_PUMP, Position(0, 0)) {
                bonus(BonusType.EXTRA_DEFENSE, 1.2f)
                bonus(BonusType.EXTRA_STRENGTH, 1.05f)
            }

            accessory(AccessoryType.STRENGTH_AMPLIFIER, Position(30, 40)) {
                bonus(BonusType.EXTRA_HEAL)
            }
        }


        shop {
            note("Fishes")
            fish(100, Environment.RIVER) {
                note("Guppy")
            }

            fish(300, Environment.TROPICAL) {
                note("Barracuda")
                equipment(Equipment.SHIELD)
            }

            fish(800, Environment.DEEP_SEA) {
                note("Fangtooth")
                equipment(Equipment.SHIELD)
                equipment(Equipment.WEAPON)
            }

            note("Accessories")
            accessory(500, AccessoryType.OXYGEN_PUMP, Position(0, 0)) {
                note("Basic oxygen pump")
                bonus(BonusType.ANOXIA, -0.5f)
            }

            accessory(1000, AccessoryType.STRENGTH_AMPLIFIER, Position(30, 40)) {
                note("Basic amplifier")
            }
        }
    }

    print(game.toString().replace(", ", ",\n").replace("[", "[\n").replace("]", "\n]"))
}
