package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Accessory

data class ShopEntry<T>(val price: Int, val item: T)

class Shop : AbstractStorage<ShopEntry<Fish>, ShopEntry<Accessory>>()

class Storage : AbstractStorage<Fish, Accessory>()

abstract class AbstractStorage<F, A> {
    private val fishes: List<F> = mutableListOf()

    fun addFish(fish: F) {
        fishes as MutableList
        fishes.add(fish)
    }

    fun removeFish(fish: F) {
        fishes as MutableList
        fishes.remove(fish)
    }

    private val accessories: List<A> = mutableListOf()

    fun addAccessory(accessory: A) {
        accessories as MutableList
        accessories.add(accessory)
    }

    fun removeAccessory(accessory: A) {
        accessories as MutableList
        accessories.remove(accessory)
    }
}