package ru.netology.kTatarinov.service;

import org.springframework.stereotype.Service;
import ru.netology.kTatarinov.domain.Operation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatementService {

    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public Map<Integer, List<Operation>> getStatement() {
        return storage;
    }


    public Operation getOperation(int clientId, int operationIndex) {
        return storage.get(clientId).get(operationIndex);
    }

    public void setOperation(int clientId, Operation operation) {
        List<Operation> operations = storage.getOrDefault(clientId, new ArrayList<>());
        operations.add(operation);
        storage.put(clientId, operations);
    }

    public List<Operation> getCustomerOperations(int customerId) {
        if(storage.containsKey(customerId)){
            return storage.getOrDefault(customerId, List.of());
        }
        return null;
    }
}