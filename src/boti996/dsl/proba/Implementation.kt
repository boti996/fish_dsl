package boti996.dsl.proba

/*
Kell: game, levels, level, entities of level, accessories of level, entity, equioments of entity, equipment,
catalog: accessories, , placement_restriction, entities, my entites, boss, enemy, aggro, etc.,
collectable bonus money

    Game {
        Properties {
            initial_money: $ int
            money_bonus_per_level: % float
            max_skippable_levels: n int
            level_size: x y POS_TYPE
        }
        Levels: [
            Level {
                Properties {
                    is_skippable: bool
                    is_extra_accessories_enabled: bool
                    extra_money_bonus: % float
                    environment: enum ENV_TYPE
                }
                Accessories [
                    Accessory {
                        position: x y POS_TYPE
                        type: enum ACCESSORY_TYPE
                        Bonuses [
                            Bonus {
                                type: enum BONUS_TYPE
                                rate: % float
                            }
                        ]
                    }
                    ...
                ]
                Mobs [
                    Fish {
                        type: enum FISH_TYPE
                        Equipments [
                            Equipment {
                                type: enum EQUIPMENT_TYPE
                                Bonuses [
                                    Bonus {
                                        type: enum BONUS_TYPE
                                        rate % float
                                    }
                                    ...
                                ]
                            }
                            ...
                        ]
                    }
                    ...
                ]
            }
            ...
        ]
    }


    Storage {
        Fishes [
            Fish {
                -||-
            }
            ...
        ]
        Accessories [
            Accessory {
                -||-
            }
        ]
    }


    Shop {
        Fishes [
            Fish {
                price: $ int
                -||-
            }
            ...
        ]
        Accessories [
            Accessory {
                price: $ int
                -||-
            }
            ...
        ]
    }

*/