package com.kaitoshy.service;

import com.kaitoshy.annotation.DBCurd;
import com.kaitoshy.annotation.MyComponent;

@MyComponent
public interface EchoServer {

    @DBCurd(field = "xxx", name = "aaa")
    String assembleString();

}
