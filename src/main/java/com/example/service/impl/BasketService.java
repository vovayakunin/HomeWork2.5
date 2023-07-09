package com.example.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Service
@SessionScope

public class BasketService {
    private List<Long> ids;

    public BasketService() {
        ids = new ArrayList<>();
    }

    public void add(List<Long> ids) {
        this.ids.addAll(ids);
    }
    public List<Long> getAll(){
        return Collections.unmodifiableList(ids);
    }
}
