package edu.kh.collection.set.service;

import edu.kh.collection.list.dto.Student;

import java.util.*;

public class SetService {

    /**
     * Set(집합)
     * - 순서를 유지하지 않음 (인덱스X)
     * - 중복 데이터 저장 불가(같은 객체를 덮어 씌움)
     * - null도 1개만 저장 가능
     * - 대표적인 자식 클래스: HashSet, LinkedHashSet, TreeSet
     *   1) HashSet: hashCode()를 오버라이딩 한 객체만 저장 가능
     */

    public void ex1() {
        Set<String> set = new HashSet<>();

        // 순서유지 X
        set.add("네이버");
        set.add("카카오");
        set.add("라인");
        set.add("쿠팡");
        set.add("배달의민족");
        set.add("당근마켓");
        set.add("토스");
        set.add("직방");
        set.add("야놀자");

        // 중복저장 X
        set.add("야놀자");
        set.add("야놀자");
        set.add("야놀자");
        set.add("야놀자");
        set.add("야놀자");

        // null도 추가할 수 있지만 한 개만 가능(중복제거)
        set.add(null);
        set.add(null);
        set.add(null);
        set.add(null);

        System.out.println(set); // hashCode() 해시값 순서대로 출력

        // boolean remove(Object o)
        // - Set에 저장된 객체 중 같은 객체를 찾아서 제거
        // - return: 같은 객체가 있어서 제거되면 true / 아니면 false
        // - 같은 객체의 기준: hashCode() 같음 && equals() -> true
        if(set.remove("직방")) {
            System.out.println("직방이 제거되었습니다.");
        } else {
            System.out.println("직방이 존재하지 않습니다.");
        }
        System.out.println(set);

        if(set.remove("직방")) {
            System.out.println("직방이 제거되었습니다.");
        } else {
            System.out.println("직방이 존재하지 않습니다.");
        }
        System.out.println(set);

        System.out.println("---------------------------------------------------------------------");

        /* Set에 저장된 요소 하나씩 얻어오기 */
        // 1. Iterator (반복자): 컬렉션에서 제공하는 걸렉션 객체의 요소를 반복 접근하는 객체
        //   * 코드 설명에 iterator, iterable 단어가 포함되어 있다면 반복(순차)접근이 가능하다고 볼 수 있다.
        //   set 객체는 반복자 Iterator 객체가 붙어있다고 생각
        Iterator<String> it = set.iterator();
        while(it.hasNext()) { // 다음 꺼내올 객체가 존재하면 true
            String temp = it.next(); // 다음 객체를 꺼내옴
            System.out.println(temp);
        }

        System.out.println("---------------------------------------------------------------------");

        // 2. 향상된 for문 이용
        for(String s : set) {
            System.out.println(s);
        }
    }

    public void ex2() {
        // set이 저장된 객체가 중복임을 확인하는 밥법
        // eqauls()를 통해 필드 값이 같으면 같은 객체로 판단
        // -> equals() 오버라이딩 필수

        // hash가 붙은 Set/Map은 equals()가 true일 때 hashCode()도 같아야 동일한 객체로 취급한다.
        // => Hash가 붙은 Map/Set은 equals(), hashCode() 오버라이딩이 필수다.
        // hash가 붙은 이유: 속도 향상

        Set<Student> set = new HashSet<>();
        set.add(new Student("홍길동", 1, 2, 3, "서울시 어딘가", 'M', 50));
        set.add(new Student("홍길동", 1, 2, 3, "서울시 어딘가", 'M', 50));
        set.add(new Student("홍길동", 1, 2, 3, "서울시 어딘가", 'M', 50));
        set.add(new Student("홍길동", 1, 2, 3, "서울시 어딘가", 'M', 50));

        for(Student s : set) {
            System.out.println(s);
        }
    }

    public void createLotto() {
        // 로또 번호 5세트 만들어서 출력하기

        List<Set<Integer>> lottoList = new ArrayList<>();
        Random random = new Random();

        for(int i=0; i<5; i++) {
//            Set<Integer> set = new HashSet<>();
            Set<Integer> set = new TreeSet<>();

            while(set.size() < 6) // set에 저장된 데이터 객체가 6개 미만일때 수행
                set.add(random.nextInt(45) + 1); // 1 ~ 45
            lottoList.add(set); // 완성된 한 세트의 로또 번호를 리스트에 추가
        }

        for(Set<Integer> s : lottoList) {
            System.out.println(s);
        }
    }
}
