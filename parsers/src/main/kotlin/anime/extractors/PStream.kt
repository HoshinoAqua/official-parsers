package parsers.anime.extractors

import ani.saikou.FileUrl
import ani.saikou.Mapper
import ani.saikou.client
import ani.saikou.findBetween
import ani.saikou.parsers.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.util.*

class PStream(override val server: VideoServer) : VideoExtractor() {

    val link = "https://www.pstream.net/u/player-script"
    val regex = Regex("""\)\)}\("(.+)"\),n=.*file:.\.(.+),tracks:""")

    val headers = mapOf(
        "accept" to "*/*",
        "content-type" to "application/json",
        "accept-language" to "*/*"
    )

    override suspend fun extract(): VideoContainer {
        val res = client.get(server.embed.url, headers).text
        val jslink = res.findBetween("<script src=\"$link", "\" type=") ?: return VideoContainer(listOf())
        val (base64, key) = regex.find(client.get(link + jslink, headers).text)?.destructured!!
        val jsonText = Base64.getDecoder().decode(base64).decodeToString().substring(2)
        val json = Mapper.parse<JsonObject>(jsonText)
        val link = json[key]?.jsonPrimitive?.content ?: return VideoContainer(listOf())
        return VideoContainer(listOf(Video(null, VideoType.M3U8, FileUrl(link, headers))))
    }
}