package Components

import TravelNote
import csstype.ClassName
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.*
import react.dom.html.ReactHTML.div

val mainScope = MainScope()

external interface TravelNoteCardsContainerProps : Props

val TravelNoteCardsContainer = FC<TravelNoteCardsContainerProps> {
    var unwatchedNotes: List<TravelNote> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            unwatchedNotes = fetchNotes()
        }
    }

    div {
        className = ClassName("container album py-5 bg-light")
        div {
            className = ClassName("row")

            unwatchedNotes.forEach {
                val travelNoteTest = TravelNoteCard.create {
                    id= it.id!!
                    title = it.title!!
                    city = it.city!!
                    noteText = it.noteText!!
                    imgUrl = it.imgUrl!!
                }
                child(travelNoteTest)
            }
        }
    }
}

suspend fun fetchNotes(): List<TravelNote> = coroutineScope {
    async {
        loadTravelNoteFromDB()
    }.await()
}

suspend fun loadTravelNoteFromDB(): List<TravelNote> {
    val response = window
        .fetch("/loadTravelNotes")
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}