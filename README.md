# Образовательный сервис УрФУ

Этот проект представляет собой веб-сервис для работы с данными образовательных программ и их модулей в Уральском федеральном университете (УрФУ).

## Функциональность

Сервис поддерживает следующие функции:

- **CRUD операции**: Создание, чтение, обновление и удаление образовательных программ и их модулей через API.
- **Базовая авторизация**: Используется простая авторизация по логину и паролю (Basic Auth).
- **Главная страница с вкладками**:
  - **Образовательные программы**: Позволяет просматривать список всех образовательных программ с возможностью их добавления, редактирования и удаления.
  - **Модули образовательных программ**: Предоставляет доступ к списку модулей для выбранной образовательной программы, а также возможность связывать программы с модулями.

## Описание API

Для взаимодействия с сервисом используется RESTful API. Доступные эндпоинты:

- **/educProg**: Эндпоинт для работы с образовательными программами.
- **/module**: Эндпоинт для работы с модулями образовательных программ.


## Требования к среде выполнения

- Java — Java 8+
- Spring / Spring Boot
- реляционная СУБД — Postgre или MS SQL
- git — проект доступен на GitHub / GitLab или иных публичных репо, либо полный архив с
- коммитами на Email / Telegram