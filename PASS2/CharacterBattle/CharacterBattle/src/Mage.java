// 전사와 같은 부모를 상속하지만 별도의 자식 클래스이다.
public class Mage extends GameCharacter {
    public Mage() {
        // 마법사는 체력이 낮은 대신 기본 공격력이 높다.
        super("마법사", 85, 22);
    }

    @Override
    public int attack() {
        // 마법사의 일반 공격은 기본 공격력 그대로이다.
        int damage = attackPower;
        System.out.println("마법 화살! 피해량: " + damage);
        return damage;
    }

    @Override
    public int specialAttack() {
        // *는 곱셈이다. 기본 공격력의 두 배를 반환한다.
        int damage = attackPower * 2;
        System.out.println("화염구! 피해량: " + damage);
        return damage;
    }
}
