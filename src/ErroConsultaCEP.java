public class ErroConsultaCEP extends RuntimeException {
    private String message;

    public ErroConsultaCEP(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
