package MainCode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * Main Class For the Calculation
 * @author Hamzat Olowu
 */
public class PercentageCalc {
    private String name;
    private double total;
    private double score;

    /**
     * Constructor to initialize the fields
     */
    public PercentageCalc(){
        this.name = "";
        this.score = 0;
        this.total = 0;
    }

//------------Acess Modifiers-------------------

    /**
     * Setter for name 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for score
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Setter for total
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Getter for name
     * @return {@code name}
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for score
     * @return {@code score}
     */
    public double getScore() {
        return score;
    }

    /**
     * Getter for total
     * @return {@code total}
     */
    public double getTotal() {
        return total;
    }
    //#endregion

    /**
     * calculates the percentage
     * @return a number at 2 decimal places
     */
    public double getPercentage() {
        double p = (score * 100)/total;
        BigDecimal myDecimal = BigDecimal.valueOf(p).setScale(2, RoundingMode.HALF_UP);
        return myDecimal.doubleValue();
    }

    /**
     * Writes to a text file 
     * @param name
     * @param total
     * @param score
     * @param percent
     * @throws IOException
     */
    public void saveToTxt(String name, double total, double score, double percent ) throws IOException{
        String out = name+"\t"+score+"\t"+total+"\t"+percent+"%"+"\n";
        File file = new File("Calcution Results.txt");
        try (FileWriter fWriter = new FileWriter(file,true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);) {
            bWriter.write(out);
            bWriter.newLine();
        }
    }

    /**
     * Writes to a text file for {@code readAndWrite()}
     * @param file
     * @throws IOException
     */
    private void saveToTxt(File file) throws IOException{
        String out = this.getName()+"\t"+this.getScore()+"\t"+this.getTotal()+"\t"+this.getPercentage()+"%"+"\n";
        try (FileWriter fWriter = new FileWriter(file,true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);) {
            bWriter.write(out);
            bWriter.newLine();
        }
    }

    /**
     * Reads the file then write to a file using the private {@code saveToTxt()}
     * @param file
     * @throws IOException
     */
    public void readAndWrite(File file) throws IOException{
        try (Scanner input = new Scanner(file);){

            if(file.isFile()){
                while(input.hasNext()){
                    String nm = input.next();
                    double sc = input.nextDouble();
                    double ttal = input.nextDouble();

                    this.setName(nm);
                    this.setScore(sc);
                    this.setTotal(ttal);
                    saveToTxt(new File(file.getParent(), "Calculated_"+file.getName()));
                }
            }
        }
    }
}
