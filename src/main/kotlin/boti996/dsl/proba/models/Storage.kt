package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Accessory

data class ShopEntry<T>(val price: Int, val item: T) {

    override fun equals(other: Any?): Boolean {
        return other is ShopEntry<*> &&
                price == other.price &&
                item == other.item
    }

    override fun hashCode(): Int {
        var result = price
        result = 31 * result + (item?.hashCode() ?: 0)
        return result
    }
}

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

    fun addFishes(fishes: List<F>) {
        fishes.forEach { addFish(it) }
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

    fun addAccessories(accessories: List<A>) {
        accessories.forEach { addAccessory(it) }
    }

    override fun equals(other: Any?): Boolean {
        return other is AbstractStorage<*, *> &&
                fishes == other.fishes &&
                accessories == other.accessories
    }

    override fun hashCode(): Int {
        var result = fishes.hashCode()
        result = 31 * result + accessories.hashCode()
        return result
    }
}