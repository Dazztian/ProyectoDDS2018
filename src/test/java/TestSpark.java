import static spark.Spark.*;

public class TestSpark {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}