package tech.michalik.playground

data class Item(val id: String, val message: String)

class ItemRepository : Repository<String, Item> {

    val dataSource = mutableMapOf<String, Item>()

    override fun getAll(): List<Item> {
        return dataSource.values.toList()
    }

    override fun add(entity: Item): Item {
        dataSource[entity.id] = entity
        return entity
    }

    override fun remove(id: String) {
        dataSource.remove(id)
    }
}