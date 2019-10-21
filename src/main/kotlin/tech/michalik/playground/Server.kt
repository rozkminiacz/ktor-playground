package tech.michalik.playground

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

fun Application.main() {

    val repository: Repository<String, Item> = ItemRepository()

    install(ContentNegotiation) {
        gson()
    }

    routing {

        post("/items") {
            val request = call.receive<Item>()

            val item = repository.add(request)
            call.respond(HttpStatusCode.Created, item)
        }

        get("/items") {
            val itemList = repository.getAll()
            call.respond(itemList)
        }

        delete("/items/{id}") {
            call.parameters["id"]?.let { id ->
                repository.remove(id)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
