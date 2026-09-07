// 도적은 조건문과 난수를 이용해 일반 공격의 내용을 다르게 구현한다.
public class Rogue extends GameCharacter {
    public Rogue() {
        super("도적", 90, 16);
    }

    @Override
    public int attack() {
        // 처음에는 기본 공격력을 피해량으로 정한다.
        int damage = attackPower;
        // 난수가 0.3 미만이면 치명타이다. 약 30% 확률이라는 의미이다.
        if (Math.random() < 0.3) {
            // *= 2는 현재 damage에 2를 곱해 다시 저장한다는 뜻이다.
            damage *= 2;
            System.out.println("치명타!");
        }
        // 치명타 여부에 따라 16 또는 32를 출력하고 반환한다.
        System.out.println("단검 공격! 피해량: " + damage);
        return damage;
    }

    @Override
    public int specialAttack() {
        int damage = attackPower * 2;
        System.out.println("기습! 피해량: " + damage);
        return damage;
    }
}
