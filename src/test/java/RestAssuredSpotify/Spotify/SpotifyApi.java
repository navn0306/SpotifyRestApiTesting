package RestAssuredSpotify.Spotify;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SpotifyApi {

	String token = "Bearer BQAzQkX0PBzu00E4yt1ozgrxRYXyoeLcElirmUdhBXYoxRecqidvc2hO9nKyi5TjCg_WGYXxVUBH0vZKLFMe1f7-3YjqpgtSuQO8i8RB7WRL_sKjZX1Ge5-8eoQpNDdKUE3Y6DsqnVAKY5dxSHr51bqhh8BnitkqoUtM4V3z8qOoLEDpbb6e8qixKXajlIZ1mdclBfxNdXVc071TR9_VrnzxV90Zvd0KrlVZnkm9RN6gakSO5Ti4F8u-xbQU-iYfHqiBNBMDy--cSWec7LLw-cqXgAbP9dcufhpm_toe";
	String userId;

	@Test
	public void testGet_CurrentUserProfile_ShouldReturn_StatusCode200() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		System.out.println("Status code is :" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testGet_CurrentUserProfileId_ShouldReturnStatusCode200() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).get("https://api.spotify.com/v1/users/me");
		userId = response.then().extract().path("id");
		System.out.println("user Id : " + userId);
		response.prettyPrint();
		response.then().statusCode(200);

	}
	
	@Test
	public void testPost_When_CreatePlaylist_ShouldReturnStatusCode201() {
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", "Rest Playlist");
		requestParam.put("description", "Playlist made by rest assured");
		requestParam.put("public", "false");
		httpRequest.header("Authorization",token);
		httpRequest.body(requestParam.toString());
		Response response = httpRequest.request(Method.POST,"https://api.spotify.com/v1/users/jafnod40d7mx3wfao2vua6iu9/playlists");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is :" +responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		Assert.assertEquals(statusCode, 201);
	}
}
