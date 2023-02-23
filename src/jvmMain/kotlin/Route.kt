package org.example.application

import Gallery
import Review
import TravelNote
import TravelNoteParam
import insertGallery
import insertReview
import insertTravelNote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import selectGalleryByTravelNoteId
import selectReviewByTravelNoteId
import selectTravelNotes

fun Routing.addNote() {
    post("/addNote") {
        val request = call.receive<TravelNote>()
        insertTravelNote(request)
        call.response.status(HttpStatusCode.OK)
    }
}

fun Routing.addReview() {
    post("/addReview") {
        val request = call.receive<Review>()
        insertReview(request)
        call.response.status(HttpStatusCode.OK)
    }
}

fun Routing.addGallery() {
    post("/addGallery") {
        val request = call.receive<Gallery>()
        insertGallery(request)
        call.response.status(HttpStatusCode.OK)
    }
}

fun Routing.getTravelNotes() {
    get("/loadTravelNotes") {
        call.respond(selectTravelNotes())
    }
}

fun Routing.getReviewByTravelNoteId() {
    post("/getReviewByTravelNoteId") {
        val request = call.receive<TravelNoteParam>()
        call.respond(selectReviewByTravelNoteId(request.id))
    }
}

fun Routing.getGalleryByTravelNoteId() {
    post("/getGalleryByTravelNoteId") {
        val request = call.receive<TravelNoteParam>()
        call.respond(selectGalleryByTravelNoteId(request.id))
    }
}