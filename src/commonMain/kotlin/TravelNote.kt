import kotlinx.serialization.Serializable

@Serializable
data class TravelNote(
    val id: Int?,
    val title: String?,
    val city: String?,
    val imgUrl: String?,
    val coordinate1: String?,
    val coordinate2: String?,
    val noteText: String?
)


@Serializable
data class TravelNoteParam(
    val id: Int
)