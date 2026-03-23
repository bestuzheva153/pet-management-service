# Pet Management Service

Backend сервис на Java для управления питомцами и их владельцами.

## About

Проект реализует REST API для работы с питомцами и владельцами.
Поддерживает CRUD операции, фильтрацию и базовую авторизацию пользователей.

Проект был разработан поэтапно в рамках учебного курса, но оформлен как полноценный backend сервис:

- базовая бизнес-логика
- работа с базой данных (Hibernate, JPA)
- REST API (Spring Boot)
- безопасность (Spring Security)

## Tech stack

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven

## Features

- CRUD операции для питомцев и владельцев
- фильтрация и пагинация
- REST API
- авторизация и аутентификация пользователей
- ролевая модель (user / admin)

## Architecture

Проект построен по слоистой архитектуре:

- controller — обработка HTTP запросов
- service — бизнес логика
- repository — работа с базой данных
- dto — модели для API
- model — сущности

## Why this project

Этот проект демонстрирует базовые навыки backend разработки:

- построение REST API
- работа с базой данных через ORM
- разделение логики на слои
- реализация аутентификации и авторизации

## How to run

```bash
mvn clean install
mvn spring-boot:run