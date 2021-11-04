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

class gRPCClient(private val url: String)  {

  def TimeFunction(timeData: TimeData): Boolean = {
    val request = Http(url)
      .headers(Map(
        "Content-Type" -> "application/grpc+proto",
        "Accept" -> "application/grpc+proto"
      )).postData(timeData.toByteArray)


    val response = request.asBytes
    val responseMessage = TimeResponse.parseFrom(response.body)
    println(s"Got response: $responseMessage")

    println("Client Response" + response)

    responseMessage.result
  }
}
