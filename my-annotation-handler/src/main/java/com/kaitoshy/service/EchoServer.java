package com.kaitoshy.service;

import com.kaitoshy.core.DBCurd;
import com.kaitoshy.core.MyComponent;

@MyComponent
public interface EchoServer {

    @DBCurd(field = "xxx", name = "aaa")
    String assembleString();

}
