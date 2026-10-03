package transfer;

import java.io.Serializable;
import transfer.util.Operation;

/**
 *
 * @author Kosta
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private Operation operation;
    private Object data;

    public Request() {
    }

    public Request(Operation operation, Object data) {
        this.operation = operation;
        this.data = data;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
