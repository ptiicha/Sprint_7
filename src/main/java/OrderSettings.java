import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderSettings extends BaseURL{
    private static final String PATH = "api/v1/orders";

    public ValidatableResponse create(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(PATH)
                .then();
    }

    public ValidatableResponse cancel(int orderId) {
        String json = String.format("{\"track\": \"%d\"}", orderId);
        return given()
                .spec(getSpec())
                .body(json)
                .when()
                .put(PATH + "/cancel")
                .then();
    }

    public ValidatableResponse returnListOfOrders() {
        return given()
                .spec(getSpec())
                .when()
                .get(PATH)
                .then();
    }

}
