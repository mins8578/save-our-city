# Save Our City: 도시소멸 위기 알리미 앱 (1인 프로젝트)

> 인구 소멸 위험 지역을 알려주는 웹 애플리케이션  
> **도시 소멸 문제에 대한 인식 제고와 정보 제공을 목표로 합니다.**

---

## 프로젝트 개요

- **주제**: 인구 감소와 도시 소멸 위기에 처한 지역을 시각적으로 알리는 웹 서비스 개발
- **기간**: 2025.5.27 ~ 2025.6.18
- **기획 의도**: 사용자들에게 **도시 소멸 위험도를 직관적으로 전달**하고, **지역 활성화에 대한 관심을 유도**

**본 프로젝트는 JSP 활용 능력 향상을 목표로 진행한 1인 프로젝트입니다.**

---
## 기술 스택

| 분류 | 기술 |
|------|------|
| **Frontend** |<img src="https://img.shields.io/badge/JSP-E84E0F?style=for-the-badge&logo=logoColor=white"> <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"> |
| **Backend** | <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"> |
| **Database** | <img src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white"> |
| **Tool** | <img src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white">|

---

## 주요 기능

| 기능 | 설명 |
|------|------|
| 지표 시각화 | 강원도 지역별 소멸 위험도 지도화 |
| 지역 소멸 위험도 분석 | 강원도 소멸 위험 지역 Top3 정보 및 소멸 위험 분석 파일 제공 |
| 정책 정보 | 지역별 활성화 정책 및 지원 내용 제공 |
| 커뮤니티 | 유저 간 의견 공유 게시판 (익명 사용 가능) |

---
## ⚙️ 프로젝트 디렉터리 구조


```bash
WebServer-Project/
├── .gradle/
├── .idea/
├── build/
├── gradle/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org.example/
│   │   │       ├── config/
│   │   │       │   ├── AppConfig.java
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   └── WebConfig.java
│   │   │       ├── controller/
│   │   │       │   ├── ApiController.java
│   │   │       │   └── PageController.java
│   │   │       ├── domain/
│   │   │       │   ├── Idea.java
│   │   │       │   ├── Policy.java
│   │   │       │   └── Region.java
│   │   │       ├── dto/
│   │   │       │   ├── IdeaDTO.java
│   │   │       │   ├── PolicyDTO.java
│   │   │       │   └── RegionDTO.java
│   │   │       ├── repository/
│   │   │       │   ├── IdeaRepository.java
│   │   │       │   ├── PolicyRepository.java
│   │   │       │   └── RegionRepository.java
│   │   │       ├── service/
│   │   │       │   ├── IdeaService.java
│   │   │       │   ├── PolicyService.java
│   │   │       │   └── RegionService.java
│   │   │       ├── util/
│   │   │       │   └── PolicyCsvImporter.java
│   │   │       └── Main.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── main.css
│   │       │   └── js/
│   │       │       ├── gangwon-map.js
│   │       │       └── main.js
│   │       ├── application.yml
│   │       └── policy.csv
│   ├── test/
│   └── webapp/
│       └── WEB-INF/
│           └── views/
│               └── includes/
│                   ├── footer.jsp
│                   ├── header.jsp
│                   ├── ideas.jsp
│                   ├── main.jsp
│                   └── policies.jsp
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle
```
---
### 파트별 구성

| 프론트엔드 | 백엔드 | DB & infra |
|:--:|:--:|:--:|
| <img width="740" height="500" alt="Image" src="https://github.com/user-attachments/assets/cbda620d-c875-44ca-9574-af14a4ed2842" /> |<img width="700" height="400" alt="Image" src="https://github.com/user-attachments/assets/1ee92b6d-d529-43ff-ac12-cb675adbc9f9" /> | <img width="700" height="400" alt="Image" src="https://github.com/user-attachments/assets/09e552ec-cb71-4950-932f-f26079dfe9a3" /> |

---
## 시연 동영상
[![도시소멸 위기 알리미 앱](https://img.youtube.com/vi/QQN08lDmG3M/0.jpg)](https://www.youtube.com/watch?v=QQN08lDmG3M)
---

## 📷 시연 이미지

| 홈 화면 | 지역 소멸 지수 |
|:--:|:--:|
| <img width="400" height="500" alt="Image" src="https://github.com/user-attachments/assets/ee51340f-1c5d-4591-bb7e-a39de66612dd" /> |<img width="600" height="400" alt="Image" src="https://github.com/user-attachments/assets/fd066e6f-c20d-4622-aa5d-487dd7f8e96d" /> | 

| 강원도 통계 분석 자료 | 지역 소멸 지수 |
|:--:|:--:|
| <img width="550" height="200" alt="Image" src="https://github.com/user-attachments/assets/020b0d62-32b2-4205-92d4-2ef6bb2c7686" />  |<img width="500" height="400" alt="Image" src="https://github.com/user-attachments/assets/f84a20d8-c36b-4ade-8aa8-bbb6c14f64f1" />| 

- 전체 강원도 통계 보기 버튼을 누르면 강원도 내 소멸 위험도 분석 자료가 다운로드 됩니다.

| 지자체 정책 조회 | 각 지자체 정책 조회 사이트(예시: 고성군) |
|:--:|:--:|
| <img width="887" height="309" alt="Image" src="https://github.com/user-attachments/assets/9cc400cb-c819-48d3-84c1-a8266b313206" /> |<img width="800" height="802" alt="Image" src="https://github.com/user-attachments/assets/2d9b81a2-7f97-4055-9a16-13283f616ac1" /> | 

- 상세보기 버튼을 누르면 해당 지자체 사이트로 이동하여 정책에 대한 상세 내용을 확인할 수 있습니다.

| 아이디어 공유 게시판 | 익명을 사용한 등록/조회/삭제/수정 기능|
|:--:|:--:|
|<img width="587" height="199" alt="Image" src="https://github.com/user-attachments/assets/57a848dd-bd29-480a-b9bb-ca5fdacf4c72" /> |<img width="590" height="341" alt="Image" src="https://github.com/user-attachments/assets/49020486-881f-4d7c-b344-1bcac6e1995f" /> |

- 익명 기능을 적용하여, 글 작성 시 비밀번호를 입력하고 이후 해당 비밀번호로 본인 글을 수정하거나 삭제할 수 있습니다.
---
### 참고자료
- [NABIS 균형발전종합정보시스템](https://www.nabis.go.kr/)
- [고성군 홈페이지](https://www.gwgs.go.kr/)
- [영월군 홈페이지](https://www.yw.go.kr/www/index.do)
- [태백시 홈페이지](https://www.taebaek.go.kr/www/index.do)
- [카카오 맵 API](https://apis.map.kakao.com/)
- Chat gpt
- Claude ai

