package j.user.utils;

import j.user.domain.post.gateway.PostGateway;
import j.user.infra.repositories.post.memory.PostRepositoryMemory;

public class PostRepositoryUtils {
	private static final PostGateway postGateway = PostRepositoryMemory.create();
	
	public static PostGateway getPostGateway() {
		return postGateway;
	}
}
