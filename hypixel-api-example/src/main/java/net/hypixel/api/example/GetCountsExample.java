package net.hypixel.api.example;

public class GetCountsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getCounts().subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
