package net.hypixel.api.example;

public class GetLeaderboardsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getLeaderboards().subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
