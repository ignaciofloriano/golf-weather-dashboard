package com.ignacio;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> {
            ctx.contentType("text/plain; charset=utf-8");
            ctx.result("⛳ Golf Weather Dashboard — OK");
        });
    }
}
