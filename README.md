# Board API Training

게시판 CRUD + 인증 REST API 프로젝트.
단순 구현에서 시작하여 단계적으로 리팩토링하며 기술적 깊이를 쌓아가는 학습 프로젝트입니다.

## Tech Stack

- Java 21
- Spring Boot 4.0.3
- Spring Data JPA
- H2 Database (개발)
- Gradle

## Phase 진행 현황

| Phase | 주제 | 상태 |
|-------|------|------|
| 1 | 최소 동작 구현 (CRUD, 세션 로그인, H2) | 진행 중 |
| 2 | 구조 리팩토링 (DTO, 예외 처리, Validation, BaseEntity) | |
| 3 | 인증/인가 고도화 (JWT, Spring Security, BCrypt) | |
| 4 | 테스트 + API 문서 (JUnit, MockMvc, REST Docs) | |
| 5 | JPA 깊이 + 동시성 (N+1, 락, 트랜잭션, 멱등성) | |
| 6 | 검색/페이징 최적화 (QueryDSL, 인덱스, Cursor 페이징) | |
| 7 | 캐싱 + AOP (Spring Cache, Redis, AOP 로깅) | |
| 8 | DB 전환 + 인프라 (MySQL, Docker, GitHub Actions) | |

## Getting Started

```bash
./gradlew bootRun
```

- API 확인: `http://localhost:8080/swagger-ui/index.html`
- H2 콘솔: `http://localhost:8080/h2-console`
