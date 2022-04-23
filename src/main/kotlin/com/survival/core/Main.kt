package com.survival.core

import com.survival.core.config.RegisterMessagesConfig
import net.minestom.server.extensions.Extension
import java.io.File

class Main : Extension() {

    override fun initialize() {
    }

    override fun terminate() {
    }

    private fun registerConfigs() {
        if(!File("configs").exists()) { File("configs").mkdir() }
        RegisterMessagesConfig
    }

}
