INSERT INTO MEMBER VALUES (1, '김종훈');
INSERT INTO MEMBER VALUES (2, '김현아');
INSERT INTO MEMBER VALUES (3, '성지운');
INSERT INTO MEMBER VALUES (4, '최혜미');

INSERT INTO TRIP VALUES (1, NOW(), NULL, NOW(), '2020-10-20', FALSE, TRUE, '2020-10-18', '테스트여행1', 1);
INSERT INTO TRIP VALUES (2, NOW(), NULL, NOW(), '2020-01-20', FALSE, TRUE, '2020-01-15', '테스트여행2', 1);
INSERT INTO TRIP VALUES (3, NOW(), NULL, NOW(), '2020-02-20', TRUE, TRUE, '2020-02-16', '테스트여행3', 2);
INSERT INTO TRIP VALUES (4, NOW(), NULL, NOW(), '2020-04-04', FALSE, TRUE, '2020-03-17', '테스트여행4', 2);
INSERT INTO TRIP VALUES (5, NOW(), NULL, NOW(), '2020-05-18', FALSE, TRUE, '2020-05-12', '테스트여행5', 3);
INSERT INTO TRIP VALUES (6, NOW(), NULL, NOW(), '2020-02-12', TRUE, TRUE, '2020-02-11', '테스트여행6', 4);
ALTER TABLE TRIP ALTER COLUMN TRIP_ID RESTART WITH 5;


INSERT INTO ITINERARY VALUES ('Movement', 1, NOW(), NULL, NULL, '서울역 -> 대전역', 1, NOW(), 1);
INSERT INTO MOVEMENT VALUES ('2020-10-18 08:00:05', '서울역', '2020-10-18 10:30:00', '대전역', '기차', 1);
INSERT INTO ITINERARY VALUES ('Stay', 2, NOW(), NULL, NULL, '성심당', 2, NOW(), 1);
INSERT INTO STAY VALUES ('2020-10-18 10:30:00', '2020-10-18 14:30:00', 2);
INSERT INTO ITINERARY VALUES ('Movement', 3, NOW(), NULL, NULL, '성심당 -> 친구집', 3, NOW(), 1);
INSERT INTO MOVEMENT VALUES ('2020-10-18 14:30:00', '성심당', '2020-10-18 18:30:00', '친구집', '도보', 3);
INSERT INTO ITINERARY VALUES ('Lodgement', 4, NOW(), NULL, NULL, '친구집', 4, NOW(), 1);
INSERT INTO LODGEMENT VALUES ('2020-10-18 15:00:00', '2020-10-20 10:00:00', 4);
INSERT INTO ITINERARY VALUES ('Movement', 5, NOW(), NULL, NULL, '친구집 -> 엑스포', 5, NOW(), 1);
INSERT INTO MOVEMENT VALUES ('2020-10-20 10:00:00', '친구집', '2020-10-20 12:00:00', '엑스포', '기차', 5);
INSERT INTO ITINERARY VALUES ('Lodgement', 6, NOW(), NULL, NULL, '엑스포', 6, NOW(), 1);
INSERT INTO LODGEMENT VALUES ('2020-10-20 12:00:00', '2020-10-20 18:00:00', 6);
INSERT INTO ITINERARY VALUES ('Movement', 7, NOW(), NULL, NULL, '대전역 -> 서울역', 6, NOW(), 1);
INSERT INTO MOVEMENT VALUES ('2020-10-20 18:00:00', '대전역', '2020-10-20 20:00:00', '서울역', '기차', 7);


INSERT INTO ITINERARY VALUES ('Movement', 8, NOW(), NULL, NULL, '우리집 -> 쟤내집', 1, NOW(), 2);
INSERT INTO MOVEMENT VALUES ('2020-10-18 08:00:05', '우리집', '2020-10-18 10:30:00', '쟤내집', '날아서', 8);
INSERT INTO ITINERARY VALUES ('Stay', 9, NOW(), NULL, NULL, '쟤내집', 2, NOW(), 2);
INSERT INTO STAY VALUES ('2020-10-18 10:30:00', '2020-10-18 14:30:00', 9);
INSERT INTO ITINERARY VALUES ('Movement', 10, NOW(), NULL, NULL, '쟤내집 -> 우리집', 3, NOW(), 2);
INSERT INTO MOVEMENT VALUES ('2020-10-18 14:30:00', '쟤내집', '2020-10-18 18:30:00', '우리집', '땅굴', 10);

INSERT INTO ITINERARY VALUES ('Movement', 11, NOW(), NULL, NULL, '지구-화성', 1, NOW(), 3);
INSERT INTO MOVEMENT VALUES ('2020-10-18 08:00:05', '지구', '2020-10-18 10:30:00', '화성', '로켓', 11);
INSERT INTO ITINERARY VALUES ('Lodgement', 12, NOW(), NULL, NULL, '테슬라화성기지', 2, NOW(), 3);
INSERT INTO STAY VALUES ('2020-10-18 10:30:00', '2020-10-18 14:30:00', 13);
INSERT INTO ITINERARY VALUES ('Movement', 13, NOW(), NULL, NULL, '화성-지구', 3, NOW(), 3);
INSERT INTO MOVEMENT VALUES ('2020-10-18 14:30:00', '지구', '2020-10-18 18:30:00', '화성', '포탈', 13);

INSERT INTO ITINERARY VALUES ('Movement', 14, NOW(), NULL, NULL, '인천공항-나리타공항', 1, NOW(), 4);
INSERT INTO MOVEMENT VALUES ('2020-10-18 08:00:05', '인천공항', '2020-10-18 10:30:00', '나리타공항', '비행기', 14);
INSERT INTO ITINERARY VALUES ('Stay', 15, NOW(), NULL, NULL, '나리타머시기', 2, NOW(), 4);
INSERT INTO STAY VALUES ('2020-10-18 10:30:00', '2020-10-18 14:30:00', 15);
INSERT INTO ITINERARY VALUES ('Lodgement', 16, NOW(), NULL, NULL, '어딘가의호텔', 2, NOW(), 4);
INSERT INTO STAY VALUES ('2020-10-18 10:30:00', '2020-10-18 14:30:00', 16);
INSERT INTO ITINERARY VALUES ('Movement', 17, NOW(), NULL, NULL, '나리타공항-인천공항', 3, NOW(), 4);
INSERT INTO MOVEMENT VALUES ('2020-10-18 14:30:00', '나리타공항', '2020-10-18 18:30:00', '인천공항', '전세기', 17);

ALTER TABLE ITINERARY ALTER COLUMN ITINERARY_ID RESTART WITH 18;