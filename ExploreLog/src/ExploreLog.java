public class ExploreLog {
    private String area;
    private String content;
    private int dangerLevel;

    public ExploreLog(String area, String content, int dangerLevel) {
        this.area = area;
        this.content = content;
        this.dangerLevel = dangerLevel;
    }

    public String getArea() {
        return area;
    }

    public String getContent() {
        return content;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void showInfo() {
        System.out.println("탐사 지역 : " + area);
        System.out.println("발견 내용 : " + content);
        System.out.println("위험도 : " + dangerLevel);
    }
}