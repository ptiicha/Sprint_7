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

public class testOrder {

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @org.junit.Test
    public void createdOrder() {
        String json = "{\"firstName\": \"Анастасия\", \"lastName\": \"Пукина\", \"address\": \"ул. Жукова, д. 5\", \"metroStation\": \"Красные ворота\", \"phone\": \"+7 987 654 32 10\", \"rentTime\": \"10\", \"deliveryDate\": \"2023-02-10\", \"comment\": \"Хочу катаца!\",}";
        io.restassured.response.Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/orders");
        response.assertThat().body("track").and().statusCode(200);
    }

    @org.junit.Test
    public void cancelOrder() {
        String json = "{\"track\": \"123456\"}";
        io.restassured.response.Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .put("/api/v1/orders/cancel");
        response.assertThat().body("ok").and().statusCode(200);
    }
}