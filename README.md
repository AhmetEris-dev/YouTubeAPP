# YotubeApp

## 1-Genel İçerik ve Notlar

### Projemiz Youtube programına benzer bir şekilde tasarlanmıştır.Projemizde temel olarak User,Video,Like ve Comment'ler bulunmaktadır
### İşlevsellik olarak kullanıcı kayıt sistmei,video kaydetme istemi,like atma,dislike atma,like geri cekme,yorum atma,yorum silme(soft delete),yorum duzenlemeler bulunmaktadır

## 2-Kullanılan Teknolojiler

### Bu Projede JDBC ve Postgresql veritabnı kullanılmıştır

## 3-Mimari

### * Bu projede katmanlı mimari yapısı kullanılmıştır 
### * database'imiz ile repository sınıflarımız bağlantı kuruyor
### * repository sınıflarımız ile o repository'e ait service sınıflarımız bağlantı kuruyor
### * service sınıflarımız ile o service'e ait controller sınıflarımız bağlantı kuruyor
### * controller sınıflarımızda son olarak gui sınıflarımız ile bağlantı kurmaktadır

## NOT: 

### service sınıflarımız kendi arasında bağlantı kurabilirler.Bir service sınıfı farklı türde bir repository veya controller ile bağlantı kuramaz.
### gui sınıflarımız kullanıcların gördüğü menulerin oldugu sınıftır.

## 4-Sınıflar ve Yapıları

### 1-Entity Package
### Entity sınıflarımız field'lar , constructor metotları , getter and setter ve toString bulunmaktadır

### 2-Repository Package
### Repository sınıflarımızın amacı sql sorguları ile database'den bilgi alışverişini sağlamaktadır
### Repository sınıflarımızda Utility package'ımız içindeki ICrud interface sınıfımızdaki metotlar implement edilmektedir
### bu sınıflarımızın içerisinde ortak olarak save,update,findById,delete ve findAll metotları kullanılmıştır
### ayrıca ortak metotlar dışında sadece o sınıfı ilgilendiren metotlar da yazılmıştır.

### 3-Service Package
### Service sınıflarımızın amacı verilerin düzenlenmesini sağlayan sınıflarımızdır.
### Service sınıflarımız repository sınıflarımız ile controller arasındaki bağlantıyı sağlar
### Service sınıflarımızın içerisinde dto'lar kullanılmaktadır

### 4-Controller Package
### Controller sınıfımız kullanıcıdan gelen veriyi kontrol eder Bu gelen veri doğruysa ilgili olan service sınıfına gönderir. Eğer bu veri o controller ile ilgili değilse veriyi kabul etmez.
### Controller sınıflarımız dışarıdan gelen veriyi ilk karşılayan sınıflarımızdır.Ayrıca içeriden cıkıcak olan veriyi en son kontrol eden sınıflarımızdır

### 5-DTO Package
### DTO sınıflarımızda kullanıcının görmesinin gerekli olmadığı bilgiler cıkartılıp görmesini istediğimiz verilerin tutulduğu sınıftır

### 6-GUI Package
### GUI sınıflarımızda sadece ilgili gui sınıfının menuleri tutulmaktadır

### 7-Model Package
### bu sınıflarımızda arayüze benzer kullandıgımız sınıflardır.Kullanıcıya göstermek istediğimiz verileri istediğimiz şekillerde gösterdiğimiz sınıflardır.

### 8-Utility Package
### Utility sınıflarımızda database connection sağladıgımız ve bu connection'ın acılıp kapanmasını denetlediğimiz sınıflarımız bulunmaktadır
### Ayrıca bu package'ın içerisinde DatabaseSchema sınıfımız bulunmaktadır bu sınıfımızda tablolarımızı oluşturdugumuz sınıfımızdır.

## 5-Veritabanı Tasarımı

### 1-Veri tabanımızda 4 tane tablomuz bulunmakta bu tablolar tbl_video,tbl_user,tbl_like ve tbl_comment'dir
### 2- tbl_video tablosunda id,user_id,title,description,viewcount,likecount,commentcount,dislikecount,state,createat ve updateat columnları bulunmaktadır. Bu tabla tbl_user tablosundan gelen user_id yi tutmaktadır 
### 3- tbl_user tablosunda id,name,surname,email,username,password,state,createat,updateat columnları bulunmaktadır
### 4- tbl-like tablosunda id,user_id,video_id,status,state,createat,updeateat columnları bulunmaktadır
### bu tablonun içerisinde tbl_video ve tbl_user tablolarının id'leri tutulmaktadır
### 5- tbl-comment tablosunda id,user_id,video_id,commenttext,status,state,createat,updeateat columnları bulunmaktadır
### bu tablonun içerisinde tbl_video ve tbl_user tablolarının id'leri tutulmaktadır

## 6-Fonksiyonel Özellikler

### Bu projede kullanıcı kayıt işlemi gercekleştirebilir,Video izleyebilir,Video paylaşabilir,Like atabilir,geri cekebilir ve dislike atabilir,Videolara yorum yapabilir,yorumunu duzenleyebilir ve yorumunu silebilir(soft delete)

## 7-Geliştirme Süreci
### Bu projeyi yaparken mimariyi oluşturmakta zorlandık,dto sınıflarında kullanıcıya göstermemiz gereken kısımları belirlemede sıkıntılar yaşadık Ayrıca ICrud'ımızdaki ortak metot'larımız butun ihtiyacımızı karşılamadı bu yuzden yeni metotlar yazdık. 