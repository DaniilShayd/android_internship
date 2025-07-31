package com.example.androidinternship.data

import com.example.androidinternship.R

val POSTS = listOf(
    Post(
        id = 1,
        title = "Мой первый пост",
        description = "Сегодня я узнал много нового о Jetpack Compose и хочу поделиться этим с вами!",
        isLiked = true,
        userName = "Алексей Петров",
        comments = listOf(
            Comment(
                userName = "Иван Иванов",
                text = "Очень интересный пост, Алексей! Jetpack Compose — это будущее разработки UI."
            )
        )
    ),
    Post(
        id = 2,
        title = "Красота природы",
        description = "Фотографии с моей последней прогулки в лесу. Осень - прекрасное время года!",
        isLiked = false,
        userName = "Мария Иванова",
        comments = listOf(
            Comment(
                userName = "Александр Сидоров",
                text = "Ваши фотографии прекрасны, Мария! Осень — моё любимое время года."
            ),
            Comment(
                userName = "Елена Петрова",
                text = "Я тоже обожаю осенние прогулки! Спасибо за вдохновение."
            ),
            Comment(
                userName = "Дмитрий Козлов",
                text = "Какие красивые снимки! Вы мастерски передали атмосферу леса."
            )
        )
    ),
    Post(
        id = 3,
        title = "Рецепт дня",
        description = "Попробуйте мой новый рецепт тыквенного супа - это просто и вкусно!",
        isLiked = true,
        userName = "Ольга Кузнецова",
        comments = listOf(
            Comment(
                userName = "Екатерина Морозова",
                text = "Ваш рецепт потрясающий! Суп получился очень вкусным."
            ),
            Comment(
                userName = "Анна Семёнова",
                text = "Спасибо за рецепт! Обязательно попробую приготовить."
            ),
            Comment(
                userName = "Игорь Николаев",
                text = "Имбирь в супе — это гениально! Блюдо получилось ароматным."
            )
        )
    ),
    Post(
        id = 4,
        title = "IT новости",
        description = "Google анонсировала новые функции Android 14 на конференции разработчиков",
        isLiked = false,
        userName = "Дмитрий Смирнов",
        comments = listOf(
            Comment(
                userName = "Сергей Васильев",
                text = "Android 14 выглядит очень многообещающе. Жду новых функций!"
            ),
            Comment(
                userName = "Алексей Ковалёв",
                text = "Спасибо за новость! Улучшения конфиденциальности — это важно."
            ),
            Comment(
                userName = "Наталья Волкова",
                text = "Интересно, как новые функции повлияют на батарею. Буду следить."
            )
        )
    ),
    Post(
        id = 5,
        title = "Спортивные достижения",
        description = "Пробежал свой первый марафон! Результат - 3 часа 45 минут.",
        isLiked = true,
        userName = "Иван Козлов",
        comments = listOf(
            Comment(
                userName = "Анна Соколова",
                text = "Поздравляю, Иван! Это отличный результат."
            ),
            Comment(
                userName = "Михаил Павлов",
                text = "Мои поздравления! Марафон — это серьёзное испытание."
            ),
            Comment(
                userName = "Светлана Иванова",
                text = "Вы молодец! Ваш результат вдохновляет."
            )
        )
    )
)

val users = listOf(
    User(
        id = 1,
        nickname = "@john_doe",
        name = "John Doe",
        address = "123 Main St",
        phoneNumber = "+1234567890",
        comments = listOf(
            Comment("Admin", "Great user!"),
            Comment("User123", "Always helpful in discussions."),
            Comment("Guest", "Nice to have around."),
            Comment("Support", "Quick to respond.")
        )
    ),
    User(
        id = 2,
        nickname = "@jane_smith",
        name = "Jane Smith",
        address = "456 Elm St",
        phoneNumber = "+0987654321",
        comments = listOf(
            Comment("Moderator", "Active participant."),
            Comment("Alex", "Shares valuable insights."),
            Comment("Lily", "Good at problem solving."),
            Comment("Admin", "Reliable contributor.")
        )
    ),
    User(
        id = 3,
        nickname = "@alice_johnson",
        name = "Alice Johnson",
        address = "789 Oak St",
        phoneNumber = "+1122334455",
        comments = listOf(
            Comment("Admin", "Helpful and friendly."),
            Comment("Mike", "Very polite and responsive."),
            Comment("Sarah", "Enjoyed working with her."),
            Comment("Moderator", "Consistently positive attitude.")
        )
    )
)

val albums = listOf(
    Album(
        1, "Природа", listOf(
            R.drawable.photo1, R.drawable.photo2, R.drawable.photo3
        )
    ),
    Album(
        2, "Города", listOf(
            R.drawable.photo4, R.drawable.photo5, R.drawable.photo6
        )
    ),
    Album(
        3, "Архитектура", listOf(
            R.drawable.photo7, R.drawable.photo8, R.drawable.photo9
        )
    )
)