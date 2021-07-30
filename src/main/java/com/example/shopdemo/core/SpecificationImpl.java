package com.example.shopdemo.core;

import com.example.shopdemo.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationImpl<T extends AbstractEntity> implements Specification<T> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {

        if (criteria.getOperation().equals(SearchOperation.ge))
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());

        else if (criteria.getOperation().equals(SearchOperation.le))
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());

        else if (criteria.getOperation().equals(SearchOperation.eq))
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());

        else if (criteria.getOperation().equals(SearchOperation.like))
            return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");

        return null;
    }
}
