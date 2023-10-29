 6조 FastCampus ToyProject 2
=======================

6조 토이프로젝트 입니다.

이름: 김종훈, 김현아, 성지운, 최혜미

- - -

### 프로젝트 사용 기술

- **언어** : OpenJDK 11
- **스프링 버전** : 2.7
- **빌드** : Gradle 8.1.1
- **테스트** : Junit Jupiter 5.7.2
- **형상 관리** : Git
- **저장소** : GitHub
- **라이브러리 의존성** : Lombok
- **DB** : h2(MySQL)

- - -

### 프로젝트 목표

1. 모듈링 & 객체 지향 설계:
2. DDD 패키지 구성: DDD 클래스 별로 역할 분담 & 최종 리팩터링 진행
3. Spring - JPA - DB 간의 관계와 그 이해.
4. 예외처리 핸들러의 활용

등 Spring 프레임워크를 적극적으로 활용 하려 하였습니다.


- - -

### 프로젝트 진행

- GitHub Repository [link:GitHub](https://github.com/FC-BE-ToyProject-Team6/KDT_Y_BE_Toy_Project2_DEV)
- 프로젝트의 이슈를 적극적으로 활용 [link:프로젝트 이슈](https://github.com/FC-BE-ToyProject-Team6/KDT_Y_BE_Toy_Project2_DEV/issues?q=is%3Aissue+is%3Aclosed)
- 코드 리뷰를 통해 적극적으로 의견 표출 [link:코드 리뷰](https://github.com/FC-BE-ToyProject-Team6/KDT_Y_BE_Toy_Project2_DEV/pulls?q=is%3Apr+is%3Aclosed)
- - -

### 기능 구현

- 여행과 여정의 엔티티를 나눠서 관리
- 여정은 '이동', '숙박', '체류' 셋으로 나눠지므로 여정을 상속받은 세 엔티티로 관리
- 일시에 대한 공통 유틸리티 : 출발일-종료일등 여행, 여정 기간을 계산하는 유틸리티 생성(DateUtil)
- 여행 일정 장소에 대한 정보 구현 : 국내외의 여정 장소에 대해 Google GeoAPI를 이용해 좌표 검색

### API EndPoint

 간단한 수준의 회원 기능을 구현했습니다.(테스트 용 사용자의 id는 1)

- 사용자 추가
```
 POST /member
```
```
{ nickname : '회원명' }
```

----

아래 API 부터는 {memberId} PathVariable을 1로 표시하였습니다.


- 전체 여행 조회
```
GET /api/member/1/trip/all
```
```
파라미터 없음
```
---

- 단일 여행 상세 조회
```
GET /api/member/1/trip/{tripId}
```
```
파라미터 없음
```
---

- 여행 입력
``` 
POST /api/member/1/trip
```    
``` 
{  
  "tripName" : "제주도 여행",
  "startDate" : "2023-11-01",
  "endDate" : "2023-11-10",
  "isDomestic" : false
} 
```
---
- 여행 수정
```
PUT /api/member/1/trip/{tripId}
```
```
{
    "tripName" : "서울 여행",
    "startDate" : "2023-12-01",
    "endDate" : "2023-12-10",
    "isDomestic" : true
}
```
---

- 여행 삭제
```
DELETE /api/member/1/trip/{tripId}
```
```
파라미터 없음
```
---



- 여정 입력
```
POST api/member/1/trip-itineraries/{tripId}
```
```
[
  {
    "type" : "MOVEMENT",
    "item" : "비행기",
    "startDate" : "2023-10-22T12:03:10",
    "endDate" : "2023-10-22T22:00:10",
    "order" : 8,
    "departurePlace" : "인천 공항",
    "arrivalPlace" : "도쿄 공항"
  },
  ...
]
```
---

- 여정 수정
```
PUT /api/member/1/trip-itineraries/{tripId}
```
```
[
  {
    "itineraryId" : 11,
    "type" : "MOVEMENT",
    "item" : "로켓",
    "startDate" : "2023-10-23T17:03:10",
    "endDate" : "2023-10-24T00:00:10",
    "order" : 8,
    "departurePlace" : "우주센터",
    "arrivalPlace" : "어쩌구저쩌구"
  },
  ...
]
```
---

- 여정 삭제
```
POST /api/member/1/trip-itineraries/delete/{tripId}
```
```
[ 3, 4 ... ]

```
---


### 프로젝트 설명
- 패키지 구조 및 기능
```
📦 src
└─ 📂main
   │  ├─ 📂toyproject
   │  │  ├─ 📜ToyprojectApplication.java
   │  │  ├─ 📂common
   │  │  │  ├─ 📜BaseTimeEntity.java: 시간 관련 공통 처리 Entity(각 도메인 Entity에서 상속)
   │  │  │  ├─ 📂dto: 공통으로 사용하는 Response의 DTO
   │  │  │  ├─ 📂exception: 도메인 공통으로 발생하는 에러 처리 
   │  │  │  └─ 📂util: 날짜,시간과 장소 유틸
   │  │  └─ 📂domain: 컨텍스트 별로 도메인 설정
   │  │  ├─ 📂itinerary
   │  │  │  ├─ 📂dto: 요청과 응답 진행시 Entity를 사용하지 않고 Request, Response 사용
   │  │  │  │  └─ 📜ItineraryResponseFactory.java: 상속 관계의 자식 클래스 종류에 따른 Response 생성 
   │  │  │  ├─ 📂entity: Itinerary와 Lodgement, Movement, Stay는 상속 관계
   │  │  │  │  └─ 📜ItineraryFactory.java: 상속 관계의 자식 클래스 종류에 따른 Entity 생성 
   │  │  │  ├─ 📂type: 자식 클래스의 타입을 Enum으로 처리
   │  │  │  └─ 📂util: 여정 관련 공통 메소드
   │  │  ├─ 📂member: 여행과 여정을 가지는 멤버 컨텍스트 정의
   │  │      └─ 📂dto: 여정과 마찬가지로 Request와 Response에 따라 DTO 정리
   │  │  └─ 📂trip
   │  │      └─ 📂dto: 여정과 마찬가지로 Request와 Response에 따라 DTO 정리
   │  └─ 📂resources
   │     ├─ 📜application.yml
   │     ├─ 📜import.sql: 프로그램 실행과 동시에 기본 테이블 생성하는 SQL
   │     └─ 📜logback-spring.xml: 로그 기록 설정
   └─📂test
      └─ 📂toyproject: 비즈니스 코드의 테스트 코드 작성
      	  ├─ 📜ToyprojectApplicationTests.java
          ├─ 📂common
          ├─ 📂domain
          └─📂http_requests: HTTP로 여행과 여정의 기능 테스트
          ├─ 📂itinerary
          └─ 📂trip
 ```
 


### 기타 정보
> 내장 H2 URL : [localhost:8080/h2-console](http://localhost:8080/h2-console)













