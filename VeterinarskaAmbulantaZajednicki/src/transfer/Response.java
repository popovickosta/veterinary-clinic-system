package transfer;

import java.io.Serializable;
import transfer.util.ResponseStatus;

/**
 *
 * @author Kosta
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object data;
    private Exception exception;
    private ResponseStatus status;

    public Response() {
        this(null, null, ResponseStatus.SUCCESS);
    }

    public Response(Object data, Exception exception, ResponseStatus status) {
        this.data = data;
        this.exception = exception;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
