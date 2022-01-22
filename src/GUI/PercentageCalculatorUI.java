package GUI;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import MainCode.PercentageCalc;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Hamzat Olowu
 */
public class PercentageCalculatorUI extends javax.swing.JFrame implements ActionListener{
    transient PercentageCalc pCalc;
    private JLabel head;
    private JLabel txtName;
    private JLabel txtScore;
    private JLabel txtTotal;
    private JLabel out;
    private JTextField name;
    private JTextField score;
    private JTextField total;
    private JButton calc;
    private JMenuBar mBar;
    private JMenu fMenu;
    private JMenuItem openItem;
    private JMenuItem exitItem;
    private static final String FONTSTYLE = "Lucida Sans";

    public PercentageCalculatorUI(){

        //initialize PercentageCalc constructor
        pCalc= new PercentageCalc();

        //JLabel for the header
        head = new JLabel("Percentage Calculator",SwingConstants.CENTER);
        head.setFont(new Font("Arial", Font.BOLD, 25));
        head.setPreferredSize(new Dimension(400,50));

        //JLabel for the Output
        out = new JLabel("",SwingConstants.CENTER);
        out.setVisible(false);

        //button to calculate output
        calc = new JButton("Calculate");
        calc.addActionListener(this);

        //---------------JLabels-----------------------------

        txtName = new JLabel("Name: ",SwingConstants.TRAILING);
        txtName.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));

        txtScore = new JLabel("Score: ",SwingConstants.CENTER);
        txtScore.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));

        txtTotal = new JLabel("Total:  ",SwingConstants.CENTER);
        txtTotal.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));

        //-------------------TextFields----------------------

        name = new JTextField();
        name.setPreferredSize(new Dimension(200,25));

        score = new JTextField();
        score.setPreferredSize(new Dimension(200,25));

        total = new JTextField();
        total.setPreferredSize(new Dimension(200,25));

        //------------------MenuBar---------------------

        mBar = new JMenuBar();
        fMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        exitItem = new JMenuItem("Exit");

        openItem.addActionListener(this);
        exitItem.addActionListener(this);

        fMenu.add(openItem);
        fMenu.add(exitItem);
        mBar.add(fMenu);

        //-------------------------JFrame----------------------------

        this.setJMenuBar(mBar);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600,450);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Percentage Calculator");
        this.setLayout(new FlowLayout(FlowLayout.CENTER,100,35));
        this.add(head);
        this.add(txtName);
        this.add(name);
        this.add(txtScore);
        this.add(score);
        this.add(txtTotal);
        this.add(total);
        this.add(calc);
    }

    //-----------------------------Action on Event Triggers-----------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == calc){
            
            pCalc.setName(name.getText());
            pCalc.setScore(Integer.parseInt(score.getText()));
            pCalc.setTotal(Integer.parseInt(total.getText()));

            out.setText(pCalc.getPercentage()+"%");
            out.setVisible(true);
            out.setFont(new Font(FONTSTYLE, Font.BOLD, 15));
            out.setPreferredSize(new Dimension(400,25));
            this.add(out);
            this.add(calc);
            this.revalidate();
            this.repaint();

            try {
                pCalc.saveToTxt(pCalc.getName(), pCalc.getTotal(), 
                                pCalc.getScore(), pCalc.getPercentage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == openItem){
            JFileChooser fChooser = new JFileChooser();
            FileNameExtensionFilter fNFilter = new FileNameExtensionFilter("Text Documents", "txt");
            fChooser.setFileFilter(fNFilter);

            int res = fChooser.showOpenDialog(null);

            if(res == JFileChooser.APPROVE_OPTION){
                File file = new File(fChooser.getSelectedFile().getAbsolutePath());
                try {
                    pCalc.readAndWrite(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        if(e.getSource() == exitItem){
            System.exit(0);
        }
    }
}
