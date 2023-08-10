package hu.neuron.mentoring.zooapp.service;

public class GondoZooNotAvailableException extends ZooException {

    private static final long serialVersionUID = 3169852662848012575L;

    public GondoZooNotAvailableException() {
        super();
    }

    public GondoZooNotAvailableException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GondoZooNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public GondoZooNotAvailableException(String message) {
        super(message);
    }

    public GondoZooNotAvailableException(Throwable cause) {
        super(cause);
    }

}
