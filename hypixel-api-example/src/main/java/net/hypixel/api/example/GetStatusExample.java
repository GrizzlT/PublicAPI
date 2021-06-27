package net.hypixel.api.example;

public class GetStatusExample {
    public static void main(String[] args) {
        // online may vary from player to player, this is a setting that can be disabled by the player
        // see comment in StatusReply
        ExampleUtil.API.getStatus(ExampleUtil.HYPIXEL).subscribe(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
