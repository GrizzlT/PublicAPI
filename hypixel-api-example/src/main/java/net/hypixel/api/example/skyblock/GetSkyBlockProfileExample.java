package net.hypixel.api.example.skyblock;

import com.google.gson.JsonElement;
import net.hypixel.api.example.ExampleUtil;

import java.util.Map;

public class GetSkyBlockProfileExample {
    public static void main(String[] args) {
        ExampleUtil.API.getPlayerByUuid(ExampleUtil.HYPIXEL).subscribe(playerReply -> {
            for (Map.Entry<String, JsonElement> profileEntry : playerReply.getPlayer().getAsJsonObject("stats").getAsJsonObject("SkyBlock").getAsJsonObject("profiles").entrySet()) {
                ExampleUtil.API.getSkyBlockProfile(profileEntry.getKey()).subscribe(ExampleUtil.getTestConsumer());
                break;
            }
        });
        ExampleUtil.await();
    }
}
