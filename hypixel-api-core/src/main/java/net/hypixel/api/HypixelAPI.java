package net.hypixel.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.hypixel.api.adapters.*;
import net.hypixel.api.data.type.GameType;
import net.hypixel.api.data.type.ServerType;
import net.hypixel.api.exceptions.BadResponseException;
import net.hypixel.api.exceptions.BadStatusCodeException;
import net.hypixel.api.http.HTTPQueryParams;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.http.HypixelHttpResponse;
import net.hypixel.api.reply.*;
import net.hypixel.api.reply.skyblock.*;
import net.hypixel.api.util.ResourceType;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.UUID;

public class HypixelAPI {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(UUID.class, new UUIDTypeAdapter())
            .registerTypeAdapter(GameType.class, new GameTypeTypeAdapter())
            .registerTypeAdapter(ServerType.class, new ServerTypeTypeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new DateTimeTypeAdapter())
            .registerTypeAdapterFactory(new BoostersTypeAdapterFactory<>(BoostersReply.Booster.class))
            .create();
    private static final String BASE_URL = "https://api.hypixel.net/";

    private final HypixelHttpClient httpClient;

    /**
     * @param httpClient a {@link HypixelHttpClient} that implements the HTTP behaviour for communicating with the API
     */
    public HypixelAPI(HypixelHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Shuts down the {@link HypixelHttpClient}
     */
    public void shutdown() {
        httpClient.shutdown();
    }

    public Mono<BoostersReply> getBoosters() {
        return get(BoostersReply.class, "boosters");
    }

    public Mono<LeaderboardsReply> getLeaderboards() {
        return get(LeaderboardsReply.class, "leaderboards");
    }

    public Mono<PunishmentStatsReply> getPunishmentStats() {
        return get(PunishmentStatsReply.class, "punishmentstats");
    }

    /**
     * @param player uuid of a player
     * @return {@link Mono} containing {@link PlayerReply}
     */
    public Mono<PlayerReply> getPlayerByUuid(UUID player) {
        return get(PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed.
     * @return {@link Mono} containing {@link PlayerReply}
     */
    public Mono<PlayerReply> getPlayerByUuid(String player) {
        return get(PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player the minecraft username of the player.
     * @return {@link Mono} containing {@link PlayerReply}
     * @deprecated While this method should continue functioning we recommend using the Mojang API for requesting UUID's by username.
     * See issue <a href="https://github.com/HypixelDev/PublicAPI/issues/249#issuecomment-645634722">#249</a>
     * This endpoint is also subject to limiting requests of the same username in a short period of time.
     */
    @Deprecated
    public Mono<PlayerReply> getPlayerByName(String player) {
        return get(PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("name", player)
        );
    }

    public Mono<FriendsReply> getFriends(UUID player) {
        return get(FriendsReply.class, "friends",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed
     * @return {@link Mono} containing {@link FriendsReply}
     */
    public Mono<FriendsReply> getFriends(String player) {
        return get(FriendsReply.class, "friends",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player uuid of a player
     * @return {@link Mono} containing {@link GuildReply}
     */
    public Mono<GuildReply> getGuildByPlayer(UUID player) {
        return get(GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("player", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed
     * @return {@link Mono} containing {@link GuildReply}
     */
    public Mono<GuildReply> getGuildByPlayer(String player) {
        return get(GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("player", player)
        );
    }

    /**
     * @param name the name of the guild
     * @return {@link Mono} containing {@link GuildReply}
     */
    public Mono<GuildReply> getGuildByName(String name) {
        return get(GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("name", name)
        );
    }

    /**
     * @param id mongo id hex string
     * @return {@link Mono} containing {@link GuildReply}
     */
    public Mono<GuildReply> getGuildById(String id) {
        return get(GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("id", id)
        );
    }

    public Mono<KeyReply> getKey() {
        return get(KeyReply.class, "key");
    }

    public Mono<CountsReply> getCounts() {
        return get(CountsReply.class, "counts");
    }

    /**
     * Gets the current status of the player with information about the server they are in
     * at that moment.
     * In case the person is in limbo, result will be the last known server
     *
     * @param uuid of player
     * @return {@link Mono} containing {@link StatusReply}
     */
    public Mono<StatusReply> getStatus(UUID uuid) {
        return get(StatusReply.class, "status",
                HTTPQueryParams.create()
                        .add("uuid", uuid)
        );
    }

    /**
     * Gets up to 100 of the player's most recently played games. Games are removed from this list after 3 days.
     *
     * @param uuid of player
     * @return {@link Mono} containing {@link RecentGamesReply}
     */
    public Mono<RecentGamesReply> getRecentGames(UUID uuid) {
        return get(RecentGamesReply.class, "recentGames",
                HTTPQueryParams.create()
                        .add("uuid", uuid)
        );
    }

    /**
     * Retrieve resources which don't change often.
     *
     * @param resource to be requested
     * @return {@link Mono} containing {@link ResourceReply}
     */
    public Mono<ResourceReply> getResource(ResourceType resource) {
        return getResource(resource.getPath());
    }

    public Mono<ResourceReply> getResource(String resource) {
        return requestResource(resource);
    }

    public Mono<SkyBlockProfileReply> getSkyBlockProfile(String profile) {
        return get(SkyBlockProfileReply.class, "skyblock/profile",
                HTTPQueryParams.create()
                        .add("profile", profile)
        );
    }

    public Mono<SkyBlockNewsReply> getSkyBlockNews() {
        return get(SkyBlockNewsReply.class, "skyblock/news");
    }

    public Mono<SkyBlockAuctionsReply> getSkyBlockAuctions(int page) {
        return get(SkyBlockAuctionsReply.class, "skyblock/auctions",
                HTTPQueryParams.create()
                        .add("page", page)
        );
    }

    /**
     * Requests information about products in bazaar.
     *
     * @return {@link Mono} containing {@link SkyBlockBazaarReply}
     */
    public Mono<SkyBlockBazaarReply> getSkyBlockBazaar() {
        return get(SkyBlockBazaarReply.class, "skyblock/bazaar");
    }

    private <R extends AbstractReply> Mono<R> get(Class<R> clazz, String request) {
        return get(clazz, request, null);
    }

    private <R extends AbstractReply> Mono<R> get(Class<R> clazz, String request, HTTPQueryParams params) {
        String url = BASE_URL + request;
        if (params != null) {
            url = params.getAsQueryString(url);
        }
        return httpClient.makeAuthenticatedRequest(url)
                .map(this::checkResponse)
                .map(response -> {
                    if (clazz == ResourceReply.class) {
                        return checkReply((R) new ResourceReply(GSON.fromJson(response.getBody(), JsonObject.class)));
                    }
                    return checkReply(GSON.fromJson(response.getBody(), clazz));
                });
    }

    private Mono<ResourceReply> requestResource(String resource) {
        return httpClient.makeRequest(BASE_URL + "resources/" + resource)
                .map(this::checkResponse)
                .map(response -> checkReply(new ResourceReply(GSON.fromJson(response.getBody(), JsonObject.class))));
    }

    /**
     * Checks the status of the response and throws an exception if needed
     */
    private HypixelHttpResponse checkResponse(HypixelHttpResponse response) {
        if (response.getStatusCode() == 200) {
            return response;
        }

        String cause;
        try {
            cause = GSON.fromJson(response.getBody(), JsonObject.class).get("cause").getAsString();
        } catch (JsonSyntaxException ignored) {
            cause = "Unknown (body is not json)";
        }

        throw new BadStatusCodeException(response.getStatusCode(), cause);
    }

    /**
     * Checks reply and throws appropriate exceptions based on it's content
     *
     * @param reply The reply to check
     * @param <T>   The class of the reply
     * @return the same object that was provided for cleaner usage
     */
    private <T extends AbstractReply> T checkReply(T reply) {
        if (reply != null) {
            if (!reply.isSuccess()) {
                throw new BadResponseException(reply.getCause());
            }
        }
        return reply;
    }
}
