package Components

import react.FC
import react.Props
import react.create

external interface MainPageProps : Props

val MainPage = FC<MainPageProps> {
    val travelMapContainer = TravelMap.create { }
    val travelNoteContainer = TravelNoteCardsContainer.create { }
    val addTravelNoteButton = AddTravelNoteButton.create { }
    val travelNoteCreateForm = TravelNoteCreateForm.create { }

    child(travelMapContainer)
    child(travelNoteContainer)
    child(addTravelNoteButton)
    child(travelNoteCreateForm)
}
