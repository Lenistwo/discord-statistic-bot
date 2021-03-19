package pl.lenistwo.statbot.exception;

public class GetRequestException extends RuntimeException {
    public GetRequestException(Object o, Exception e) {
        super(String.format("GET REQUEST ERROR FOR RESOURCE %s:\n  %s", o.getClass().getSimpleName(), e.getMessage()));
    }
}
