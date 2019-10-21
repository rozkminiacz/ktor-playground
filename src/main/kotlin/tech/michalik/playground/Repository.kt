package tech.michalik.playground

interface Repository<in Id, Entity>{
    fun getAll(): List<Entity>
    fun add(entity: Entity): Item
    fun remove(id: Id)
}