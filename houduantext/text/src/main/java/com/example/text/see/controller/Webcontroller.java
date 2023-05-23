package com.example.text.see.controller;

import com.example.text.see.websockets.Websocket;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/websocket/")
public class Webcontroller {
    @Resource
    private Websocket websocket;

    @RequestMapping("online/")
    public void online() throws IOException {
        String msg = "skfjka";
        for (int i = 0; i < 10; i++) {
            websocket.sendMessageAll(msg, "kldkssd");
        }
    }
}
