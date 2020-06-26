package com.pay.api.user.repository;

import com.pay.api.base.BaseRepositoryImpl;
import com.pay.api.user.model.QUser;
import com.pay.api.user.model.User;

public class UserRepositoryCustomImpl extends BaseRepositoryImpl implements UserRepositoryCustom {

    private final QUser table = QUser.user;

    public UserRepositoryCustomImpl() {
        super(User.class);
    }
}
