package com.example.sse.endpoints

import org.http4k.core.Method.GET
import org.http4k.datastar.DatastarEvent.MergeSignals
import org.http4k.datastar.Signal
import org.http4k.format.Moshi
import org.http4k.routing.RoutingSseHandler
import org.http4k.routing.sse
import org.http4k.routing.sse.bind
import java.io.File
import java.util.zip.ZipFile

fun BadApples(): RoutingSseHandler {

    val animation = loadAnimation(File("src/main/resources/bad-apples.zip"))

    return "/bad_apple/updates" bind sse(
        GET to sse {
            println("starting bad apple")
            var currentFrameIdx = 0

            while (currentFrameIdx < animation.frames.size) {
                val frame = animation.frames[currentFrameIdx]
                val percentage = 100.0 * (currentFrameIdx + 1) / animation.frames.size
                val store = BadAppleStore(frame, percentage)

                it.send(MergeSignals(Signal.of(Moshi.asFormatString(store))).toSseEvent())

                currentFrameIdx += 1
                Thread.sleep(33)
            }
            it.close()
        }
    )
}

private fun loadAnimation(zipFile: File): AsciiAnimation {
    val frames = mutableListOf<String>()

    ZipFile(zipFile).use {
        it.entries().asSequence()
            .sortedBy { it.name }
            .forEach { entry ->
                it.getInputStream(entry).use { inputStream ->
                    frames.add(inputStream.bufferedReader().use { it.readText() })
                }
            }
    }

    return AsciiAnimation(frames)
}

data class BadAppleStore(val _contents: String, val percentage: Double)

class AsciiAnimation(val frames: List<String>)
