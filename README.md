# 선착순 쿠폰 이벤트 개발 API
이 프로젝트는 [여기어때-선착순 쿠폰 이벤트 개발 구조](https://www.codenary.co.kr/architecture/list?detail_id=84)에서 아이디어를 얻어 시작된 토이 프로젝트입니다.

## 기본 요구사항
1. 이벤트 기간 동안, 매일 특정 시간 오픈하며 총지급 수량을 한정한다.
   1. 이 프로젝트에서는 특정 시간 오픈은 제어하지 않는다.
2. 쿠폰의 지급 수량은 당일 정해진 양을 초과해서는 안된다.
3. 쿠폰은 1인당 1장만 지급한다.

## 동시성 제어
- Redisson을 사용한 분산락 적용

## Skills
- Kotlin
- Spring Boot 2.7
- Spring Data JPA
- Spring Data Redis
- Spring Security
- QueryDSL
- Ktlint
- JUnit5

## 문제점
- JPA - bulk insert
  - 이벤트를 생성하고 쿠폰 생성을 할 때 bulk insert를 할 수 없다.
    - id 채번 방식이 auto_increment일 경우 불가능

## Contributor
kuu2002@naver.com
