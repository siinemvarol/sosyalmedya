# Burada kendimize ait ozel bir docker imaji olusturuyoruz.
# Ilk olarak olusturacagimiz sistem icin isletim sistemini seciyoruz
# Burada biz icinde isletim sistemi ve javaJDK17 olan surumu seciyoruz
FROM amazoncorretto:17.0.8-alpine3.18
# Kendi projemizi bu bilgisayarin icine kopyalamak icin islem yapiyoruz
COPY build/libs/api-gateway-service-1.0-SNAPSHOT.jar app.jar
# Son olarak uygulamamizin calismasi icin onu baslatiyoruz.
ENTRYPOINT ["java","-jar","/app.jar"]