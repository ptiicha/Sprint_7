public class OrderGenerator {
    public static Order getDefault() {
        return new Order("Анастасия",
                "Умница",
                "ул. Шантичева",
                "Красные ворота",
                "+7 123 456 78 91",
                10,
                "2023-02-16",
                "Тесты тестовые тестятся туда сюда"
        );
    }
}
