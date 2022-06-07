package com.kaitoshy.service;

import com.kaitoshy.core.DbCurd;
import com.kaitoshy.core.DbCurdMethodEnum;
import com.kaitoshy.domain.Video;

public interface VideoService {
    @DbCurd(method = DbCurdMethodEnum.SELECT_ONE)
    Video getOne();

}
