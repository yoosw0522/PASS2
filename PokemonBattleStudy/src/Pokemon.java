public class Pokemon {

    // 한 포켓몬이 가지고 있어야 하는 정보들을 필드로 만들었다.
    // private를 사용해서 다른 클래스에서 값을 마음대로 바꾸지 못하게 했다.
    private String name;
    private String type;
    private int hp;
    private int maxHp;
    private int attackPower;
    private String skillName;
    private int skillPower;
    private int skillCount;
    private int healCount;

    // 생성자를 사용하면 객체를 만들 때 필요한 값을 한 번에 넣을 수 있다.
    // this는 매개변수와 필드의 이름이 같을 때 현재 객체의 필드를 구분하기 위해 사용했다.
    public Pokemon(String name, String type, int hp, int attackPower,
                   String skillName, int skillPower) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.skillName = skillName;
        this.skillPower = skillPower;

        // 특수 기술과 회복은 너무 많이 사용하지 못하도록 횟수를 정했다.
        this.skillCount = 2;
        this.healCount = 2;
    }

    // 일반 공격 메서드
    // enemy도 Pokemon 객체이기 때문에 공격할 상대를 매개변수로 받을 수 있다.
    public void attack(Pokemon enemy) {
        int damage = attackPower;

        System.out.println();
        System.out.println(name + "의 일반 공격!");
        enemy.receiveDamage(damage);
    }

    // 각 포켓몬의 고유 기술을 사용하는 메서드
    public void useSkill(Pokemon enemy) {
        if (skillCount <= 0) {
            System.out.println("특수 기술을 더 이상 사용할 수 없습니다.");
            return;
        }

        skillCount--;

        // 타입 상성을 확인해서 기술 데미지를 계산한다.
        double multiplier = getTypeMultiplier(enemy);
        int damage = (int) (skillPower * multiplier);

        System.out.println();
        System.out.println(name + "의 " + skillName + "!");

        if (multiplier > 1.0) {
            System.out.println("효과가 굉장했다!");
        } else if (multiplier < 1.0) {
            System.out.println("효과가 별로인 것 같다.");
        }

        enemy.receiveDamage(damage);
    }

    // 상대에게 공격을 받았을 때 HP를 줄이는 기능을 따로 메서드로 만들었다.
    // 이렇게 하면 Main에서 hp 값을 직접 수정하지 않아도 된다.
    private void receiveDamage(int damage) {
        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }

        System.out.println(name + "이(가) " + damage + "의 데미지를 받았습니다.");
        System.out.println(name + "의 남은 HP : " + hp + " / " + maxHp);
    }

    // 체력을 회복하는 메서드
    public void heal() {
        if (healCount <= 0) {
            System.out.println("회복 횟수를 모두 사용했습니다.");
            return;
        }

        if (hp == maxHp) {
            System.out.println("이미 HP가 가득 차 있습니다.");
            return;
        }

        int healAmount = 30;
        hp += healAmount;

        if (hp > maxHp) {
            hp = maxHp;
        }

        healCount--;

        System.out.println();
        System.out.println(name + "이(가) 체력을 회복했습니다.");
        System.out.println("현재 HP : " + hp + " / " + maxHp);
        System.out.println("남은 회복 횟수 : " + healCount);
    }

    // 현재 포켓몬의 상태를 출력하는 메서드
    public void showStatus() {
        System.out.println("--------------------");
        System.out.println("이름 : " + name);
        System.out.println("타입 : " + type);
        System.out.println("HP : " + hp + " / " + maxHp);
        System.out.println("공격력 : " + attackPower);
        System.out.println("특수 기술 : " + skillName);
        System.out.println("남은 특수 기술 횟수 : " + skillCount);
        System.out.println("남은 회복 횟수 : " + healCount);
        System.out.println("--------------------");
    }

    // HP가 0보다 큰지 확인해서 전투 가능한 상태인지 반환한다.
    public boolean isAlive() {
        return hp > 0;
    }

    // 간단한 타입 상성
    // 피카츄는 전기 타입으로 설정해서 꼬부기에게 강하도록 만들었다.
    private double getTypeMultiplier(Pokemon enemy) {
        String enemyType = enemy.getType();

        if (type.equals("불") && enemyType.equals("풀")) {
            return 1.5;
        } else if (type.equals("물") && enemyType.equals("불")) {
            return 1.5;
        } else if (type.equals("풀") && enemyType.equals("물")) {
            return 1.5;
        } else if (type.equals("전기") && enemyType.equals("물")) {
            return 1.5;
        }

        if (type.equals("불") && enemyType.equals("물")) {
            return 0.7;
        } else if (type.equals("물") && enemyType.equals("풀")) {
            return 0.7;
        } else if (type.equals("풀") && enemyType.equals("불")) {
            return 0.7;
        }

        return 1.0;
    }

    // private 필드 값을 다른 클래스에서 읽을 필요가 있어서 getter를 사용했다.
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public String getSkillName() {
        return skillName;
    }

    public int getSkillCount() {
        return skillCount;
    }

    public int getHealCount() {
        return healCount;
    }
}
