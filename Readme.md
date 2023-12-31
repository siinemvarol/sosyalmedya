# UYGULAMA GEREKSINIMLERI VE ACIKLAMALAR

## 1. Gereksinimler
    - docker mongodb kurulum komutlari
    docker run -d -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=root -p 27017:27017 mongo
    - mongoDB yonetim tool'u indirme linki
    https://www.mongodb.com/try/download/compass
    https://downloads.mongodb.com/compass/mongodb-compass-1.38.2-win32-x64.exe
    - mongoDB compass tool acildiktan sonra
    * Advanced Connection Options'a tiklayip acin
    * Host kismina baglanti yapilacak bilgisayar ve mongoDB'nin calistigi portu yazin
        localhost:27017
    * Authentication kismina tiklayin ve burada Username/Password secenegini secin
    * Username kismina admin yazin ve password kismina root yazin neden boyle yaziyoruz,
    cunku mongoDB'yi docker uzerinde calistirirken username ve password bilgisini bu sekilde girmistik.
    * Authentication database kismini bos birakiyoruz. Nedeni ise, yonetici kullanicisinin 
    herhangi bir DB'ye baglanmasi gerekmez, tum sistemi yonetebilir. Ancak eger bir kullanici ekler
    ve ona bir DB tanimlar iseniz, o zaman bu kisma giris yapmaniz gereklidir.
    * Connect butonuna tiklayin ve baglantiyi saglayin.
    * Acilan pencerede + butonuna basarak yeni bir DB ekleyin, DB adi ve ornek bir collection adi
    girmeniz yeterlidir.
    *** DIKKAT: burdan itibaren olusturdugumuz DB icin onu yonetecek bir kullanici olusturacagiz.
    * Ilk olarak mongoDB compass'in sol alt kosesinde bulunan MONGOSH'a tiklayip console ekranini
    aciyoruz. Burda uzerinde islem yapmak istedigimiz DB'nin adi ile birlikte;
    - use <DB ADI> yazıp enter a basiyoruz.
    * Ilgili DB'ye gectikten sonra;
    - db.createUser({user:"bilgeadmin",pwd:"bilge33**",roles:["readWrite","dbAdmin"]})
    * Artik bu kullanici ile bu veritabanina baglanabilir ve islem yapabiliriz.

# RabbitMQ icin islemler
    - Docker'a kurulum icin gerekli yapilandirmalari yapalim. Onemli mutlaka portlar eklenmeli (5672 ve 15672)
    docker run -d --name bilgejava8-rabbit -e RABBITMQ_DEFAULT_USER=bilgejava8 -e RABBITMQ_DEFAULT_PASS=bilgeadmin* -p 5672:5672 -p 15672:15672 rabbitmq:3-management
    - RabbitMQ yonetim arayuzune erismek icin asagidaki linki kullanabilirsiniz
    http://localhost:15672/
    - RabbitMQ'ya java icinden baglanmak icin asagidaki linki kullanabilirsiniz.
    http://localhost:5672/

# PROJENIN DEPLOY ADIMLARI
    1 - Uygulamanin gradle ile build edilmesi gereklidir.
        1.1 - Sag taraftan gradle sekmesine tiklayin
        1.2 - Coklu yapi oldugu icin projenin adini secin "config-server-git"
        1.3 - Tasks>build>build cift tiklayip projeyi olusturun
        1.4 - Tasks>build>buildDependents cift tiklayin
    2 - Bu islemden sonra proje dosyasinin altinda olusan build klasorunun icindeki
        libs klasorunun icine projenizin jar dosyasi eklenmis olur. Bu dosya
        direkt calistirilabilir bir dosyadir.
    3 - Dockerfile olusturuyoruz.
    4 - console (Terminal) ekranindan bu dockerfile ile imaji olusturuyoruz.
        docker build -t configservergit .       -> DIKKAT: bu imaji buluta atmak icin, repo adini kullanin
        docker build -t siinemvarol/java8configservergit:v01 .
    5 - docker build -t siinemvarol/java8authservice:v01 .
    6 - docker build -t siinemvarol/java8userservice:v01 .
        docker build -t siinemvarol/java8gatewayservice:v01 .

