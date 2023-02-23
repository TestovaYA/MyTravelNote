package Components
import csstype.ClassName
import openModalDialog
import react.FC
import react.Props
import react.dom.html.ButtonType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div

external interface AddTravelNoteButtonProps : Props

val AddTravelNoteButton = FC<AddTravelNoteButtonProps> {
    div {
        className = ClassName("row")
        button {
            className = ClassName("btn btn-secondary")
            id = "addTravelNoteButton"
            type = ButtonType.button
            onClick = {
                openModalDialog("modalForm")
            }
            +"Добавить новое место"
        }
    }
}








