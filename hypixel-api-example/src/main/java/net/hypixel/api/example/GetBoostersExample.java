package net.hypixel.api.example;

public class GetBoostersExample {
    public static void main(String[] args) {
        ExampleUtil.API.getBoosters().subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
