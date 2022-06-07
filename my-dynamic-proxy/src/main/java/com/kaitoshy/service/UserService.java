package com.kaitoshy.service;

import com.kaitoshy.core.DbCurd;
import com.kaitoshy.core.DbCurdMethodEnum;
import com.kaitoshy.domain.User;

public interface UserService {
    @DbCurd(method = DbCurdMethodEnum.SELECT_ONE)
    User getOne();
}
