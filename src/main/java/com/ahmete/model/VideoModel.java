package com.ahmete.model;

import com.ahmete.controller.CommentController;
import com.ahmete.controller.LikeController;
import com.ahmete.controller.UserController;
import com.ahmete.controller.VideoController;
import com.ahmete.entity.Comment;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;

import java.util.List;

public class VideoModel {
	
	public CommentController commentController = new CommentController();
	public UserController userController = new UserController();
	public LikeController likeController = new LikeController();
	public VideoController videoController =new VideoController();
	
	private String username;
	private String title;
	private String description;
	private int viewCount;
	private int likeCount;
	private int commentCount;
	private int dislikeCount;
	private Long videoId;
	
	public VideoModel(User user, Video video) {
		this.username = user.getUsername();
		this.title = video.getTitle();
		this.description = video.getDescription();
		this.viewCount = video.getViewCount();
		this.likeCount = video.getLikeCount();
		this.commentCount = video.getCommentCount();
		this.dislikeCount = video.getDislikeCount();
		this.videoId = video.getId();
	}
	
	
	private void printDescriptionWithoutBreakingWords(String description, int lineLength) {
		String[] words = description.split(" ");
		StringBuilder currentLine = new StringBuilder();
		
		for (String word : words) {
			if (currentLine.length() + word.length() + 1 <= lineLength) {
				if (currentLine.length() > 0) {
					currentLine.append(" ");
				}
				currentLine.append(word);
			} else {
				System.out.println(currentLine.toString());
				currentLine = new StringBuilder(word);
			}
		}
		
		if (currentLine.length() > 0) {
			System.out.println(currentLine.toString());
		}
	}
	
	public List<Comment> getCommentsForVideo(Long videoId) {
		return commentController.findByIdComment(videoId);
	}
	
	public void displayVideo() {
		System.out.println("*************************************************");
		for (int i = 0; i < 10; i++) {
			System.out.println("*\t\t\t\t\t\t\t\t\t\t\t\t*");
		}
		System.out.println("*************************************************");
		
		// Başlık ve kullanıcı adı bilgilerini ekleyin
		System.out.println("Başlık: " + title);
		System.out.println("Kullanıcı: " + username);
		System.out.printf("\uD83D\uDC4D:%d \uD83D\uDC4E:%d \t📺:%d \t💬:%d\n", likeCount, dislikeCount, viewCount, commentCount);
		System.out.println("-------------------------------------------------");
		
		// Açıklamayı yazdır
		printDescriptionWithoutBreakingWords("Acıklama: "+description, 50);
		System.out.println("-------------------------------------------------");
		
		// Yorumları al ve yazdır
		List<Comment> comments = getCommentsForVideo(videoId);
		if (comments.isEmpty()) {
			System.out.println("Bu video için yorum bulunmamaktadır.");
		} else {
			comments.forEach(comment -> {
				User userCommented = userController.findById(comment.getUserId()).get();
				System.out.println(userCommented.getUsername() + ": " + comment.getCommentText());
			});
		}
		
		System.out.println();
	}
	
}