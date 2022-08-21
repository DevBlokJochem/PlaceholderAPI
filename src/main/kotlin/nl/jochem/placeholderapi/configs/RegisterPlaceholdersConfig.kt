package nl.jochem.placeholderapi.configs

import com.google.gson.GsonBuilder
import java.io.File

private const val fileName = "extensions/placeholderapi/active_placeholders.json"

object RegisterPlaceholdersConfig {
    init {
        if(!File(fileName).exists()) {
            File(fileName).createNewFile()
            File(fileName).writeText(
                GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(PlaceholdersConfig(
                        placeholders = ArrayList()
                    ))
            )
        }
    }

}

private fun PlaceholdersConfig.update() {
    File(fileName).writeText(
        GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(this))
}

fun PlaceholdersConfig.addPlaceholder(name : String) {
    if(placeholders.contains(name)) { return }
    placeholders.add(name)
    update()
}

fun PlaceholdersConfig.removePlaceholder(name : String) {
    if(!placeholders.contains(name)) { return }
    placeholders.remove(name)
    update()
}

var placeholdersConfig = GsonBuilder()
    .setPrettyPrinting()
    .create()!!.fromJson(File(fileName).readText(), PlaceholdersConfig::class.java)!!

data class PlaceholdersConfig(
    //prefix
    val placeholders : ArrayList<String>
)