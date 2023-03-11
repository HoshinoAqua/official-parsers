import ani.saikou.initializeNetwork
import ani.saikou.printIt
import ani.saikou.tryWithSuspend
import anime.AllAnime
import kotlinx.coroutines.runBlocking

class Test {
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            initializeNetwork(0)
            val all = AllAnime()
            runBlocking {
                val show = all.search("one piece")

                val ep = all.loadEpisodes(show[1].link,null)[1]
                val videos = all.loadVideoServers(ep.link,null).mapNotNull {
                    tryWithSuspend {
                        it.printIt("server : ")
                        all.getVideoExtractor(it)?.extract()?.videos
                    }
                }.flatten()
                videos.forEach {
                    println(it)
                }
            }
        }
    }
}