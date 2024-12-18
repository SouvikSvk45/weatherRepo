package com.example.weather_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


@Configuration
public class DynamoDBConfig {
	
	// For locally testing 
//    @Value("${aws.access.key}")
//    private String awsAccessKey;
//
//    @Value("${aws.access.secret-key}")
//    private String awsSecretKey;

    @Value("${aws.dynamodb.endpoint}")
    private String awsDynamoDBEndPoint;

    @Value("${aws.region}")
    private String awsRegion;

 // For locally testing 
//    @Bean
//    public AWSCredentials amazonAWSCredentials(){
//        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
//    }

 // For locally testing 
//    @Bean
//    public AWSCredentialsProvider amazonAWSCredentialsProvider(){
//        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
//    }

    //For locally testing
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB(){
//        return AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
//                .withCredentials(amazonAWSCredentialsProvider())
//                .build();
//    }
    
   
    //For ci/cd testing

    @Bean
  public AmazonDynamoDB amazonDynamoDB(){
      return AmazonDynamoDBClientBuilder.standard()
              .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
              .build();
  }
   
//---------------------------------    
    @Bean
    public DynamoDBMapper mapper(){
        return new DynamoDBMapper(amazonDynamoDB());
    }


}
