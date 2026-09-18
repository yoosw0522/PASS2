# PASS2

Java 자유주제 과제입니다. 하나의 IntelliJ Java 모듈에서 활동별 패키지를 사용합니다.

```text
PASS2
├── src
│   ├── CharacterBattle
│   ├── PokemonBattleStudy
│   └── ExploreLog
├── .gitignore
└── PASS2.iml
```

## IntelliJ 실행

1. PASS2 루트 폴더를 엽니다.
2. File > Project Structure > Project에서 설치된 JDK를 Project SDK로 선택합니다.
3. Modules에 PASS2가 없다면 + > Import Module에서 루트의 PASS2.iml을 선택합니다.
4. src가 Sources로 지정되었는지 확인합니다. PASS2.iml에 이 설정이 포함되어 있습니다.
5. Build > Build Project를 실행한 뒤 원하는 활동 폴더의 Main.java에서 main 옆 실행 버튼을 누릅니다.

실행 클래스는 CharacterBattle.Main, PokemonBattleStudy.Main, ExploreLog.Main입니다.
기존 활동별 실행 설정 대신 해당 Main에서 새로 실행합니다.

.idea와 out, 컴파일된 class 파일은 Git에 올리지 않습니다. 단일 모듈을 정의하는 루트 PASS2.iml만 공유합니다.
기존 Java 소스에는 이름 충돌을 피하기 위한 package 선언만 추가했으며 로직과 주석은 보존했습니다.

## 터미널 실행

PASS2 루트에서 실행합니다. JDK의 java와 javac가 PATH에 있어야 합니다.

```text
javac -encoding UTF-8 -d out src/CharacterBattle/*.java src/PokemonBattleStudy/*.java src/ExploreLog/*.java
java -cp out CharacterBattle.Main
java -cp out PokemonBattleStudy.Main
java -cp out ExploreLog.Main
```
