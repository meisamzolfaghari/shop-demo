package com.example.shopdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider {

    private static ApplicationContext context;

    @Autowired
    public ApplicationContextProvider(ApplicationContext context) {
        ApplicationContextProvider.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
