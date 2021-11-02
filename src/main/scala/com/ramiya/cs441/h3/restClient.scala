package com.ramiya.cs441.h3

import com.twitter.finagle.{Http, Service, ServiceFactory}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}
import com.twitter.io.Buf

object restClient extends App {

  // Make request to external REST API
  val host = "t332cmw1ad.execute-api.us-east-2.amazonaws.com"

//  val client: Service[http.Request, http.Response] = Http.newService
//    .withTransport.tls(host)
//    .newService(s"$host:443")

  val client: Service[http.Request, http.Response] = Http.client
    .withTransport.tls(host)
    .newService(s"$host:443")

  val request: http.Request = http.Request("https://t332cmw1ad.execute-api.us-east-2.amazonaws.com/default/restTest")
//  request.host = host
//  request.headerMap.set("Content-Type", "application/json")
//  request.headerMap.set("Accept", "application/json")

  val response: Future[http.Response] = client(request)

  Await.result(response.onSuccess { rep: http.Response => println("GET success: " + rep + " " + rep.contentString) })
}