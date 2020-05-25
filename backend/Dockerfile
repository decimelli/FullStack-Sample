FROM maven:3.6.3-openjdk-14-slim AS build
LABEL stage=build

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM open-liberty:kernel
LABEL stage=run

COPY --chown=1001:0 src/main/liberty/config /config/
COPY --from=build /home/app/target/backend.war /config/apps/

RUN configure.sh