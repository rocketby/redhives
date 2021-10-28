package api.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static api.config.ApiCredentials.apiCredentials;

public class Specs {
    public static String consumerKey = apiCredentials.consumerKey();
    public static String consumerSecret = apiCredentials.consumerSecret();
    public static String accessToken = apiCredentials.accessToken();
    public static String secretToken = apiCredentials.secretToken();


    public static RequestSpecification requestSpec = with()
            .auth()
            .oauth(consumerKey, consumerSecret, accessToken, secretToken)
            .baseUri("https://api.twitter.com")
//            .basePath("/2/users")
//            .log().uri()
            .contentType(ContentType.JSON);


    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
