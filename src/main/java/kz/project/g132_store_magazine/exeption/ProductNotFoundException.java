package kz.project.g132_store_magazine.exeption;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
