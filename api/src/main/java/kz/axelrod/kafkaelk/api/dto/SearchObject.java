package kz.axelrod.kafkaelk.api.dto;

import kz.axelrod.kafkaelk.api.repository.impl.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchObject {

    private String key;
    private Object value;
    private SearchOperation operation;
}
