import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: Int?,
    val reviewText: String?,
    val travelNoteId: Int?,
)
