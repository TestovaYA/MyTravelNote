package Components

import TravelNote
import addNewTravelNote
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
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.textarea
import react.useState
import reloadPage

external interface TravelNoteCreateFormProps : Props

val TravelNoteCreateForm = FC<TravelNoteCreateFormProps> {
    val (newTravelNoteTitle, setTitle) = useState("")
    val (newTravelNoteCity, setCity) = useState("")
    val (newTravelNoteImgUrl, setImgUrl) = useState("")
    val (newTravelNoteCoordinate1, setCoord1) = useState("")
    val (newTravelNoteCoordinate2, setCoord2) = useState("")
    val (newTravelNoteNoteText, setNoteText) = useState("")

    div {
        className = ClassName("row modal fade")
        id = "modalForm"
        div {
            className = ClassName("modal-dialog")
            div {
                className = ClassName("modal-content container card")
                div {
                    className = ClassName("card-header panel-heading")
                    h3 {
                        className = ClassName("panel-title text-center")
                        +"Новое место"
                    }
                }
                div {
                    className = ClassName("modal-body card-body panel-body")
                    form {
                        className = ClassName("form-horizontal")
                        role = AriaRole.form

                        //NoteTitle
                        div {
                            className = ClassName("panel-body")
                            label {
                                className = ClassName("control-label")
                                +"Название"
                            }
                            div {
                                input {
                                    className = ClassName("form-control")
                                    onChange = { event ->
                                        setTitle(event.target.value)
                                    }
                                }
                            }
                        }
                        //City
                        div {
                            className = ClassName("panel-body")
                            label {
                                className = ClassName("control-label")
                                +"Город"
                            }
                            div {
                                input {
                                    className = ClassName("form-control")
                                    onChange = { event ->
                                        setCity(event.target.value)
                                    }
                                }
                            }
                        }
                        //ImgUrl
                        div {
                            className = ClassName("panel-body")
                            label {
                                className = ClassName("control-label")
                                +"URL фото"
                            }
                            div {
                                input {
                                    className = ClassName("form-control")
                                    onChange = { event ->
                                        setImgUrl(event.target.value)
                                    }
                                }
                            }
                        }

                        //Coordinates
                        className = ClassName("form-row")
                        div {
                            className = ClassName("panel-body")
                            label {
                                className = ClassName("control-label")
                                +"Координата 1"
                            }
                            div {
                                input {
                                    className = ClassName("form-control")
                                    onChange = { event ->
                                        setCoord1(event.target.value)
                                    }
                                }
                            }
                        }
                        div {
                            className = ClassName("panel-body")
                            label {
                                className = ClassName("control-label")
                                +"Координата 2"
                            }
                            div {
                                input {
                                    className = ClassName("form-control")
                                    onChange = { event ->
                                        setCoord2(event.target.value)
                                    }
                                }
                            }
                        }


                        //NoteText
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
                                        setNoteText(event.target.value)
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
                                val note = TravelNote(
                                    id = null,
                                    title = newTravelNoteTitle,
                                    city = newTravelNoteCity,
                                    imgUrl = newTravelNoteImgUrl,
                                    coordinate1 = newTravelNoteCoordinate1,
                                    coordinate2 = newTravelNoteCoordinate2,
                                    noteText = newTravelNoteNoteText
                                )
                                addNewTravelNote(note)
                                closeModal("modalForm")
                                reloadPage()
                            }
                            +"Сохранить новое место"
                        }
                        button {
                            className = ClassName("btn btn-sm btn-outline-secondary")
                            type = ButtonType.button
                            onClick = {
                                closeModal("modalForm")
                            }
                            +"Закрыть"
                        }
                    }
                }
            }
        }
    }
}
