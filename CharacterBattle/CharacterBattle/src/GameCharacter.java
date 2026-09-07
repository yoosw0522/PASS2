/*
 * 네 직업의 공통 정보를 가진 부모 클래스.
 * 자식은 체력 관리 기능을 물려받고 공격 메서드는 다르게 재정의한다.
 * 필드에 접근 제한자를 생략하여 같은 패키지에서 직접 접근할 수 있다.
 * private과 필요한 메서드로 바꾸는 것은 이후 개선할 부분이다.
 */
public class GameCharacter {
    // 이름은 문자열, 체력과 공격력은 정수로 저장한다.
    String name;
    int hp;
    // 회복할 때 처음 체력을 넘지 않도록 최대 체력도 보관한다.
    int maxHp;
    int attackPower;

    // 자식 생성자의 super(...)가 넘긴 값으로 공통 필드를 초기화한다.
    public GameCharacter(String name, int hp, int attackPower) {
        // this.name은 객체의 필드, 오른쪽 name은 매개변수이다.
        this.name = name;
        this.hp = hp;
        // 처음 전달받은 체력을 최대 체력으로 사용한다.
        this.maxHp = hp;
        this.attackPower = attackPower;
    }

    // int 반환형: 출력만 하는 void와 달리 계산 결과를 호출한 곳에 전달한다.
    // Main에서는 자식 객체의 재정의된 attack()이 실행된다.
    public int attack() {
        return attackPower;
    }

    // 부모에도 같은 이름의 특수 공격을 선언하여 공통 타입으로 호출할 수 있다.
    public int specialAttack() {
        return attackPower * 2;
    }

    // 반격 피해를 받아 현재 객체의 체력을 변경한다. 반환값은 없다.
    public void takeDamage(int damage) {
        // hp -= damage는 hp = hp - damage와 같은 뜻이다.
        hp -= damage;
        // 체력을 음수로 표시하지 않도록 최솟값을 0으로 맞춘다.
        if (hp < 0) {
            hp = 0;
        }
    }

    // 네 직업이 공통으로 사용하는 회복 메서드이다.
    public void heal() {
        // 물약 하나는 체력을 30 회복한다.
        hp += 30;
        // 최대 체력보다 커지면 최대 체력까지만 회복한다.
        if (hp > maxHp) {
            hp = maxHp;
        }
        // 실제 회복 후 체력을 표시하므로 적게 회복된 경우도 확인할 수 있다.
        System.out.println("회복 후 체력: " + hp + "/" + maxHp);
    }
}
