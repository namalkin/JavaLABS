package content;
import java.util.*;

/**
 * Класс PortalContent хранит и управляет контентом портала:
 * лучшие игры, новости, обзоры и подборки.
 */
public class PortalContent {
    private final List<String> bestGames = new ArrayList<>(List.of(
        "Counter-Strike 2", "Dota 2", "Minecraft", "GTA V", "League of Legends"
    ));
    private final List<String> news = new ArrayList<>(List.of(
        "GTA VI анонсирована!",
        "Обновление Minecraft 1.21",
        "Dota 2: новый боевой пропуск",
        "League of Legends: старт сезона"
    ));
    private final List<String> reviews = new ArrayList<>(List.of(
        "Counter-Strike 2 — лучший онлайн-шутер",
        "Minecraft — песочница без границ",
        "GTA V — свобода и сюжет",
        "Dota 2 — классика MOBA"
    ));
    private final List<String> collections = new ArrayList<>(List.of(
        "Лучшие кооперативные игры: Portal 2, It Takes Two, Overcooked 2",
        "Игры с открытым миром: GTA V, The Witcher 3, Minecraft",
        "Киберспорт: Dota 2, CS:GO, League of Legends",
        "Для всей семьи: Stardew Valley, Minecraft, Mario Kart"
    ));

    public List<String> getBestGames() { return bestGames; }
    public List<String> getNews() { return news; }
    public List<String> getReviews() { return reviews; }
    public List<String> getCollections() { return collections; }
    public void addNews(String n) { news.add(0, n); }
    public void addReview(String r) { reviews.add(0, r); }
    public void addCollection(String c) { collections.add(0, c); }
    public void addBestGame(String g) { bestGames.add(0, g); }
}
