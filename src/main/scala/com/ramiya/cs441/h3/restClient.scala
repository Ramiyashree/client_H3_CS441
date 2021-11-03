package com.ramiya.cs441.h3

import scalaj.http.{Http, HttpResponse}
import com.google.gson.Gson

//import com.twitter.finagle.{Http, Service, ServiceFactory}
//import com.twitter.finagle.http
//import com.twitter.util.{Await, Future}
//import com.twitter.io.Buf
object restClient  extends App {

  def sendRequest(time: String, delta: String): String = {
    val timeOBJ = Request(time, delta)
    val gson = new Gson()
    val reqOBJ = gson.toJson(timeOBJ)
    val temp = gson.fromJson(reqOBJ, classOf[Request])
    print(reqOBJ)
    val request = Http("https://t323213c5g.execute-api.us-east-2.amazonaws.com/prod/restapi")
      .timeout(2000, 10000).postData(reqOBJ)
    val response = request.asString.body
    print("responseREST" + response)
    return response
  }

  case class Request(time: String, delta: String)

 // def main(args: Array[String]) = {
    sendRequest("17:12:58.745", "00:00:01.000")

}

//
//object restClient extends App {
//
//  // Make request to external REST API
//  val host = "domc9f8oi7.execute-api.us-east-2.amazonaws.com"
//
//  val client: Service[http.Request, http.Response] = Http.client
//    .withTransport.tls(host)
//    .newService(s"$host:443")
//
//  val request: http.Request = http.Request("https://domc9f8oi7.execute-api.us-east-2.amazonaws.com/default/restBinarySeaarch")
//  request.setContentString("997897")
////  request.headerMap.set("Content-Type", "application/json")
////  request.headerMap.set("Accept", "application/json")
//
//  val response: Future[http.Response] = client(request)
//
//  Await.result(response.onSuccess { rep: http.Response => println("GET success: " + rep + " " + rep.contentString) })
//}
//


