# 依赖java8的运行环境镜像
FROM java:8
# 将jar包复制到容器内默认当前目录下
ADD eureka-server-1.0-SNAPSHOT.jar .
# 容器启动命令：java -jar [放入的jar包]
ENTRYPOINT ["java","-jar","eureka-server-1.0-SNAPSHOT.jar"]