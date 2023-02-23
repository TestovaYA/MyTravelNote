package Components

import Review
import addNewReview
import closeModal
import csstype.ClassName
import react.FC
import react.Props
import react.dom.aria.AriaRole
import react.dom.html.ButtonType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.textarea
import react.useState
import reloadPage

external interface AddReviewFormProps : Props{
    var travelNoteId : Int
}

val AddReviewForm = FC<AddReviewFormProps> {props->
    val (newReviewText, setReviewText) = useState("")

    div {
        className = ClassName("row modal fade")
        id = "addReviewForm${props.travelNoteId}"
        div {
            className = ClassName("modal-dialog")
            div {
                className = ClassName("modal-content container card")
                div {
                    className = ClassName("card-header panel-heading")
                    h3 {
                        className = ClassName("panel-title text-center")
                        +"Новая заметка"
                    }
                }
                div {
                    className = ClassName("modal-body card-body panel-body")
                    form {
                        className = ClassName("form-horizontal")
                        role = AriaRole.form

                        div {
                            className = ClassName("form-group")
                            label {
                                className = ClassName("control-label")
                                +"Заметка"
                            }
                            div {
                                textarea {
                                    className = ClassName("form-control")
                                    rows = 7
                                    onChange = { event ->
                                        setReviewText(event.target.value)
                                    }
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
                                val review = Review(
                                    id = null,
                                    reviewText = newReviewText,
                                    travelNoteId = props.travelNoteId
                                )
                                addNewReview(review)
                                closeModal("addReviewForm${props.travelNoteId}")
                                reloadPage()
                            }
                            +"Сохранить заметку"
                        }
                        button {
                            className = ClassName("btn btn-sm btn-outline-secondary")
                            type = ButtonType.button
                            onClick = {
                                closeModal("addReviewForm${props.travelNoteId}")
                            }
                            +"Закрыть"
                        }
                    }
                }
            }
        }
    }
}