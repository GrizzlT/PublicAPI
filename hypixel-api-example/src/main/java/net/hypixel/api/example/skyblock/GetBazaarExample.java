package net.hypixel.api.example.skyblock;

import net.hypixel.api.example.ExampleUtil;

public class GetBazaarExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockBazaar().subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
