package Components

import closeModal
import csstype.ClassName
import openModalDialog
import react.FC
import react.Props
import react.create
import react.dom.aria.AriaRole
import react.dom.html.ButtonType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul

external interface ReviewPanelProps : Props {
    var travelNoteId: Int
    var reviewTexts: List<String>
}

val ReviewPanel = FC<ReviewPanelProps> { props ->
    div {
        className = ClassName("modal fade")
        id = "reviewModalForm${props.travelNoteId}"

        div {
            className = ClassName("modal-dialog")
            div {
                className = ClassName("modal-content")
                div {
                    className = ClassName("card-header")
                    +"Заметки"
                }

                div {
                    className = ClassName("modal-body card-body")

                    form {
                        className = ClassName("form-horizontal")
                        role = AriaRole.form
                        ul {
                            className = ClassName("list-group list-group-flush")

                            props.reviewTexts.forEach {
                                li {
                                    className = ClassName("list-group-item")
                                    +it
                                }
                            }
                        }
                    }
                }

                div {
                    className = ClassName("modal-footer")
                    div {
                        className = ClassName("btn-group")
                        button {
                            className = ClassName("btn btn-sm btn-outline-secondary")
                            type = ButtonType.button
                            onClick = {
                                closeModal("reviewModalForm${props.travelNoteId}")
                                openModalDialog("addReviewForm${props.travelNoteId}")
                            }
                            +"Добавить заметку"
                        }
                        button {
                            className = ClassName("btn btn-sm btn-outline-secondary")
                            type = ButtonType.button
                            onClick = {
                                closeModal("reviewModalForm${props.travelNoteId}")
                            }
                            +"Закрыть"
                        }
                    }
                }
            }
        }
    }

    val reviewForm = AddReviewForm.create{
        travelNoteId = props.travelNoteId
    }
    child(reviewForm)
}
