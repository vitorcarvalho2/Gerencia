package java.user.usecases.post.find;

import java.util.Date;

public record FindPostOutputDto(String id,String title,String post,String author,Date date) {

}