package com.ramiya.cs441.h3

import scalaj.http.{Http, HttpResponse}
import com.google.gson.Gson
import com.ramiya.cs441.h3.Client.conf
import com.typesafe.config.{Config, ConfigFactory}

object restClient  extends App {

  val conf: Config = ConfigFactory.load("application.conf")

  val restEndpoint: String = conf.getString("clientConfig.restEndPoint")

  val inputTime : String = conf.getString("clientConfig.inputTime")
  val timeInterval : String = conf.getString("clientConfig.timeInterval")

  def sendRequest(time: String, delta: String): String = {

    val conf: Config = ConfigFactory.load("application.conf")
    val restEndpoint: String = conf.getString("clientConfig.restEndPoint")

    val timeOBJ = Request(time, delta)
    val gson = new Gson()
    val reqOBJ = gson.toJson(timeOBJ)
    val request = Http(restEndpoint).postData(reqOBJ)
    val response = request.asString.body
    print("Response Received" + response)
    response
  }

  case class Request(time: String, delta: String)

    sendRequest(inputTime, timeInterval)

}
