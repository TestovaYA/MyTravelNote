import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.dom.addClass
import kotlinx.dom.removeClass
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit

fun reloadPage() {
    window.location.reload()
}

fun openModalDialog(id: String) {
    val modalWindow = document.getElementById(id)!!

    if (!modalWindow.className.contains("show"))
        modalWindow.addClass("show")
    modalWindow.removeAttribute("style")
    modalWindow.setAttribute("style", "display: block; padding-top: 50px;")
    document.body!!.addClass("modal-open")

    val modalBackdrop = document.createElement("div")
    modalBackdrop.addClass("modal-backdrop fade show")
    modalBackdrop.id = "modalBackdrop"
    document.body!!.appendChild(modalBackdrop)
}

fun closeModal(id: String) {
    val modalWindow = document.getElementById(id)!!

    if (modalWindow.className.contains("show"))
        modalWindow.removeClass("show")

    modalWindow.removeAttribute("style")
    modalWindow.setAttribute("style", "display: none;")

    document.body!!.removeClass("modal-open")

    val modalBackdrop = document.body!!.lastChild
    if (modalBackdrop != null) {
        document.body!!.removeChild(modalBackdrop)
    }
}

fun addNewTravelNote(note: TravelNote) {
    window.fetch(
        "/addNote", RequestInit(
            method = "POST",
            body = Json.encodeToString(note),
            headers = Headers().also {
                it.set("Accept", "application/json")
                it.set("Content-Type", "application/json")
            }
        ))
}

fun addNewReview(review: Review) {
    window.fetch(
        "/addReview", RequestInit(
            method = "POST",
            body = Json.encodeToString(review),
            headers = Headers().also {
                it.set("Accept", "application/json")
                it.set("Content-Type", "application/json")
            }
        ))
}

fun addNewGallery(gallery: Gallery) {
    window.fetch(
        "/addGallery", RequestInit(
            method = "POST",
            body = Json.encodeToString(gallery),
            headers = Headers().also {
                it.set("Accept", "application/json")
                it.set("Content-Type", "application/json")
            }
        ))
}