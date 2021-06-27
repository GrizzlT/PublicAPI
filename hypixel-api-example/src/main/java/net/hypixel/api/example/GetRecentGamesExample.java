package net.hypixel.api.example;

public class GetRecentGamesExample {

    public static void main(String[] args) {
        ExampleUtil.API.getRecentGames(ExampleUtil.HYPIXEL).subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
