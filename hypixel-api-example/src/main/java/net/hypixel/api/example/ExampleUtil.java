package net.hypixel.api.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reactor.ReactorHttpClient;
import net.hypixel.api.reply.AbstractReply;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ExampleUtil {

    public static final HypixelAPI API;

    static {
        String key = System.getProperty("apiKey", "64bd424e-ccb0-42ed-8b66-6e42a135afb4"); // arbitrary key, replace with your own to test or use the property
        API = new HypixelAPI(new ReactorHttpClient(UUID.fromString(key)));
    }

    public static final UUID HYPIXEL = UUID.fromString("f7c77d99-9f15-4a66-a87d-c4a51ef30d19");

    /**
     * Keep the program alive till we explicitly exit.
     */
    public static void await() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T extends AbstractReply> Consumer<T> getTestConsumer() {
        return result -> {
            System.out.println(result);

            System.exit(0);
        };
    }
}
