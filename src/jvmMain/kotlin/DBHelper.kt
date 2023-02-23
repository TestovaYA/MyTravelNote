
import org.ktorm.dsl.*
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object TravelNoteObject : Table<Nothing>("travelnote") {
    val id = int("id").primaryKey()
    val title = varchar("title")
    val city = varchar("city")
    val imgUrl = varchar("imgurl")
    val coordinate1 = varchar("coordinate1")
    val coordinate2 = varchar("coordinate2")
    val noteText = varchar("notetext")
}

object GalleryObject : Table<Nothing>("gallery") {
    val id = int("id").primaryKey()
    val photoLink = varchar("photolink")
    val travelNoteId = int("travelnoteid")
}

object ReviewObject : Table<Nothing>("review") {
    val id = int("id").primaryKey()
    val reviewText = varchar("reviewtext")
    val travelNoteId = int("travelnoteid")
}

fun selectTravelNotes(): MutableList<TravelNote> {
    val travelNotes = mutableListOf<TravelNote>()
    for (row in database.from(TravelNoteObject).select()) {
        val result = TravelNote(
            id = row[TravelNoteObject.id]!!,
            title = row[TravelNoteObject.title],
            city = row[TravelNoteObject.city],
            imgUrl = row[TravelNoteObject.imgUrl],
            coordinate1 = row[TravelNoteObject.coordinate1],
            coordinate2 = row[TravelNoteObject.coordinate2],
            noteText = row[TravelNoteObject.noteText]
        )
        travelNotes.add(result)
    }
    return travelNotes
}

fun selectGalleryByTravelNoteId(travelNoteId: Int): MutableList<Gallery> {
    val photos = mutableListOf<Gallery>()
    for (row in database.from(GalleryObject).select().where(GalleryObject.travelNoteId eq travelNoteId)) {
        val result = Gallery(
            id = row[GalleryObject.id]!!,
            photoLink = row[GalleryObject.photoLink]!!,
            travelNoteId = row[GalleryObject.travelNoteId]!!
        )
        photos.add(result)
    }
    return photos
}

fun selectReviewByTravelNoteId(travelNoteId: Int): MutableList<Review> {
    val reviews = mutableListOf<Review>()
    for (row in database.from(ReviewObject).select().where(ReviewObject.travelNoteId eq travelNoteId)) {
        val result = Review(
            id = row[ReviewObject.id]!!,
            reviewText = row[ReviewObject.reviewText]!!,
            travelNoteId = row[ReviewObject.travelNoteId]!!
        )
        reviews.add(result)
    }
    return reviews
}

fun insertTravelNote(requestTravelNote: TravelNote) {
    val id = database.insertAndGenerateKey(TravelNoteObject) {
        set(it.title, requestTravelNote.title)
        set(it.city, requestTravelNote.city)
        set(it.imgUrl, requestTravelNote.imgUrl)
        set(it.coordinate1, requestTravelNote.coordinate1)
        set(it.coordinate2, requestTravelNote.coordinate2)
        set(it.noteText, requestTravelNote.noteText)
    }
}

fun insertReview(requestReview: Review) {
    val id = database.insertAndGenerateKey(ReviewObject) {
        set(it.reviewText, requestReview.reviewText)
        set(it.travelNoteId, requestReview.travelNoteId)
    }
}

fun insertGallery(requestGallery: Gallery) {
    val id = database.insertAndGenerateKey(GalleryObject) {
        set(it.photoLink, requestGallery.photoLink)
        set(it.travelNoteId, requestGallery.travelNoteId)
    }
}