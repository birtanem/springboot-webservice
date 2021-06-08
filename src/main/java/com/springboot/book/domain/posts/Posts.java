package com.springboot.book.domain.posts;

import com.springboot.book.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//----------------------
// 실제 DB 테이블과 매칭될 클래스 a.k.a Entity클래스
// jpa로 작업시 entity class를 통해 db에 접근한다.
@Getter
// entity에서 setter를 만들지 않는 이유.
// 무작정 getter/setter를 생성하면 해당 클래스의 인스턴스 값들이 언제 어디서 변하는지 코드상으로 명확하게 구분하기 어렵다.
// 수정해야 할 경우 복잡해진다.
// entity 클래스에서는 절대 setter를 만들지 않고, 해당 필드 값 변경이 필요하면
// 명확히 목적과 의도를 알 수 있는 메소드를 만든다.
// setter없이 db에 데이터 삽입은 생성자를 통해 이뤄지며 생성자 대신 @Builder 클래스를 사용하기도 한다.
@NoArgsConstructor //기본 생성자 자동추가.
@Entity //테이블과 링크되는 클래스임을 나타냄.
public class Posts extends BaseTimeEntity {

    @Id //해당 테이블의 PK값.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성규칙을 나타냄. GenerationType.IDENTITY를 추가해야 auto_increment가 적용된다.
    private Long Id;

    // Entity의 PK는 Long타입의 Auto_increment를 추천. (MYSQL기준 bigint타입)
    // 주민등록번호와 같은 컬럼이나, 여러키를 조합한 복합키로 pk잡기를 비추하는 이유
    // 1. FK를 맺을 때 다른 테이블에서 복잡한 키를 전부 갖고 있거나, 중간 테이블을 하나 더 둬야하는 상황이 발생할 수도.
    // 2. 인덱스에 좋지 않음
    // 3. 유니크한 조건이 변경 될 경우 PK 전체를 수정해아할 수도 있다.
    // => 주민등록번호, 복합키 등은 유니크로 별도로 추가를 권장.


    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타냄.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
