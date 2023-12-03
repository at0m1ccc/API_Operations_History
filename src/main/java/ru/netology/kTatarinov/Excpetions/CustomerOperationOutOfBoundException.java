package ru.netology.kTatarinov.Excpetions;

public class CustomerOperationOutOfBoundException extends OperationRuntimeException {
    private final int idCustomer;
    private final int idOperation;
    public static final String EXC_MESSAGE = "Exception while trying to save operation %s for customer %s";

    public CustomerOperationOutOfBoundException(int customerId, int operationId) {
        super();
        this.idCustomer = customerId;
        this.idOperation = operationId;
    }

    @Override
    public String getMessage() {
        return EXC_MESSAGE.formatted(idOperation, idCustomer);
    }
}
