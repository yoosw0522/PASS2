import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;

        System.out.println("===== 포켓몬 배틀 =====");

        // playAgain이 true인 동안 게임을 다시 시작할 수 있게 만들었다.
        while (playAgain) {

            // 아직 Collection을 배우는 중이라 이번 과제에서는 ArrayList를 사용하지 않았다.
            // 대신 Pokemon 객체를 직접 만들고 배열에 담아서 선택할 수 있게 했다.
            Pokemon[] pokemons = createPokemons();

            System.out.println();
            System.out.println("사용할 포켓몬을 선택하세요.");

            showPokemonList(pokemons);
            int myChoice = inputPokemonNumber(scanner, pokemons.length);

            Pokemon myPokemon = pokemons[myChoice - 1];

            System.out.println();
            System.out.println("상대 포켓몬을 선택하세요.");
            showPokemonList(pokemons);

            int enemyChoice;

            // 자기 자신과 싸우는 상황은 제외했다.
            while (true) {
                enemyChoice = inputPokemonNumber(scanner, pokemons.length);

                if (enemyChoice != myChoice) {
                    break;
                }

                System.out.println("같은 포켓몬은 선택할 수 없습니다. 다시 선택하세요.");
            }

            Pokemon enemyPokemon = pokemons[enemyChoice - 1];

            System.out.println();
            System.out.println(myPokemon.getName() + " VS " + enemyPokemon.getName());
            System.out.println("===== 배틀 시작 =====");

            // 둘 중 한 포켓몬의 HP가 0이 될 때까지 반복한다.
            while (myPokemon.isAlive() && enemyPokemon.isAlive()) {

                System.out.println();
                System.out.println("1. 일반 공격");
                System.out.println("2. 특수 기술 (" + myPokemon.getSkillName()
                        + ", 남은 횟수 " + myPokemon.getSkillCount() + ")");
                System.out.println("3. 회복 (남은 횟수 " + myPokemon.getHealCount() + ")");
                System.out.println("4. 상태 확인");
                System.out.print("선택 : ");

                int menu = inputMenu(scanner);

                if (menu == 1) {
                    myPokemon.attack(enemyPokemon);

                } else if (menu == 2) {
                    // 기술 횟수가 없을 경우 상대 턴으로 넘어가지 않도록 먼저 확인했다.
                    if (myPokemon.getSkillCount() <= 0) {
                        System.out.println("특수 기술을 더 이상 사용할 수 없습니다.");
                        continue;
                    }

                    myPokemon.useSkill(enemyPokemon);

                } else if (menu == 3) {
                    // 회복을 할 수 없는 상태라면 턴을 사용하지 않도록 했다.
                    if (myPokemon.getHealCount() <= 0) {
                        System.out.println("회복 횟수를 모두 사용했습니다.");
                        continue;
                    }

                    myPokemon.heal();

                } else {
                    System.out.println();
                    System.out.println("[내 포켓몬]");
                    myPokemon.showStatus();

                    System.out.println("[상대 포켓몬]");
                    enemyPokemon.showStatus();

                    // 상태 확인은 공격 행동이 아니므로 상대 턴으로 넘기지 않는다.
                    continue;
                }

                // 내 공격으로 상대 HP가 0이 되었다면 상대는 공격하지 못한다.
                if (!enemyPokemon.isAlive()) {
                    break;
                }

                System.out.println();
                System.out.println("===== 상대 턴 =====");

                // 상대 포켓몬은 Random을 사용해서 일반 공격과 특수 기술 중 하나를 선택한다.
                int enemyAction = random.nextInt(2);

                if (enemyAction == 0 || enemyPokemon.getSkillCount() <= 0) {
                    enemyPokemon.attack(myPokemon);
                } else {
                    enemyPokemon.useSkill(myPokemon);
                }
            }

            System.out.println();
            System.out.println("===== 배틀 종료 =====");

            if (myPokemon.isAlive()) {
                System.out.println(myPokemon.getName() + " 승리!");
            } else {
                System.out.println(enemyPokemon.getName() + " 승리!");
            }

            System.out.println();
            System.out.println("1. 다시 하기");
            System.out.println("2. 종료");
            System.out.print("선택 : ");

            int restart = inputRestart(scanner);

            if (restart == 2) {
                playAgain = false;
            }
        }

        scanner.close();

        System.out.println();
        System.out.println("게임을 종료합니다.");
    }

    // Pokemon 클래스로 여러 객체를 만든다.
    // 같은 클래스에서 만들어도 각 객체는 서로 다른 이름, HP, 공격력 등을 가질 수 있다.
    public static Pokemon[] createPokemons() {

        Pokemon pikachu =
                new Pokemon("피카츄", "전기", 100, 18, "10만볼트", 32);

        Pokemon charmander =
                new Pokemon("파이리", "불", 105, 19, "불꽃세례", 31);

        Pokemon squirtle =
                new Pokemon("꼬부기", "물", 115, 16, "물대포", 30);

        Pokemon bulbasaur =
                new Pokemon("이상해씨", "풀", 110, 17, "덩굴채찍", 30);

        return new Pokemon[]{
                pikachu,
                charmander,
                squirtle,
                bulbasaur
        };
    }

    public static void showPokemonList(Pokemon[] pokemons) {
        for (int i = 0; i < pokemons.length; i++) {
            System.out.println((i + 1) + ". "
                    + pokemons[i].getName()
                    + " (" + pokemons[i].getType() + ")");
        }
    }

    // 잘못된 숫자를 입력했을 때 프로그램이 바로 끝나지 않도록 입력 검사를 넣었다.
    public static int inputPokemonNumber(Scanner scanner, int max) {

        while (true) {
            System.out.print("선택 : ");

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();

                if (number >= 1 && number <= max) {
                    return number;
                }
            } else {
                scanner.next();
            }

            System.out.println("1부터 " + max + " 사이의 숫자를 입력하세요.");
        }
    }

    public static int inputMenu(Scanner scanner) {

        while (true) {
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();

                if (menu >= 1 && menu <= 4) {
                    return menu;
                }
            } else {
                scanner.next();
            }

            System.out.print("1~4 중에서 선택하세요 : ");
        }
    }

    public static int inputRestart(Scanner scanner) {

        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();

                if (number == 1 || number == 2) {
                    return number;
                }
            } else {
                scanner.next();
            }

            System.out.print("1 또는 2를 입력하세요 : ");
        }
    }
}
