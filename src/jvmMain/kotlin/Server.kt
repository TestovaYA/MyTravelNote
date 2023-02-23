import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import kotlinx.html.*
import org.example.application.*
import org.ktorm.database.Database
import kotlin.collections.set

val database = Database.connect(
    url = "jdbc:mysql://localhost:3306/ktorm?useSSL=false",
    driver = "com.mysql.cj.jdbc.Driver",
    user = "root",
    password = "admin"
)

fun HTML.index() {
    head {
        link {
            href = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel = "stylesheet"
            attributes["integrity"] = "sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            attributes["crossorigin"] = "anonymous"
        }
        title { +"MyTravelNote" }
        script {
            src = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity = "sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            attributes["crossorigin"] = "anonymous"
        }
        script { src = "https://maps.api.2gis.ru/2.0/loader.js?pkg=full" }
        script {
            type = "text/javascript"
            +"""var map;

                DG.then(function () {
                    map = DG.map('map', {	
                        center: [43.406, 39.954],
                        zoom: 14
                    });
    
                DG.marker([43.40620425458882, 39.95405704681227])
                .addTo(map);
                
                DG.marker([43.41754725226069, 39.9470535937382])
                .addTo(map);
                
                DG.marker([43.39866466554485, 39.96255465346493])
                .addTo(map);
                
            });"""
        }
    }
    body {
        script(src = "/static/MyTravelNote.js") {}

    }
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::myApplicationModule).start(wait = true)
}

fun Application.myApplicationModule() {
    install(CallLogging)
    install(Resources)
    install(ContentNegotiation) {
        jackson()
    }

    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK, HTML::index)
        }

        addNote()
        addReview()
        addGallery()

        getTravelNotes()
        getReviewByTravelNoteId()
        getGalleryByTravelNoteId()

        static("/static/") {
            resources()
        }
    }
}
