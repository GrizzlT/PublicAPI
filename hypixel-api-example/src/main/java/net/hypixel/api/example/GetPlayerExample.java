package net.hypixel.api.example;

public class GetPlayerExample {
    public static void main(String[] args) {
        ExampleUtil.API.getPlayerByUuid(ExampleUtil.HYPIXEL).subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
