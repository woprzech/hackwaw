Dokumentacja api
==================

| Opis      | URL       | typ | params  | return|
|-----------|-----------|------|---------| ----------|
| Pobranie wszystkich kawiarni    | /cafes         |GET| - | JSON
|Znalezienie kawiarni w okolicy | /cafe/findByLocation | POST| userX, userY, rad(km)|JSON
|Pobranie menu | /cafe/getMenu | GET/POST | (cafeId, category)/(token) | JSON
|Aktualizacja produktu| /cafe/menu/product/update | POST| token, productId, name, description, price, categoryId| "OK"
|Usuniecie produktu | /cafe/menu/product/remove | POST | token, productId | "OK"
|Dodanie zamowienia| /order/menu/product/update | POST | cafeId, userName, productIds(tablica), minutes | "OK"
|Realizacja zamowienia | /order/realize | POST | token, orderId | "OK"
|Pobranie zamowien | /orders | POST |token|JSON
|Pobranie kategorii| /categories | GET | - | JSON
|Logowanie| /account/login | POST | login, password, token(opcjonalnie) | JSON
|Wylogowanie| /account/logout| POST | token | "OK"
|Rejestracja| /registration | POST | address, cafeName, email, phone | "OK"
|Tworzenie danych testowych | /test/createData | GET | - | "OK"
|Dodanie przez admina kawiarni| /admin/cafe/add | POST | token, cafeName, posX, posY | "OK"
|Edycja danych kawiarni przez admina | /admin/cafe/update | POST | token, cafeId ,cafeName, posX, posY | "OK"
|Usuniecie kawiarni przez dmina | /admin/cafe/remove | POST | token, cafeId | "OK"