package net.hypixel.api.example.skyblock;

import net.hypixel.api.example.ExampleUtil;

public class GetSkyBlockAuctionsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockAuctions(0).subscribe(page0 -> {
            System.out.println(page0);
            if (page0.hasNextPage()) {
                ExampleUtil.API.getSkyBlockAuctions(page0.getPage() + 1).subscribe(ExampleUtil.getTestConsumer());
            } else {
                System.exit(0);
            }
        });
        ExampleUtil.await();
    }
}
