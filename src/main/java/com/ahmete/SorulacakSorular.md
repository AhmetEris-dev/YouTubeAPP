video entity
yorum entity
kullanıcı entity
video entity
yorumcount
likecount
izlenmecount
disslikecount
populerlik endeksi

user profil
sabitlenmiş video
populer videolar 5 adet list<video>
tum videolar  list<video>
video model


like entity
videoGUI
userGUI

Login olmadan videolar arasında gezinebilecekmiş.
Ama bir videoya beğenmek veya yorum yapmak için mutlaka login olmalı.

Video -> Login -> Video redirecting.

Yorumlar düzenlebilir.
Silme işlemleri soft-delete olarak yapılmalı.
Dto'lar kullanılacak.

Kullanıcı profili buna bir model oluşturarak tutun.
Bir profile girildiğinde o profilin videolarını (List<Video>), user bilgilerini vs. hepsini çekip tek bir sayfada görüntüleyebilrsin.


Alperen hocaya
public Optional<Like> save(Like like) {
try {
likeRepository.save(like);
System.out.println(like.getVideoId() + " başarıyla kaydedildi.");
} catch (Exception e) {
System.out.println("Service Like kaydedilirken hata oluştu: " + e.getMessage());
}

		return Optional.ofNullable(like);
	}

burada neden videoID aldık diye sor

alperen hocaya  FutbolcuSaveRequestDto neden save ile isimlendirdik alperen hocaya sor 

videorequest ve videoresponse u alperen hocaya sor

service deki hangi metotların dto yapılacagını sor


UserController userController = new UserController();

//		Optional<UserResponseDto> dto =userController.save(new UserSaveRequestDto("Ahmet","Eriş","ahmete@gmail.com","ahmete","123456789"));
//		Optional<UserResponseDto> dto =userController.save(new UserSaveRequestDto("Emine","Karabolat","emink@gmail.com","eminek","123456789"));