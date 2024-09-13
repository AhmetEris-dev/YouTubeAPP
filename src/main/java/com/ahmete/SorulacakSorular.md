video entity
yorum entity
kullanıcı entity
video entity
like entity
videoGUI
userGUI


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