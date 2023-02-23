package Components

import closeModal
import csstype.ClassName
import openModalDialog
import react.FC
import react.Props
import react.create
import react.dom.html.ButtonType
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img

external interface GalleryPanelProps : Props {
    var travelNoteId: Int
    var photoLinks: List<String>
}

val GalleryPanel = FC<GalleryPanelProps> { props ->
    div {
        className = ClassName("modal fade")
        id = "galleryModalForm${props.travelNoteId}"
        div {
            div {
                className = ClassName("modal-content container")
                div {
                    className = ClassName("card-header")
                    +"Фото"
                }

                div {
                    className = ClassName("container")
                    div {
                        className = ClassName("row")

                        props.photoLinks.forEach {
                            div {
                                className = ClassName("col-lg-4")
                                a {
                                    className = ClassName("img-thumbnail")
                                    href = it
                                    img {
                                        src = it
                                        width = 400.0
                                        height = 250.0
                                        alt = "Photo"
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
                                closeModal("galleryModalForm${props.travelNoteId}")
                                openModalDialog("addGalleryForm${props.travelNoteId}")
                            }
                            +"Добавить фото"
                        }
                        button {
                            className = ClassName("btn btn-sm btn-outline-secondary")
                            type = ButtonType.button
                            onClick = {
                                closeModal("galleryModalForm${props.travelNoteId}")
                            }
                            +"Закрыть"
                        }
                    }
                }
            }
        }
    }

    val galleryForm = AddGalleryForm.create{
        travelNoteId = props.travelNoteId
    }
    child(galleryForm)
}

