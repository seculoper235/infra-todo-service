# Infra Test API Server

Infra Development에 사용되는 투두리스트 서비스 API 입니다

---
## 관련 저장소
* **인프라**\
https://github.com/seculoper235/Kubernetes_Development


* **Frontend**\
https://github.com/seculoper235/infra-test-web
---
## 💡 관련 글
* [Hikari CP 란?](https://velog.io/@seculoper235/Database-HikariCP-%EB%9E%80)

* [로깅 시스템을 구축해보자 1편](https://velog.io/@seculoper235/Kubernetes-%EB%A1%9C%EA%B7%B8-%EA%B4%80%EB%A6%AC%EB%A5%BC-%ED%95%B4%EB%B3%B4%EC%9E%90-1%ED%8E%B8)

---
## 📝 backend Image 및 DB 서버
### Backend 이미지 생성
```shell
// 소스코드 빌드
gradle build

// 이미지 생성
docker build -t infra-backend:1.0 -f ./docker/Dockerfile .
```

### DB 서버 생성
```shell
// 컨테이너 생성
docker-compose -f ./docker/docker-compose.yml up -d
```
