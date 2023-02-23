package Components

import csstype.px
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface TravelMapProps : Props

val TravelMap = FC<TravelMapProps> {
    div{
        id = "map"
        css {
            width = 1300.px
            height = 650.px
        }
    }
}
