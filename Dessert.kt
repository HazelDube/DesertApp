import tornadofx.*

class Desert : View("My View") {
    override val root = borderpane {
        fun main(args: Array<String>) {
            launch<MyApp>(args)
        }
    }
}
