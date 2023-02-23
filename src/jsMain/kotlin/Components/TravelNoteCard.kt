package Components

import Gallery
import Review
import TravelNoteParam
import csstype.ClassName
import kotlinx.browser.window
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import openModalDialog
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import react.*
import react.dom.html.ButtonType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.small
import react.dom.html.ReactHTML.strong

external interface TravelNoteCardProps : Props {
    var id: Int
    var imgUrl: String
    var title: String
    var noteText: String
    var city: String
}

val TravelNoteCard = FC<TravelNoteCardProps> { props ->
    var reviews: List<Review> by useState(emptyList())
    var gallery: List<Gallery> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            val param = TravelNoteParam(props.id)
            reviews = fetchReviews(param)
            gallery = fetchGallery(param)
        }
    }

    div {
        className = ClassName("col-md-4")
        div {
            className = ClassName("card mb-4 shadow-sm")
            img {
                className = ClassName("bd-placeholder-img card-img-top")
                src = props.imgUrl
                alt = "TravelNote image"
                width = 400.0
                height = 250.0
            }
            div {
                className = ClassName("card-body")
                strong {
                    className = ClassName("card-text")
                    +props.title
                }
                p {
                    className = ClassName("card-text")
                    +props.noteText
                }
                div {
                    className = ClassName("d-flex justify-content-between align-items-center")
                    small {
                        className = ClassName("text-muted")
                        +props.city
                    }
                    div {
                        className = ClassName("btn-group")
                        button {
                            className = ClassName("btn btn-sm btn-outline-secondary")
                            type = ButtonType.button
                            onClick = {
                                openModalDialog("reviewModalForm${props.id}")
                            }
                            +"Заметки"
                        }
                        button {
                            className = ClassName("btn btn-sm btn-outline-secondary")
                            type = ButtonType.button
                            onClick = {
                                openModalDialog("galleryModalForm${props.id}")
                            }
                            +"Фото"
                        }
                    }
                }
            }
        }
    }

    val texts = mutableListOf<String>()
    reviews.forEach {
        it.reviewText?.let { it1 -> texts.add(it1) }
    }

    val imgUrls = mutableListOf<String>()
    gallery.forEach {
        it.photoLink?.let { it1 -> imgUrls.add(it1) }
    }

    val reviewElement = ReviewPanel.create {
        travelNoteId = props.id
        reviewTexts = texts
    }
    val galleryElement = GalleryPanel.create {
        travelNoteId = props.id
        photoLinks = imgUrls
    }
    child(reviewElement)
    child(galleryElement)
}


suspend fun fetchReviews(param: TravelNoteParam): List<Review> = coroutineScope {
    async {
        loadReviewsFromDB(param)
    }.await()
}

suspend fun loadReviewsFromDB(param: TravelNoteParam): List<Review> {
    val response = window
        .fetch("/getReviewByTravelNoteId", RequestInit(
            method = "POST",
            body = Json.encodeToString(param),
            headers = Headers().also {
                it.set("Accept", "application/json")
                it.set("Content-Type", "application/json")
            }
        ))
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}


suspend fun fetchGallery(param: TravelNoteParam): List<Gallery> = coroutineScope {
    async {
        loadGalleryFromDB(param)
    }.await()
}

suspend fun loadGalleryFromDB(param: TravelNoteParam): List<Gallery> {
    val response = window
        .fetch("/getGalleryByTravelNoteId", RequestInit(
            method = "POST",
            body = Json.encodeToString(param),
            headers = Headers().also {
                it.set("Accept", "application/json")
                it.set("Content-Type", "application/json")
            }
        ))
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}

