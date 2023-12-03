package ru.netology.kTatarinov.service;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.kTatarinov.config.OperationProperties;
import ru.netology.kTatarinov.domain.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Service
@RequiredArgsConstructor
public class AsyncInputOperationService {
    private final Queue<Operation> queue = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProperties operationProperties;

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    private void startAsyncOperationProcessing() {
        new Thread(this::processQueue).start();
    }

    @PostConstruct
    private void postInit() {
        this.startAsyncOperationProcessing();
    }

    private void processQueue() {
        while (true) {
            Operation current_operation = queue.poll();

            if (current_operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(operationProperties.getSleepMilliSeconds());
                } catch (InterruptedException exc) {
                    throw new RuntimeException(exc);
                }
            } else {
                System.out.println("Processing operation:" + current_operation);
                statementService.setOperation(current_operation.getCustomerId(), current_operation);
            }
        }
    }
}
