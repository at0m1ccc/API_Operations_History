package ru.netology.kTatarinov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.kTatarinov.domain.Operation;
import ru.netology.kTatarinov.service.AsyncInputOperationService;
import ru.netology.kTatarinov.service.StatementService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {
    private final AsyncInputOperationService asyncInputOperationService;
    private final StatementService statementService;

    @PostMapping
    public Operation postOperation(@RequestBody Operation operation) {
        asyncInputOperationService.offerOperation(operation);

        return operation;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Iterable<Operation>> getClientOperations(@PathVariable Integer customerId) {
        List<Operation> operations = statementService.getCustomerOperations(customerId);
        if(operations == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(operations, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable Integer id) {
        AtomicReference<Operation> deletedOperation = new AtomicReference<>(null);

        statementService.getStatement()
                .values().forEach(operations -> operations.removeIf(operation -> {
                    if (operation.getId().equals(id)) {
                        deletedOperation.set(operation);
                        return true;
                    }
                    return false;
                }));
        return deletedOperation.get() == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(deletedOperation.get(), HttpStatus.OK);
    }
}