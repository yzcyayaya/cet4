FROM java:8

# 镜像
MAINTAINER ylzlcl@163.com

# 拷贝java的二进制包
COPY ./target/cet4-0.0.1-SNAPSHOT.jar /app.jar

# 端口
EXPOSE 5000
# 运行
ENTRYPOINT [ "java","-Dspring.profiles.active=prod","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar" ]