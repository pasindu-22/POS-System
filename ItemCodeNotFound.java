public class ItemCodeNotFound extends RuntimeException {
    public ItemCodeNotFound() {
    }

    public ItemCodeNotFound(String message) {
        super(message);
    }
}

    class CustomerNotFound extends RuntimeException {
        public CustomerNotFound() {
        }

        public CustomerNotFound(String message) {
            super(message);
        }
    }

