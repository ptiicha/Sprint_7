import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.RestAssured.*;
import org.junit.Assert.*;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testCourier {

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

@org.junit.Test
    public void courierCreated() {
    String json = "{\"login\": \"login\", \"password\": \"123qwerty\", \"lastName\": \"Иванов\"}";
    io.restassured.response.Response response =
            given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(json)
                    .when()
                    .post("/api/v1/courier");
    response.then().assertThat().statusCode(201);
}
    @org.junit.Test
    public void creationSameCourierFailed () {
        String json = "{\"login\": \"login\", \"password\": \"123qwerty\", \"lastName\": \"Иванов\"}";
        io.restassured.response.Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().statusCode(409);
    }

    @org.junit.Test
    public void loggedIn () {
        String json = "{\"login\": \"login\", \"password\": \"123qwerty\"}";
        io.restassured.response.Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().statusCode(200);
    }

    @org.junit.Test
    public void notLoggedRequiredFields () {
        String json = "{\"login\": \" \", \"password\": \"123qwerty\"}";
        io.restassured.response.Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().statusCode(400);
    }

    @org.junit.Test
    public void notLoggedInvalidField () {
        String json = "{\"login\": \"wrong login\", \"password\": \"123qwerty\"}";
        io.restassured.response.Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().statusCode(404);
    }
}
