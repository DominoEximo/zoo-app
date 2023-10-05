package hu.neuron.mentoring.zooapp.core.exception;

public class ZooEmployeeException extends ZooException {

    private static final long serialVersionUID = 6621702353288916889L;

    public ZooEmployeeException() {
        super();
    }

    public ZooEmployeeException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ZooEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZooEmployeeException(String message) {
        super(message);
    }

    public ZooEmployeeException(Throwable cause) {
        super(cause);
    }

}
