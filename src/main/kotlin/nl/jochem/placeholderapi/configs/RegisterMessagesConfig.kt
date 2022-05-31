package nl.jochem.placeholderapi.configs

import com.google.gson.GsonBuilder
import java.io.File

private const val fileName = "extensions/placeholderapi/messages.json"

object RegisterMessagesConfig {
    init {
        if(!File(fileName).exists()) {
            File(fileName).createNewFile()
            File(fileName).writeText(
                GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(MessagesConfig(
                        prefix = "&b[&b&lPapi&b] ",
                        usage = "&c/papi \n list \n download <name> \n remove <name>",
                        placeholder_exists = "&cSorry, but this placeholder already exists.",
                        download_placeholder = "&aYou have downloaded the placeholder.",
                        remove_placeholder = "&aYou have removed the placeholder.",
                        invalid_name = "cEnter a valid placeholder name."
                    ))
            )
        }
    }

}

val messagesConfig = GsonBuilder()
    .setPrettyPrinting()
    .create()!!.fromJson(File(fileName).readText(), MessagesConfig::class.java)!!

data class MessagesConfig(
    //prefix
    val prefix : String,
    //command messages
    val usage : String,
    val placeholder_exists : String,
    val download_placeholder : String,
    val remove_placeholder : String,
    val invalid_name : String,
)