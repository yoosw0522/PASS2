package ExploreLog2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            System.out.println("4. 파일에 기록 저장");
            System.out.println("5. 파일에서 기록 불러오기" );
            System.out.println("6. 종료" );
            System.out.print("선택 : " );

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

            else if (menu == 4) {
                saveLogs(logs);
            }

            else if (menu == 5) {
                loadLogs(logs);
            }

            // 프로그램 종료
            else if (menu == 6) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            else {
                System.out.println("잘못된 메뉴입니다.");
            }
        }

        scanner.close();
    }

    // 한 기록을 지역, 내용, 위험도 순서로 세 줄에 저장한다.
    public static void saveLogs(ArrayList<ExploreLog> logs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("explore_log.txt"))) {
            for (ExploreLog log : logs) {
                writer.write(log.getArea());
                writer.newLine();
                writer.write(log.getContent());
                writer.newLine();
                writer.write(String.valueOf(log.getDangerLevel()));
                writer.newLine();
            }
            System.out.println("파일에 기록을 저장했습니다.");
        } catch (IOException e) {
            System.out.println("파일을 저장하지 못했습니다: " + e.getMessage());
        }
    }

    public static void loadLogs(ArrayList<ExploreLog> logs) {
        ArrayList<ExploreLog> loadedLogs = new ArrayList<>();

        // try 괄호 안에서 연 파일은 읽기가 끝나면 자동으로 닫힌다.
        try (BufferedReader reader = new BufferedReader(new FileReader("explore_log.txt"))) {
            String area;
            while ((area = reader.readLine()) != null) {
                String content = reader.readLine();
                String dangerText = reader.readLine();
                if (content == null || dangerText == null) {
                    System.out.println("파일에 빠진 내용이 있습니다. 기존 기록을 유지합니다.");
                    return;
                }
                int dangerLevel = Integer.parseInt(dangerText);
                loadedLogs.add(new ExploreLog(area, content, dangerLevel));
            }

            // 전부 읽은 뒤에 교체해서 오류가 나도 기존 기록은 남겨 둔다.
            logs.clear();
            logs.addAll(loadedLogs);
            System.out.println("파일에서 기록을 불러왔습니다.");
        } catch (IOException e) {
            System.out.println("파일을 읽지 못했습니다. 기존 기록을 유지합니다.");
        } catch (NumberFormatException e) {
            System.out.println("파일의 위험도가 숫자가 아닙니다. 기존 기록을 유지합니다.");
        }
    }
}
