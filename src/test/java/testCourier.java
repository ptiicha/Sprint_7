import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.RestAssured.*;
import io.restassured.response.ValidatableResponse;
import Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testCourier {

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

@Test
Courier courier = new Courier ("login", "123qwerty", "Иванов");
    public void courierCreated() {
        given()
                .get("/api/users/me")
                .then().assertThat().body("data.name", equalTo("Тестировщик"));

}
