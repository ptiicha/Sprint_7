import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CourierSettings extends BaseURL {
    private static final String PATH = "api/v1/courier";

    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(PATH)
                .then()
                ;
    }

    public ValidatableResponse login(CourierCredentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH + "/login")
                .then()
                ;
    }

    public static ValidatableResponse delete(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);
        return given()
                .spec(getSpec())
                .body(json)
                .when()
                .delete(PATH + "/" + courierId)
                .then()
                ;
    }
}
