package ru.netology.kTatarinov.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.kTatarinov.OperationHistoryApiApplicationTest;
import ru.netology.kTatarinov.config.OperationProperties;
import ru.netology.kTatarinov.domain.Currency;
import ru.netology.kTatarinov.domain.Operation;

import static org.junit.jupiter.api.Assertions.*;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    @Autowired
    private StatementService statementService;
    @Autowired
    private OperationProperties operationProperties;

    @Test
    public void asyncInputOperationServiceWorksTest() throws InterruptedException {
        Operation operation = new Operation(3, 123, Currency.USD, "Shop", 1);

        asyncInputOperationService.offerOperation(operation);
        Thread.sleep(5L * operationProperties.getSleepMilliSeconds());
        Operation operationOfService = statementService.getOperation(operation.getCustomerId(), 0);
        assertEquals(operation, operationOfService);
        assertEquals(operation.getId(), operationOfService.getId());
        assertEquals(operation.getSum(), operationOfService.getSum());
        assertEquals(operation.getCurrency(), operationOfService.getCurrency());
        assertEquals(operation.getMerchant(), operationOfService.getMerchant());
    }
}
