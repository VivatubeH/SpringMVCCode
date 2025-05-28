package hello.hello_spring.domain;

// 도메인 패키지를 별도로 만든 이유 -> 핵심 개체를 따로 보관하기 위해
// DB(테이블) 한 줄 <-> 인스턴스 한 개로 매핑된다.
// DB 한 줄과 1:1로 매핑되는 클래스 = 엔티티(Entity), 도메인 객체(Domain Object)
public class Member {

    // 필드는 private으로 선언해서 외부에서 직접 변경 못하도록
    // 캡슐화( 반드시 메서드를 통해서만 바꾸게 하는 원리 )
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
