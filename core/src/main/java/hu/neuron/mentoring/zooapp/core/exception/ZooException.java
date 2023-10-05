package hu.neuron.mentoring.zooapp.core.exception;

public class ZooException extends Exception {

    private static final long serialVersionUID = 2216912116508290146L;

    public ZooException() {
        super();
    }

    public ZooException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ZooException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZooException(String message) {
        super(message);
    }

    public ZooException(Throwable cause) {
        super(cause);
    }

}
