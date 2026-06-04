# 🏠 집사 서버 (Jipsa Server)

> 처음 집 구하는 사회초년생·신혼부부를 위한 부동산 거래 가이드 서비스 **집사**의 백엔드 서버입니다.

## 📌 관련 레포지토리
- 프론트엔드: [jipsa-client](https://github.com/jipsa-app/jipsa-client)

---

## 🛠 기술 스택

| 분류 | 기술 |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.6 |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security + JWT |
| Database | MySQL 8.0 |
| Build | Gradle |
| Docs | Swagger (SpringDoc OpenAPI) |
| Test | JUnit 5 + Mockito |

---

## 🗂 프로젝트 구조

```
src/main/java/com/jipsa/jipsaserver
├── auth/          # 회원가입, 로그인, JWT 인증
├── checklist/     # 체크리스트 저장 API
├── contract/      # 계약 일정 CRUD API
├── domain/        # JPA 엔티티 및 Repository
└── config/        # Security, CORS, Swagger, 예외처리
```

---

## ✨ 주요 기능

- **JWT 기반 인증** — 회원가입/로그인, Stateless 세션 관리
- **체크리스트 저장** — 전세사기 예방 체크리스트 및 가이드 진행상태 DB 저장
- **계약 일정 관리** — 계약일/잔금일/만료일 저장 및 D-day 계산
- **글로벌 예외처리** — `@RestControllerAdvice`로 통일된 에러 응답
- **API 문서화** — Swagger UI 자동 생성

---

## ⚙️ 환경변수 설정

```properties
DB_URL=jdbc:mysql://localhost:3306/jipsa_db?useSSL=false&serverTimezone=Asia/Seoul
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET=your_jwt_secret_key_at_least_32_characters
```

---

## 🚀 로컬 실행 방법

```bash
# 1. 레포지토리 클론
git clone https://github.com/jipsa-app/jipsa-server.git

# 2. 환경변수 설정 (IntelliJ Run Configurations → Environment Variables)

# 3. MySQL에 데이터베이스 생성
CREATE DATABASE jipsa_db;

# 4. 실행
./gradlew bootRun
```

---

## 📄 API 문서

서버 실행 후 아래 주소에서 확인할 수 있어요.

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 테스트 실행

```bash
./gradlew test
```

---

## 🔗 배포

- Backend: Railway
- Database: Railway MySQL
- 배포 URL: https://jipsa-server-production.up.railway.app
