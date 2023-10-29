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

1. 모듈링 & 객체 지향 설계: 계층 별 변수와 메소드 명 통일
2. [코드 리뷰 적극적으로 진행](https://github.com/FC-BE-ToyProject-Team6/KDT_Y_BE_Toy_Project2_DEV/pulls?q=is%3Apr+is%3Aclosed)
3. DDD 패키지 구성: DDD 클래스 별로 역할 분담 & 최종 리팩터링 진행
4. API 문서: Java Docs

- - -

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
 

- [프로젝트 관련 진행했던 이슈](https://github.com/FC-BE-ToyProject-Team6/KDT_Y_BE_Toy_Project2_DEV/issues?q=is%3Aissue+is%3Aclosed)
- - -

### 기타 정보
> 내장 H2 URL : [localhost:8080/h2-console](http://localhost:8080/h2-console)













