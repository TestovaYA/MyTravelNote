import Components.MainPage
import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.createElement("div")
    container.className = "container py-5"
    document.body!!.appendChild(container)

    val welcome = MainPage.create { }

    createRoot(container).render(welcome)
}
