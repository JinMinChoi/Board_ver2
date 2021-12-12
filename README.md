# Board_ver2

# Api

---

## User

| METHOD | URL             | EXPLANATION    |
| ------ | --------------- | -------------- |
| POST   | /users/signup   | 회원가입       |
| POST   | /users/login    | 로그인         |
| GET    | /users/{userId} | 단일 회원 조회 |
| GET    | /users          | 전체 회원 조회 |
| PUT    | /users/{userId} | 회원 정보 수정 |
| DELETE | /users/{userId} | 회원 삭제      |

<br/>

## User - Board

| METHOD | URL                        | EXPLANATION                |
| ------ | -------------------------- | -------------------------- |
| POST   | /boards/{userId}/write     | 유저가 게시물 등록         |
| GET    | /boards                    | 전체 게시물 조회           |
| GET    | /boards/{category}         | 카테고리별 게시물 조회     |
| PUT    | /boards/{userId}/{boardId} | 게시물 수정                |
| DELETE | /boards/{userId}/{boardId} | 게시물 삭제                |
| GET    | /boards/{userId}           | 유저가 쓴 게시물 전체 조회 |

<br/>

## User - Comment - Board

| METHOD | URL                               | EXPLANATION           |
| ------ | --------------------------------- | --------------------- |
| POST   | /comment/{userId}/{boardId}/write | 댓글 작성             |
| GET    | /comment/{boardId}                | 게시물 전체 댓글 조회 |
| PUT    | /comment/{userId}/{boardId}       | 댓글 수정             |
| DELETE | /comment/{userId}/{boardId}/write | 댓글 삭제             |
