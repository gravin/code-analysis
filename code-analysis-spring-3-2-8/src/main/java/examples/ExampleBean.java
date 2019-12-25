package examples;

public class ExampleBean {

    // Number of years to calculate the Ultimate Answer
    private String years;

    private String months;

    // The Answer to Life, the Universe, and Everything
    private String ultimateAnswer;


    public ExampleBean(double years, String ultimateAnswer) {
        System.out.println("constructor 1");
        this.years = String.valueOf(years) + "年";
        this.ultimateAnswer = ultimateAnswer;
    }


    private ExampleBean(String years, String ultimateAnswer) {
        System.out.println("constructor 2");
        this.years = years + "年";
        this.ultimateAnswer = ultimateAnswer;
    }



    @Override
    public String toString() {
        return "ExampleBean{" +
                "years=" + years +
                ", ultimateAnswer='" + ultimateAnswer + '\'' +
                '}';
    }
}