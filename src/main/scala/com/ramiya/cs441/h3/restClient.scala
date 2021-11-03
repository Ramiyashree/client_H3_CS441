package com.ramiya.cs441.h3

import scalaj.http.{Http, HttpResponse}
import com.google.gson.Gson

object restClient  extends App {

  def sendRequest(time: String, delta: String): String = {

    val timeOBJ = Request(time, delta)
    val gson = new Gson()
    val reqOBJ = gson.toJson(timeOBJ)
    val request = Http("https://t323213c5g.execute-api.us-east-2.amazonaws.com/prod/restapi").postData(reqOBJ)
    val response = request.asString.body
    print("Response Received" + response)
    response
  }

  case class Request(time: String, delta: String)

    sendRequest("17:12:58.745", "00:00:01.000")

}
