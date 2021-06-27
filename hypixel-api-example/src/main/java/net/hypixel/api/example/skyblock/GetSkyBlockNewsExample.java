package net.hypixel.api.example.skyblock;

import net.hypixel.api.example.ExampleUtil;

public class GetSkyBlockNewsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockNews().subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
