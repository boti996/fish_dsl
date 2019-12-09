package boti996.dsl.proba.tests

import boti996.dsl.proba.GameBuilder
import boti996.dsl.proba.game
import boti996.dsl.proba.models.BonusProviders.Position
import boti996.dsl.proba.models.Game
import boti996.dsl.proba.models.Level

internal fun getDefaultGameObject(levels : List<Level> = listOf()) = Game(
    100,
    0.1f,
    0,
    Position(100, 50),
    levels)

internal fun getDefaultGameDSLObject(content : GameBuilder.() -> Unit) =
    game(
        100,
        0.1f,
        0,
        Position(100, 50),
        content
    )
