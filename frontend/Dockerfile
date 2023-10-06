# nginx 이미지 pull
FROM nginx
# app 디렉토리 생성
WORKDIR /app
# workdir에 build 폴더 생성 /app/build
RUN mkdir ./build
# build에서 build 폴더로 이동
ADD ./build ./build
# nginx의 기본 설정을 삭제
RUN rm -rf /etc/nginx/nginx.conf
# nginx 설정 파일 복사
COPY ./react-nginx.conf /etc/nginx/nginx.conf
# 80포트 오픈하고 nginx 실행
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]