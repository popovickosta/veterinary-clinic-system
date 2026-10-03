package so;

import db.DBBroker;
import domain.AbstractDomainObject;

/**
 *
 * @author Kosta
 */
public abstract class AbstractSO {

    private static final Object TRANSACTION_LOCK = new Object();

    protected abstract void validate(AbstractDomainObject ado) throws Exception;
    protected abstract void execute(AbstractDomainObject ado) throws Exception;

    public void templateExecute(AbstractDomainObject ado) throws Exception {
        synchronized (TRANSACTION_LOCK) {
            try {
                validate(ado);
                execute(ado);
                commit();
            } catch (Exception e) {
                try {
                    rollback();
                } catch (Exception ignored) {
                }
                throw e;
            }
        }
    }

    public void commit() throws Exception {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws Exception {
        DBBroker.getInstance().getConnection().rollback();
    }
}
