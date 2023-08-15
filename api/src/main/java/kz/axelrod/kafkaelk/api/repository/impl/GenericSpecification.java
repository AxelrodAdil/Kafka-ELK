package kz.axelrod.kafkaelk.api.repository.impl;

import jakarta.persistence.criteria.*;
import kz.axelrod.kafkaelk.api.dto.SearchObject;
import lombok.Data;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public abstract class GenericSpecification<T> implements Specification<T> {

    private List<SearchObject> list = new ArrayList<>();

    public void add(SearchObject criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchObject criteria : list) {
            switch (criteria.getOperation()) {
                case JOIN_THAN_EQUAL -> {
                    Join<Object, Object> join = null;
                    for (var tableName : criteria.getJoinTableNameList()) {
                        join = Objects.requireNonNullElse(join, root).join(tableName, JoinType.INNER);
                    }
                    if (join != null) {
                        predicates.add(criteriaBuilder.equal(join.get(criteria.getKey()), criteria.getValue().toString()));
                    }
                }
                case GREATER_THAN ->
                        predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                case LESS_THAN ->
                        predicates.add(criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                case GREATER_THAN_EQUAL ->
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                case LESS_THAN_EQUAL ->
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                case NOT_EQUAL ->
                        predicates.add(criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                case EQUAL -> predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
                case LIKE -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
                case LIKE_END -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
                case LIKE_START ->
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())),
                                "%" + criteria.getValue().toString().toLowerCase()));
                case IN -> predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                case NOT_IN -> predicates.add(criteriaBuilder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
