class BradMold:
    category = "크림빵"

    # 생성자
    def __init__(self, category, price):
        self.category = category
        self.price = price

    def make_bread(self):
        print(self.category + "을 만들어냅니다.")

    # 객체 출력내용 지정
    def __str__(self):
        return "{}의 가격은 {}원 입니다.".format(self.category, self.price)

    # 소멸자 : reference counter가 0이 되면 자동으로 호출됨
    def __del__(self):
        print(self.category + "빵이 없어졌습니다.")

#bread = BradMold()
#bread.price = 3000

#bread_choco = BradMold()
#bread_choco.category = "초코크림"

# 참조연산자 .format()
#print("{}의 가격은 {}입니다.".format(bread.category, bread.price))
#print(bread_choco.category)

# 메소드 사용하기
# - 메소드 : 함수 내부에 정의된 함수
# - 메소드 호출 시 자동으로 자기 자신 객체가 매개변수로 전달된다.
# - 메소드 정의할 때 최소 한개 이상의 매개변수를 정의해야 한다.
#bread.make_bread() # 크림빵을 만들어냅니다.

# isinstance()
number = 1
a = 1.0
print(isinstance(number, int)) # True
print(isinstance(1, int)) # True
print(isinstance(a, int)) # False

###############################################################

# 상속
# super, 부모
# sub, 자식

# 부모 클래스 정의
class ParentRestaurant:
    price = 15000

    def __init__(self, name, menu, recipe):
        self.name = name
        self.menu = menu
        self.recipe = recipe
    
    def __str__(self):
        return f"가게이름 : {self.name}, 메뉴 : {self.menu}, 조리법: {self.recipe}"
    
    def __del__(self):
        pass

# 자식 클래스 정의
class ChildRestaurant(ParentRestaurant):
    price = 20000
    
    def __init__(self, name, menu, recipe, marketing):
        ParentRestaurant.__init__(self, name, menu, recipe)
        self.marketing = marketing
    
    def __str__(self):
        return super().__str__() + f", 마케팅 : {self.marketing}"

restaurant = ChildRestaurant("빵가게", "붕어빵", "붕어빵을 굽는다.", "온라인")
print(restaurant)
print(restaurant.price)

print(issubclass(ChildRestaurant, ParentRestaurant)) # True
print(issubclass(ParentRestaurant, ChildRestaurant)) # False