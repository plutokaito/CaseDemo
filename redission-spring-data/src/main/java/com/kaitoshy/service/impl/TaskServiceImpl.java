package com.kaitoshy.service.impl;

import com.kaitoshy.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskServiceImpl implements TaskService {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void add() {
        count.incrementAndGet();
    }

    @Override
    public Integer get() {
        return count.get();
    }

    @Override
    public Integer result() {
        add();
        return get();
    }
}
