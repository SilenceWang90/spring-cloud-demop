# 依赖java8的运行环境镜像
FROM openjdk:8
# 维护人/作者
MAINTAINER wangpeng0717@sina.cn
# 创建目录/home/wp_software/
RUN /home/wp_software/
# 将jar包复制到创建的/home/wp_software/目录下，并重命名为eureka-server.jar
ADD eureka-server-1.0-SNAPSHOT.jar /home/wp_software/eureka-server.jar
# 容器启动命令：java -jar [放入的jar包]
ENTRYPOINT ["java","-jar","/home/wp_software/eureka-server.jar"]