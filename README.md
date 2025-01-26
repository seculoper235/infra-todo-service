# Infra Test API Server

Infra Development 레포지토리에 필요한 Backend 서버 소스코드 입니다

---
## 관련 저장소
* **인프라**\
https://github.com/seculoper235/Kubernetes_Development


* **Frontend**\
https://github.com/seculoper235/infra-test-web

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
