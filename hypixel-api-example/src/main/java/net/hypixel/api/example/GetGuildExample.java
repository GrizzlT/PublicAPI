package net.hypixel.api.example;

public class GetGuildExample {
    public static void main(String[] args) {
        ExampleUtil.API.getGuildByPlayer(ExampleUtil.HYPIXEL).subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
