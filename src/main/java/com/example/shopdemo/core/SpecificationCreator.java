package com.example.shopdemo.core;

import com.example.shopdemo.entity.AbstractEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationCreator {

    private SpecificationCreator() {
    }

    public static <T extends AbstractEntity> Specification<T> createFrom(List<SearchCriteria> searchCriteriaList) {

        if (searchCriteriaList.isEmpty())
            return null;

        Specification<T> searchSpecification = Specification.where(new SpecificationImpl<>(searchCriteriaList.get(0)));

        for (int i = 1; i < searchCriteriaList.size(); i++)
            searchSpecification = searchSpecification.and(new SpecificationImpl<>(searchCriteriaList.get(i)));

        return searchSpecification;
    }
}
