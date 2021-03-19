package pl.lenistwo.statbot.exception;

public class PatchRequestException extends RuntimeException {
    public PatchRequestException(String url) {
        super(String.format("PATCH REQUEST EXCEPTION FOR %s", url));
    }
}
