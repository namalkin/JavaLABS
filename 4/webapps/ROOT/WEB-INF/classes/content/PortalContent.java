package content;
import java.util.*;

/**
 * Класс PortalContent хранит и управляет контентом портала:
 * лучшие игры, новости, обзоры и подборки.
 */
public class PortalContent {

    /**
     * Вложенный класс NewsPost представляет собой новостной пост с заголовком, текстом,
     * автором и количеством просмотров.
     */
    public static class NewsPost {
        private static int idCounter = 1;
        private final int id;
        private String title;
        private String text;
        private String author;
        private int views;

        public NewsPost(String title, String text, String author) {
            this.id = idCounter++;
            this.title = title;
            this.text = text;
            this.author = author;
            this.views = 0;
        }
        public int getId() { return id; }
        public String getTitle() { return title; }
        public String getText() { return text; }
        public String getAuthor() { return author; }
        public int getViews() { return views; }
        public void incrementViews() { views++; }
    }

    private final List<String> bestGames = new ArrayList<>(List.of(
        "Counter-Strike 2", "Dota 2", "Minecraft", "GTA V", "League of Legends"
    ));
    private final List<NewsPost> news = new ArrayList<>(List.of(
        new NewsPost("GTA VI анонсирована!", "Rockstar официально анонсировала GTA VI.", "system"),
        new NewsPost("Обновление Minecraft 1.21", "Вышло крупное обновление Minecraft 1.21.", "system"),
        new NewsPost("Dota 2: новый боевой пропуск", "В Dota 2 стартовал новый боевой пропуск.", "system"),
        new NewsPost("League of Legends: старт сезона", "В LoL начался новый сезон.", "system")
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
    public List<NewsPost> getNews() { return news; }
    public List<String> getReviews() { return reviews; }
    public List<String> getCollections() { return collections; }
    public void addNews(String title, String text, String author) {
        news.add(0, new NewsPost(title, text, author));
    }
    public void addReview(String r) { reviews.add(0, r); }
    public void addCollection(String c) { collections.add(0, c); }
    public void addBestGame(String g) { bestGames.add(0, g); }
    public NewsPost getNewsById(int id) {
        for (NewsPost post : news) {
            if (post.getId() == id) return post;
        }
        return null;
    }
}
