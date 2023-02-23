package Components

import Gallery
import addNewGallery
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

external interface AddGalleryFormProps : Props{
    var travelNoteId : Int
}

val AddGalleryForm = FC<AddGalleryFormProps> {props->
    val (newPhotoLink, setPhotoLink) = useState("")

    div {
        className = ClassName("row modal fade")
        id = "addGalleryForm${props.travelNoteId}"
        div {
            className = ClassName("modal-dialog")
            div {
                className = ClassName("modal-content container card")
                div {
                    className = ClassName("card-header panel-heading")
                    h3 {
                        className = ClassName("panel-title text-center")
                        +"Новое фото"
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
                                +"Ссылка на фото"
                            }
                            div {
                                textarea {
                                    className = ClassName("form-control")
                                    rows = 7
                                    onChange = { event ->
                                        setPhotoLink(event.target.value)
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
                                val gallery = Gallery(
                                    id = null,
                                    photoLink = newPhotoLink,
                                    travelNoteId = props.travelNoteId
                                )
                                addNewGallery(gallery)
                                closeModal("addGalleryForm${props.travelNoteId}")
                                reloadPage()
                            }
                            +"Сохранить ссылку на фото"
                        }
                        button {
                            className = ClassName("btn btn-sm btn-outline-secondary")
                            type = ButtonType.button
                            onClick = {
                                closeModal("addGalleryForm${props.travelNoteId}")
                            }
                            +"Закрыть"
                        }
                    }
                }
            }
        }
    }
}