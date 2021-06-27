package net.hypixel.api.example;

public class KeyInfoExample {
    public static void main(String[] args) {
        ExampleUtil.API.getKey().subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
