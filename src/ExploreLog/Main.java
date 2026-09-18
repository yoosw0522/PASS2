package ExploreLog;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 탐사 기록을 저장할 ArrayList
        ArrayList<ExploreLog> logs = new ArrayList<>();

        while (true) {

            System.out.println();
            System.out.println("===== 탐사 로그 관리 =====");
            System.out.println("1. 탐사 기록 추가");
            System.out.println("2. 전체 기록 확인");
            System.out.println("3. 탐사 기록 삭제");
            System.out.println("4. 종료");
            System.out.print("선택 : ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            // 탐사 기록 추가
            if (menu == 1) {

                System.out.print("탐사 지역 : ");
                String area = scanner.nextLine();

                System.out.print("발견 내용 : ");
                String content = scanner.nextLine();

                System.out.print("위험도 (1~5) : ");
                int dangerLevel = scanner.nextInt();
                scanner.nextLine();

                ExploreLog log =
                        new ExploreLog(area, content, dangerLevel);

                logs.add(log);

                System.out.println("탐사 기록이 추가되었습니다.");
            }

            // 전체 탐사 기록 출력
            else if (menu == 2) {

                if (logs.size() == 0) {
                    System.out.println("저장된 탐사 기록이 없습니다.");
                }

                else {
                    System.out.println();
                    System.out.println("===== 전체 탐사 기록 =====");

                    for (int i = 0; i < logs.size(); i++) {

                        System.out.println();
                        System.out.println((i + 1) + "번 기록");

                        logs.get(i).showInfo();
                    }
                }
            }

            // 탐사 기록 삭제
            else if (menu == 3) {

                if (logs.size() == 0) {
                    System.out.println("삭제할 탐사 기록이 없습니다.");
                }

                else {

                    System.out.println();
                    System.out.println("===== 탐사 기록 목록 =====");

                    for (int i = 0; i < logs.size(); i++) {
                        System.out.println(
                                (i + 1) + ". "
                                        + logs.get(i).getArea()
                                        + " / "
                                        + logs.get(i).getContent()
                        );
                    }

                    System.out.print("삭제할 기록 번호 : ");
                    int number = scanner.nextInt();
                    scanner.nextLine();

                    if (number >= 1 && number <= logs.size()) {
                        logs.remove(number - 1);

                        System.out.println("탐사 기록이 삭제되었습니다.");
                    }

                    else {
                        System.out.println("잘못된 번호입니다.");
                    }
                }
            }

            // 프로그램 종료
            else if (menu == 4) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            else {
                System.out.println("잘못된 메뉴입니다.");
            }
        }

        scanner.close();
    }
}