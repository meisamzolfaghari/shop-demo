

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'2021-07-31 01:20:20.321192','fruit'),
                                (2,'2021-07-31 01:20:41.022395','milk'),
                                (3,'2021-07-31 01:20:47.091249','bread'),
                                (4,'2021-07-31 01:21:20.406563','clothes'),
                                (5,'2021-07-31 01:21:27.560616','shoes'),
                                (6,'2021-07-31 01:21:41.140166','juice');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `products` WRITE;
INSERT INTO `products` VALUES (1,'2021-07-31 01:55:28.544069','yellow apple',3.46,1),
                              (2,'2021-07-31 01:29:36.980009','cow milk',3.2,2),
                              (3,'2021-07-31 01:25:50.337711','blue shoe',23.45,5),
                              (4,'2021-07-31 01:26:08.138854','green shoe',20,5),
                              (5,'2021-07-31 01:46:09.571883','red apple',3.35,1),
                              (6,'2021-07-31 02:00:52.688265','goat milk',3.88,2);
UNLOCK TABLES;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES
#                            password is "123"
                           (1,'2021-07-31 01:15:19.274581',_binary '\0','zsmeisam@gmail.com','meisam','zolfaghari','$2a$10$y51oJO5XMmkyOFaw8TUHq.clBtp6k1L6W50bAjsfoXLV3ZlY.YYfW'),
#                            password is "123"
                           (2,'2021-07-31 01:18:13.017841',_binary '\0','zsmeimad@gmail.com','morgan','freeman','$2a$10$nVecxERq64NxmxcSURWFFOnAYA/PB6IHsjKBTK07Xte1kcVg6hFHG');
UNLOCK TABLES;

LOCK TABLES `user_roles` WRITE;
INSERT INTO `user_roles` VALUES (1,'ADMIN'),(1,'USER'),(2,'USER');
UNLOCK TABLES;

LOCK TABLES `product_reviews` WRITE;
INSERT INTO `product_reviews` VALUES (2,'2021-07-31 01:57:06.109799','not bad',3,1,1),
                                     (3,'2021-07-31 01:58:18.275606','good job',4,2,1),
                                     (4,'2021-07-31 02:02:16.760599','bad bad',2,6,1),
                                     (5,'2021-07-31 02:04:46.418133','well done',5,1,2),
                                     (10,'2021-07-31 02:23:53.588282','very bad',1,2,2),
                                     (11,'2021-07-31 02:24:18.641682','',2,6,2);
UNLOCK TABLES;
