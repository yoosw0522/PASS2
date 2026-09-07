// 궁수도 같은 공격 메서드 이름을 사용하므로 Main을 직업별로 나눌 필요가 없다.
public class Archer extends GameCharacter {
    public Archer() {
        super("궁수", 100, 20);
    }

    @Override
    public int attack() {
        // Math.random()은 0 이상 1 미만의 실수이다.
        // 6을 곱하고 int로 소수 부분을 버리면 0~5가 된다.
        int bonus = (int) (Math.random() * 6);
        // 기본 공격력 20에 보너스를 더해 20~25 피해를 준다.
        int damage = attackPower + bonus;
        System.out.println("활 공격! 피해량: " + damage);
        return damage;
    }

    @Override
    public int specialAttack() {
        // 연속 사격은 고정된 추가 피해 14를 사용한다.
        int damage = attackPower + 14;
        System.out.println("연속 사격! 피해량: " + damage);
        return damage;
    }
}
