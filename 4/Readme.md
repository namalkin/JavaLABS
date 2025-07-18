# Игровой портал на Java (Servlet/JSP)

## Описание

Данный проект — это учебный игровой портал, реализованный на Java с использованием сервлетов, JSP и стандартного API Jakarta EE (Servlet API). Портал поддерживает роли пользователей (Администратор, Модератор, Пользователь), хранит статистику посещений, позволяет добавлять новости, обзоры, подборки, а также загружать аватары пользователей.

### Основные возможности

- Аутентификация пользователей и разграничение ролей
- Личный кабинет с отображением статистики посещений
- Просмотр и добавление новостей, обзоров, подборок (для модераторов)
- Управление пользователями (для администратора)
- Загрузка и отображение аватара пользователя
- Счетчики посещений для различных разделов
- Хранение данных в памяти и сериализация счетчиков посещений

## Структура проекта

- `webapps/ROOT/WEB-INF/classes/servlet/` — сервлеты (контроллеры)
- `webapps/ROOT/WEB-INF/classes/service/` — сервисы (логика)
- `webapps/ROOT/WEB-INF/classes/domain/` — доменные объекты (User, Role, VisitCounter)
- `webapps/ROOT/WEB-INF/classes/dao/` — DAO для сериализации счетчиков
- `webapps/ROOT/WEB-INF/classes/content/` — контент портала
- `webapps/ROOT/WEB-INF/web.xml` — конфигурация сервлетов
- `webapps/ROOT/*.jsp` — представления (JSP)
- `webapps/ROOT/uploads/` — загруженные аватары

## Диаграмма взаимодействия (Mermaid)

```mermaid
flowchart TD
    subgraph Клиент/Браузер;
        A1[Запрос &lpar;HTTP&rpar;]
        A2[Ответ &lpar;HTML/JSP&rpar;]
    end

    subgraph Сервер/Tomcat;
        B1[web.xml]
        B2[Servlet &lpar;LoginServlet, DashboardServlet, ...&rpar;]
        B3[Service &lpar;UserService, VisitCounterService &rpar;]
        B4[DAO &lpar;VisitCounterDao&rpar;]
        B5[Domain &lpar;User, Role, VisitCounter&rpar;]
        B6[PortalContent]
        B7[Session/Context]
    end

    A1 -->|HTTP-запрос| B1
    B1 -->|Маршрутизация| B2
    B2 -->|Логика| B3
    B2 -->|Контент| B6
    B2 -->|Сессия/Контекст| B7
    B3 -->|Данные| B5
    B3 -->|Сериализация| B4
    B4 -->|Файл .ser| B4
    B2 -->|JSP| A2
```

## Диаграмма классов (Mermaid)

```mermaid
classDiagram
    class User {
        -String username
        -String password
        -Role role
        -String imagePath
        +getUsername()
        +getPassword()
        +getRole()
        +getImagePath()
        +setRole()
    }
    class Role {
        <<enum>>
        ADMIN
        MODERATOR
        USER
    }
    class VisitCounter {
        -String section
        -int count
        +increment()
        +getCount()
    }
    class PortalContent {
        -List~String~ bestGames
        -List~NewsPost~ news
        -List~String~ reviews
        -List~String~ collections
        +getBestGames()
        +getNews()
        +getReviews()
        +getCollections()
        +addNews()
        +addReview()
        +addCollection()
        +addBestGame()
        +getNewsById()
    }
    class NewsPost {
        -int id
        -String title
        -String text
        -String author
        -int views
        +getId()
        +getTitle()
        +getText()
        +getAuthor()
        +getViews()
        +incrementViews()
    }
    class UserService {
        -Map~String, User~ users
        +authenticate()
        +getUser()
        +getAvatar()
        +addUser()
        +setUserRole()
        +getAllUsers()
    }
    User "1" -- "1" Role
    PortalContent "1" o-- "*" NewsPost
    PortalContent "1" o-- "*" String : reviews
    PortalContent "1" o-- "*" String : collections
    PortalContent "1" o-- "*" String : bestGames
    UserService "1" o-- "*" User
```

## Диаграмма последовательности (Mermaid)

```mermaid
sequenceDiagram
    participant Клиент
    participant LoginServlet
    participant UserService
    participant Session

    Клиент->>LoginServlet: POST /login (логин, пароль)
    LoginServlet->>UserService: authenticate(логин, пароль)
    UserService-->>LoginServlet: User / null
    alt Успешно
        LoginServlet->>Session: setAttribute("user", User)
        LoginServlet-->>Клиент: redirect /dashboard
    else Ошибка
        LoginServlet-->>Клиент: показать ошибку
    end

    %% Добавлен сценарий добавления контента
    participant ContentServlet
    participant PortalContent

    Клиент->>ContentServlet: POST /content (type, title, text)
    ContentServlet->>PortalContent: addNews()/addReview()/addCollection()
    PortalContent-->>ContentServlet: (void)
    ContentServlet-->>Клиент: redirect /content
```

## Быстрый старт

1. Скомпилируйте проект с помощью `compile.bat`.
2. Запустите сервер через `run.bat`.
3. Откройте [http://localhost:8080/myportal/](http://localhost:8080/myportal/) в браузере.

## Пользователи по умолчанию

- **admin / 1234** — Администратор
- **moderator / abcd** — Модератор
- **user / pass** — Обычный пользователь

## Примечания

- Все счетчики посещений сериализуются в папку `counters`.
- Загруженные аватары сохраняются в папку `uploads`.
- Для корректной работы требуется Tomcat 11+ и JDK 17+.

---
