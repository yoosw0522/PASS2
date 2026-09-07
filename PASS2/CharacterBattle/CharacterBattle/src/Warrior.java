// extends로 부모의 필드와 체력 관리 메서드를 물려받는다.
public class Warrior extends GameCharacter {
    // 매개변수가 없는 전사 생성자. 전사의 초기 능력치를 부모에게 넘긴다.
    public Warrior() {
        // 이름, 체력, 기본 공격력 순서이다. 부모 생성자를 먼저 호출한다.
        super("전사", 120, 18);
    }

    // 부모와 이름·매개변수가 같은 메서드를 다시 작성하는 오버라이딩이다.
    @Override
    public int attack() {
        // damage는 이 메서드에서만 쓰는 지역 변수이다. 기본 공격력은 바꾸지 않는다.
        int damage = attackPower + 3;
        System.out.println("검 공격! 피해량: " + damage);
        // Main이 이 값을 받아 몬스터 체력에서 뺀다.
        return damage;
    }

    @Override
    public int specialAttack() {
        // 전사의 강타는 기본 공격력에 18을 더한다.
        int damage = attackPower + 18;
        System.out.println("강타! 피해량: " + damage);
        return damage;
    }
}
