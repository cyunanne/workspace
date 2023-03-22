package edu.kh.collection.map.service;

import java.util.*;

public class MapService {
    /**
     * Map
     * - 특정 키워드(key)를 입력하면 상세한 값(value)이 나온다
     * - [key:value] 형태의 데이터를 모아둔 컬렉션 객체
     * - Key만 모아서 봤을 때 Set의 특징을 가짐(중복X, 순서유지X)
     * - Value만 모아서 봤을 때 Key로 값이 구분되기 때문에 중복허용(List의 특징을 지님)
     */
    public void ex1() {
        Map<Integer, String> map = new HashMap<>(); // Key(Integer):Value(String)

        // V put(K k, V v): Map에 요소 추가
        map.put(1, "김밥");
        map.put(2, "라면");
        map.put(3, "떡볶이");
        map.put(4, "돈까스");
        System.out.println(map);

        // key가 중복되는 경우 중복저장 불가 -> 덮어씌움
        map.put(4, "치즈돈까스");
        System.out.println(map);

        // V get(K k): key에 해당하는 value 반환
        System.out.println(map.get(3));

        // Map에서 키만 묶어 보면 Set의 특징을 갖는다 + 향상된 for문
        // => map에 저장된 모든 객체의 value만 출력하기
        // Set<T> keySet(): key만 모아서 Set 형태로 반환
        Set<Integer> keys = map.keySet();
        for(Integer k : keys) {
            System.out.println(k + " : " + map.get(k));
        }
    }

    public void ex2() {
        // Map은 언제 사용하면 좋을까?
        // 1) 한 번에 다량의 데이터를 전달해야 하는 경우 + 데이터의 명확한 구분이 필요한 경우
        // 2) DTO(Data Transfer Object): 값 전달용 객체
        //    - 재사용성이 적은 DTO를 대체하는 경우
        // 3) 별도의 DTO가 없을 경우

//        Map<String, Object> member = new HashMap<>();
        Map<String, Object> member = new LinkedHashMap<>(); // 순서가 유지되는 HashMap

        // Value가 Object타입
        // -> Object는 모든 클래스의 최상위 부모
        //   == 모든 객체의 부모 타입의 참조변수로 사용 가능(다형성)
        //   == value에 어떤 객체든 작성 가능

        // member에 값 추가
        member.put("memberId", "mem01");
        member.put("memberPw", "pass01");
        member.put("memberName", "테스트1");
        member.put("memberAge", 23); // auto-boxing => Integer 타입으로 저장
        member.put("memberGeder", 'M'); // auto-boxing => Character 타입으로 저장

        Scanner scanner = new Scanner(System.in);
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("PW: ");
        String pw = scanner.nextLine();

        // id, pw가 member와 모두 일치하면 member 정보 출력
        if(member.get("memberId").equals(id)) {
            // value가 Object타입이기 때문에 equals()는 Object의 코드와 연결됨 -- 정적 바인딩
            // 하지만 프로그램 수행 시 실제 참조하는 객체의 타입 String의 equals()로 연결됨 -- 동적 바인딩

            if(member.get("memberPw").equals(pw)) {
                for(String key : member.keySet())
                    System.out.println(key + " : " + member.get(key));
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        } else {
            System.out.println("아이디가 일치하지 않습니다.");
        }
    }
}
