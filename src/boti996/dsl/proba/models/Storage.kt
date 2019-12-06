package boti996.dsl.proba.models

import boti996.dsl.proba.models.BonusProviders.Accessory

data class ShopEntry<T>(val price: Int, val item: T)

class Shop : AbstractStorage<ShopEntry<Fish>, ShopEntry<Accessory>>()

class Storage : AbstractStorage<Fish, Accessory>()


abstract class AbstractStorage<F, A> {
    val fishes: List<F> = mutableListOf()
    //TODO: getter-setter ?

    fun addFish(fish: F) {
        fishes as MutableList
        fishes.add(fish)
    }

    fun removeFish(fish: F) {
        fishes as MutableList
        fishes.remove(fish)
    }

    val accessories: List<A> = mutableListOf()
    //TODO: getter-setter ?

    fun addAccessory(accessory: A) {
        accessories as MutableList
        accessories.add(accessory)
    }

    fun removeAccessory(accessory: A) {
        accessories as MutableList
        accessories.remove(accessory)
    }



}