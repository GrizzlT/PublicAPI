package net.hypixel.api.example;

public class GetPunishmentStatsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getPunishmentStats().subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
