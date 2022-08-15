package com.careerdevs.jsonplaceholder.models;

public class PostModel {

//        "userId": 1,
//        "id": 1,
//        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//        "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"

        private String userId;
        private String id;
        private String title;
        private String body;

        public String getUserId() {
                return userId;
        }

        public String getId() {
                return id;
        }

        public String getTitle() {
                return title;
        }

        public String getBody() {
                return body;
        }
}
