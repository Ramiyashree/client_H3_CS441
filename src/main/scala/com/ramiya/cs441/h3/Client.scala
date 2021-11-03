package com.ramiya.cs441.h3

import com.typesafe.config.{Config, ConfigFactory}
import h3.{TimeData, TimeResponse}

import scala.io.StdIn

/**
 * Main Calculator client program that takes inputs from the user and evaluates the result by invoking AWS Lambda
 * functions.
 *
 * The program accepts the below two arguments in that order:
 * (0) api-type:         Either "grpc" or "rest" to specify which type of API to use for making gRPC calls.
 * (1) api-gateway-url:  The URL of the API Gateway that invokes the lambda function for the specified `api-type`.
 *
 * If these arguments are not specified, default values will be picked up from the typesafe config files.
 */
object Client extends App {


  val conf: Config = ConfigFactory.load("application.conf")

  val grpcEndpoint: String = conf.getString("clientConfig.grpcEndPoint")
  val restEndpoint: String = conf.getString("clientConfig.restEndPoint")

  val inputTime : String = conf.getString("clientConfig.inputTime")

  // Initialize API-type from the specified arguments or from the settings
//  val apiType =
//    if (args.nonEmpty) {
//      if (Seq("grpc", "rest").contains(args(0).toLowerCase)) {
//        args(0)
//      }
//      else {
//        println("Invalid API type. Must be one of ('grpc','rest')")
//        sys.exit(-1)
//      }
//    }
//    else {
//      settings.defaultAPIType
//    }
//
//  // Initialize API Gateway URL from the specified arguments or from the settings
//  val url =
//    if (args.length > 1)
//      args(1)
//    else if (apiType.equalsIgnoreCase("grpc"))
//      settings.apiGatewayUrlGrpc
//    else
//      settings.apiGatewayUrlRest
//
//  println("url" + url)

  // Instantiate CalculatorClient based on the apiType
  val client =  new gRPCClient(grpcEndpoint)

  val result = client.TimeFunction(
    TimeData(time = inputTime)
    )

  print(s"Time is Present: ${result}")
}
