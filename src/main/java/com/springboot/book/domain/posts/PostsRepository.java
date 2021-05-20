package com.springboot.book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// --- DAO라고 불리는 DB Layer접근자
// jpa에서는 Repository라고 부르며 인터페이스로 생성함.
// 인터페이스 생성 후 JpaRepository<Entity클래스, PK타입>을 상속하면 자동으로 CRUD가 생성된다.
// @Repository를 추가할 필요가 없다. Entity클래스와 기본 Entity Repository는 같은 위치에 둔다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
