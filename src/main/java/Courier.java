public class Courier {
    private String login;
    private String password;
    private String lastName;

    public Courier() {
    }


    public Courier(String login, String password, String lastName) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public <T, R> io.restassured.response.ValidatableResponseOptions<T,R> login(CourierLogin from) {
    }
}

