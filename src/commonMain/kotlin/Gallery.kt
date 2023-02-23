import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    val id: Int?,
    val photoLink: String?,
    val travelNoteId: Int?
)
