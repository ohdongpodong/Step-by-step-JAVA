package Chapter06_Object_Oriented_Programming2;
import java.util.Vector;

class Product7 {
    int price;      // 제품의 가격
    int bonusPoint; // 제품구매 시 제공하는 보너스 점수

    Product7(int price){
        this.price = price;
        bonusPoint = (int)(price/10.0);
    }

    Product7() {
        price = 0;
        bonusPoint = 0;
    }
}

class Tv7 extends Product7{
    Tv7() { super(100); }
    public String toString() { return "Tv"; }
}

class Computer7 extends Product7 {
    Computer7() { super(200); }
    public String toString() { return "Computer"; }
}

class Audio7 extends Product7 {
    Audio7() { super(50); }
    public String toString() { return "Audio"; }
}

class Buyer7 {       // 고객, 물건을 사는 사람
    int money = 1000;   // 소유금액
    int bonusPoint = 0; // 보너스점수
    Vector item = new Vector();     // 구입한 제품을 저장하는데 사용될 Vector객체

    void buy7(Product7 p) {
        if(money < p.price) {
            System.out.println("잔액이 부족하여 물건을 살 수 없습니다.");
            return;
        }
        money -= p.price;           // 가진 돈에서 구입한 제품의 가격을 뺀다.
        bonusPoint += p.bonusPoint; // 제품의 보너스 점수를 추가한다.
        item.add(p);                // 구입한 제품을 Vector에 저장한다.
        System.out.println(p + "을/를 구입하셨습니다.");
    }

    void refund7(Product7 p) {
        if(item.remove(p)) {    // 제품을 Vector에서 제거한다.
            money += p.price;
            bonusPoint -= p.bonusPoint;
            System.out.println(p + "을/를 반품하셨습니다.");
        } else {  // 제거에 실패한 경우
            System.out.println("구입하신 제품 중 해당 제품이 없습니다.");
       }
    }

    void summary7() {        // 구매한 물품에 대한 정보를 요약해서 보여준다.
        int sum = 0;        // 구입한 물품의 가격합계
        String itemList = "";  // 구입한 물품 목록

        if(item.isEmpty()) {  // Vector가 비어있는지 확인한다.
            System.out.println("구입하신 제품이 없습니다.");
            return;
        }

        // 반복문을 이용해서 구입한 물품의 총 가격과 목록을 만든다.
        for(int i = 0; i < item.size(); i++) {
            Product p = (Product)item.get(i);
            sum += p.price;
            itemList += (i==0) ? "" + p : ", " + p;
        }
        System.out.println("구입하신 물품의 총금액은 " + sum + "만원입니다.");
        System.out.println("구입하신 제품은 " + itemList + " 입니다.");
    }
}

public class PolyArgumentTest3 {
    public static void main(String[] args) {
        Buyer7 b = new Buyer7();
        Tv7 tv = new Tv7();
        Computer7 com = new Computer7();
        Audio7 audio = new Audio7();

        b.buy7(tv);
        b.buy7(com);
        b.buy7(audio);
        b.summary7();
        System.out.println();
        b.refund7(com);
        b.summary7();

    }
}
