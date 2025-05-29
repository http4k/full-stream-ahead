//package ws
//
//import org.http4k.core.Request
//import org.http4k.websocket.WsMessage
//import org.http4k.websocket.WsResponse
//import org.http4k.websocket.WsStatus
//
//typealias WsHandler = (Request) -> WsConsumer
//
//typealias WsConsumer = (Websocket) -> Unit
//
//interface Websocket {
//    fun send(message: WsMessage)
//    fun close(status: WsStatus)
//    fun onError(fn: (Throwable) -> Unit)
//    fun onClose(fn: (WsStatus) -> Unit)
//    fun onMessage(fn: (WsMessage) -> Unit)
//}
//
//
//
//val websocket = WsResponse { ws: Websocket ->
//    ws.onMessage {
//    }
//    ws.close(
//
//    )
//    ws.send(WsMessage(""))
//    ws.onClose {  }
//    ws.onError {  }
//    Unit
//}
//
//
//
//
//val a: WsHandler get() = WsResponse {
//    ws
//}
//val b: WsConsumer get() = TODO()
//


