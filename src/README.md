# File Storage

Проект представляет собой микросервис, который выполняет роль хранилища различных файлов и их атрибутов.
Микросервис предоставлят собой HTTP API и принимает/отдавает запросы/ответы в формате JSON.

### Создание файла.
На вход методу отправляется JSON, включающий в себя файл (в формате base64) и его атрибуты (название - title, дата и
время отправки - creation_date, краткое описание документа - description), на выходе метод возвращает UUID созданного
файла. UUID используется в связи с его непредсказуемой генерацией (зная id можно определить количество файлов, а также 
получить доступ к другим файлам перебирая id).

Request
<pre>
<code> POST http://localhost:8080/file 
{
    "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
    "title": "Первый файл",
    "creationDate": "2017-02-22T09:49:19.275039200",
    "description": "Привет мир"
}
</code>
</pre>

Response
<pre>
<code>
{
    "uuid": "83eb6c0d-e909-455a-b5e2-fd90d29cbf3e"
}
</code>
</pre>
### Получение файла по UUID.
На вход методу отправляется UUID файла, на выходе метод возвращает JSON, включающий в себя файл (в формате base64) и его
атрибуты (название - title, дата и время отправки - creation_date, краткое описание документа - description).

Request
<pre>
<code> 
GET http://localhost:8080/file/83eb6c0d-e909-455a-b5e2-fd90d29cbf3e
</code>
</pre>

Response
<pre>
<code>
{
    "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
    "title": "Первый файл",
    "creationDate": "2017-02-22T09:49:19.275039200",
    "description": "Привет мир"
}
</code>
</pre>

### Получение списка всех файлов.
Метод выводит список всех файлов с использованием пагинаци и сортирует по времени создания файлов.
В параметры запроса указываются номер страницы, которую необходимо вывести (page) и количество элементов, 
которое выводится на страницу (size).

Request
<pre>
<code> 
GET http://localhost:8080/file?page=0&size=2
</code>
</pre>

Response
<pre>
<code>
{
    "content": [
        {
            "uuid": "169a7e2b-eb1f-44c1-88a6-19e9fd5770cf",
            "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
            "title": "Первый файл",
            "creationDate": "2015-02-22T09:49:19.275039",
            "description": "Привет мир"
        },
        {
            "uuid": "83eb6c0d-e909-455a-b5e2-fd90d29cbf3e",
            "file": "0J/RgNC40LLQtdGCINC80LjRgA==",
            "title": "Первый файл",
            "creationDate": "2017-02-22T09:49:19.275039",
            "description": "Привет мир"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 2,
        "sort": {
            "sorted": true,
            "empty": false,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 5,
    "totalElements": 9,
    "last": false,
    "size": 2,
    "number": 0,
    "sort": {
        "sorted": true,
        "empty": false,
        "unsorted": false
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
}
</code>
</pre>