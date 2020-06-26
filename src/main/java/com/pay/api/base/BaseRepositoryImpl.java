package com.pay.api.base;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public abstract class BaseRepositoryImpl extends QuerydslRepositorySupport {

    public BaseRepositoryImpl(Class<?> domainClass) {
        super(domainClass);
    }
}
