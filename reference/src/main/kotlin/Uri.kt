package ani.saikou

class Uri(string:String) {
    var host: String? = null
    var path: String? = null

    init {
        val reg = Regex("^((https?|ftp):/)?/?([^:/\\s]+)((/\\w+)*/)([\\w\\-.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?\$").find(string)?.destructured
        if(reg!=null) {
            val (url, protocol, host, path, file, query, hash) = reg
            this.host = host
            this.path = path
        }
    }
}