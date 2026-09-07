// Java 기본 제공 입력 클래스. 외부 라이브러리가 필요 없다.
import java.util.Scanner;

public class Main {
    // 다음 개선 과제: 직업 선택과 전투를 각각 메서드로 나누면 main의 흐름이 짧아진다.
    // 프로그램 시작점. static이므로 Main 객체를 만들지 않고 실행한다.
    // void는 반환값 없음, args는 실행 인수이며 여기서는 사용하지 않는다.
    public static void main(String[] args) {
        // 키보드 입력을 읽을 Scanner 객체를 만들고 sc에 참조를 저장한다.
        Scanner sc = new Scanner(System.in);
        // [추가 학습: 다형성] 부모 타입 변수로 네 종류의 자식 객체를 다룬다.
        GameCharacter player;

        // 올바른 직업을 고를 때까지 반복한다.
        while (true) {
            System.out.println("\n1. 전사  2. 마법사  3. 궁수  4. 도적  0. 종료");
            System.out.print("직업 선택: ");
            // 입력이 끝나면 Scanner를 닫고 main을 종료한다.
            if (!sc.hasNextLine()) {
                sc.close();
                return;
            }
            // 한 줄을 문자열로 읽어 숫자 변환 없이 메뉴 글자를 비교한다.
            String job = sc.nextLine();
            // equals는 문자열 내용을 비교한다. ==와 구분한다.
            if (job.equals("1")) {
                // new가 자식 생성자를 실행하고, 그 안의 super가 부모를 초기화한다.
                player = new Warrior();
            } else if (job.equals("2")) {
                player = new Mage();
            } else if (job.equals("3")) {
                player = new Archer();
            } else if (job.equals("4")) {
                player = new Rogue();
            } else if (job.equals("0")) {
                sc.close();
                // return은 main을 끝내므로 아래 전투 코드도 실행하지 않는다.
                return;
            } else {
                System.out.println("메뉴에 있는 번호를 입력해 주세요.");
                // 이번 반복의 나머지를 건너뛰고 직업 메뉴로 돌아간다.
                continue;
            }
            // 객체를 만들었어도 아직 전투를 시작하지 않고 능력치를 먼저 보여준다.
            System.out.println("\n===== " + player.name + " 능력치 =====");
            // 선택한 객체의 필드를 읽는다. 확인만 하므로 체력이나 공격력은 변하지 않는다.
            System.out.println("체력: " + player.hp + "/" + player.maxHp);
            System.out.println("기본 공격력: " + player.attackPower);
            // 기본 공격력과 실제 피해량은 다를 수 있어 공격 특징도 따로 안내한다.
            // 개선 과제: 아래 설명의 숫자와 자식 클래스의 계산식을 따로 수정해야 한다.
            // 공격 수치를 바꿀 때 설명도 함께 확인해야 하며, 이후 직업별 설명 메서드로 옮길 수 있다.
            // 미리보기에서 attack()을 호출하면 공격 메시지와 난수가 실행되므로 설명만 출력한다.
            if (job.equals("1")) {
                System.out.println("일반 공격: 기본 공격력 + 3 (21 피해)");
                System.out.println("특수 공격: 강타, 기본 공격력 + 18 (36 피해)");
            } else if (job.equals("2")) {
                System.out.println("일반 공격: 기본 공격력 그대로 (22 피해)");
                System.out.println("특수 공격: 화염구, 기본 공격력의 2배 (44 피해)");
            } else if (job.equals("3")) {
                System.out.println("일반 공격: 무작위 추가 피해 0~5 (20~25 피해)");
                System.out.println("특수 공격: 연속 사격, 기본 공격력 + 14 (34 피해)");
            } else {
                System.out.println("일반 공격: 16 피해, 30% 확률로 치명타 32 피해");
                System.out.println("특수 공격: 기습, 기본 공격력의 2배 (32 피해)");
            }
            System.out.println("공통: 특수 공격 2회 / 물약 2개 / 물약은 체력 30 회복");

            // false는 아직 확정하지 않았다는 뜻이다. 뒤로 가면 그대로 false를 유지한다.
            boolean confirmed = false;
            // 잘못 입력했을 때는 직업 선택으로 돌아가지 않고 확인 메뉴를 다시 보여준다.
            while (true) {
                System.out.println("1. 확정하고 전투 시작  2. 직업 다시 선택  0. 종료");
                System.out.print("선택: ");
                // 능력치 화면에서 입력이 끝난 경우에도 전투를 시작하지 않고 종료한다.
                if (!sc.hasNextLine()) {
                    sc.close();
                    return;
                }
                String confirm = sc.nextLine();
                if (confirm.equals("1")) {
                    confirmed = true;
                    // 이 break는 가장 가까운 반복문인 확인 메뉴만 끝낸다.
                    break;
                } else if (confirm.equals("2")) {
                    break;
                } else if (confirm.equals("0")) {
                    sc.close();
                    return;
                } else {
                    System.out.println("메뉴에 있는 번호를 입력해 주세요.");
                }
            }
            // 확정했을 때만 바깥의 직업 선택 반복도 끝내고 아래 전투 코드로 넘어간다.
            // 다시 선택했다면 바깥 while이 반복되며 다른 직업 객체를 새로 만든다.
            if (confirmed) {
                break;
            }
        }

        // 전투 정보는 Main의 지역 변수로 관리한다. 몬스터 클래스는 만들지 않았다.
        int monsterHp = 120;
        int potions = 2;
        int specials = 2;
        // 도망이나 입력 종료를 승리·패배와 구분하기 위한 변수이다.
        boolean escaped = false;
        System.out.println(player.name + " 선택! 몬스터와 전투를 시작합니다.");

        // &&는 AND. 캐릭터와 몬스터가 모두 살아 있을 때만 한 턴을 진행한다.
        while (player.hp > 0 && monsterHp > 0) {
            // +로 문자열과 현재 값을 연결해서 표시한다.
            System.out.println("\n내 체력: " + player.hp + "/" + player.maxHp);
            System.out.println("몬스터 체력: " + monsterHp);
            System.out.println("특수 공격: " + specials + "회 / 물약: " + potions + "개");
            System.out.println("1. 공격  2. 특수 공격  3. 회복  0. 도망");
            System.out.print("행동 선택: ");
            if (!sc.hasNextLine()) {
                escaped = true;
                break;
            }
            String action = sc.nextLine();
            // 회복처럼 공격하지 않는 행동은 피해량이 0이다.
            int damage = 0;

            if (action.equals("1")) {
                // 같은 호출이지만 실제 객체에 맞는 자식의 attack()을 실행한다.
                // 메서드가 return한 정수를 damage에 저장한다.
                damage = player.attack();
            } else if (action.equals("2")) {
                // 횟수가 없으면 턴을 쓰지 않고 다시 행동을 고른다.
                if (specials == 0) {
                    System.out.println("특수 공격을 모두 사용했습니다.");
                    continue;
                }
                // 특수 공격도 자식이 재정의한 메서드를 실행한다.
                damage = player.specialAttack();
                // --는 현재 값에서 1을 뺀다.
                specials--;
            } else if (action.equals("3")) {
                if (potions == 0) {
                    System.out.println("물약이 없습니다.");
                    continue;
                }
                // 체력이 가득 차 있으면 물약과 턴을 소비하지 않는다.
                if (player.hp == player.maxHp) {
                    System.out.println("이미 최대 체력입니다.");
                    continue;
                }
                // 회복은 네 직업이 부모의 같은 메서드를 사용한다.
                player.heal();
                potions--;
            } else if (action.equals("0")) {
                escaped = true;
                break;
            } else {
                System.out.println("메뉴에 있는 번호를 입력해 주세요.");
                // 잘못된 입력에는 몬스터가 반격하지 않는다.
                continue;
            }

            // 공격 메서드가 계산한 값을 전투의 상태 변경에 실제로 사용한다.
            monsterHp -= damage;
            // 처치했다면 즉시 전투를 끝내서 죽은 몬스터가 반격하지 않게 한다.
            if (monsterHp <= 0) {
                monsterHp = 0;
                break;
            }

            // 0~7을 만든 뒤 14를 더해 14~21의 반격 피해를 만든다.
            int enemyDamage = (int) (Math.random() * 8) + 14;
            System.out.println("몬스터 반격! 피해량: " + enemyDamage);
            // 부모에게 물려받은 메서드가 캐릭터의 hp를 변경한다.
            player.takeDamage(enemyDamage);
        }

        // 전투 반복이 끝난 이유를 상태 값으로 구분한다.
        if (escaped) {
            System.out.println("전투를 중단했습니다.");
        } else if (monsterHp == 0) {
            System.out.println("승리! 몬스터를 쓰러뜨렸습니다.");
        } else {
            System.out.println("패배! 체력이 모두 소진되었습니다.");
        }
        System.out.println("남은 체력: " + player.hp + "/" + player.maxHp);
        // 프로그램을 끝내기 전에 입력 자원을 닫는다.
        sc.close();
    }
}
