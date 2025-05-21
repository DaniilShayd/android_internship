package com.example.androidinternship.data

val posts = listOf(
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
    User(1, "@john_doe", "John Doe", "123 Main St", "+1234567890"),
    User(2, "@jane_smith", "Jane Smith", "456 Elm St", "+0987654321"),
    User(3, "@alice_johnson", "Alice Johnson", "789 Oak St", "+1122334455")
)