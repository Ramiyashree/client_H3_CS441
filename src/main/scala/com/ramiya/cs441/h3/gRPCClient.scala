package com.ramiya.cs441.h3

import com.typesafe.scalalogging.LazyLogging
import h3.{TimeData, TimeResponse};
import scalaj.http.Http
//import com.amazonaws.AmazonServiceE
//
import com.amazonaws.AmazonServiceException
import com.amazonaws.SdkClientException
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.ResponseHeaderOverrides
import com.amazonaws.services.s3.model.S3Object

class gRPCClient(private var url: String)  {

  def TimeFunction( timeData: TimeData): Boolean = {
    //logger.trace(s"calculate(expression: $expression")
    print("url" + url)
    // Make POST request to calculator API Gateway
    val request = Http(url)
      .headers(Map(
        "Content-Type" -> "application/grpc+proto",
        "Accept" -> "application/grpc+proto"
      ))
      .timeout(connTimeoutMs = 2000, readTimeoutMs = 10000) // So that request doesn't time out for Lambda cold starts
      .postData(timeData.toByteArray)
    println(timeData.toByteArray)

    // logger.debug(s"Making HTTP request: $request")
    val response = request.asBytes
    val responseMessage = TimeResponse.parseFrom(response.body)
    println(s"Got response: $responseMessage")

    println("response" + response)
    //
    //
    //     //Parse response from API to protobuf Response object
    //    val responseMessage = Response.parseFrom(response.body)sb
    //   // logger.debug(s"Response message: $responseMessage")
    //
    //     println(responseMessage)
    //
    //
    //     //  Return the result
    //    responseMessage.result
    true
  }
}
