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