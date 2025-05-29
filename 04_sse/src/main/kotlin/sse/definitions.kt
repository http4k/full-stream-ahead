//package sse
//
//import org.http4k.core.Request
//import org.http4k.sse.SseMessage
//import org.http4k.sse.SseResponse
//
//typealias SseHandler = (Request) -> SseResponse
//
//typealias SseConsumer = (Sse) -> Unit
//
//interface Sse {
//    fun send(message: SseMessage)
//    fun close()
//    fun onClose(fn: () -> Unit)
//}
//
//
//
//val websocket = SseResponse { ws: Sse ->
//    ws.send(SseMessage.parse(""))
//    ws.close(
//ws.onClose {  }
//    )
//}
//
//val a: SseHandler get() = SseResponse {
//}
//
//
//val b: SseConsumer get() = SseResponse {
//}
//
//
//
//
//
