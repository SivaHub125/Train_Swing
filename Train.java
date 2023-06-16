import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class passenger_details{
    String name,dob,berth;
    char gender;
}
public class Train {
    static int x = 550;//label movement
    static String berths[] = {"select","Upper","Middle","Lower"};
    static int available[] = {1,1,1};
    static String date[] = new String[32];
    static String year[] = new String[100];
    static String month[] = {"Month","January","February","March","April","May","June","July","August","September","October","November","December"};
    public static void main(String args[]){
        JFrame frame = new JFrame("Tickets");
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JLabel title = new JLabel("BOOKING TRAIN TICKETS");
        frame.add(title);
        title.setBounds(x,20,200,20);
        new Thread(new Runnable(){
            public void run(){
                while(x > -200){
                    if(x == -199){
                        x = 550;
                    }
                    title.setBounds(x-1,20,200,20);
                    x--;
                    try{
                        Thread.sleep(10);
                    }
                    catch(Exception e){}
                }
            }
        }).start();
        date[0] = "Date";
        year[0] = "Year";
        for(int i=1;i<=31;i++){
            date[i] = Integer.toString(i);
        }
        for(int i=1;i<=99;i++){
            year[i] = Integer.toString(1923+i);
        }

        JLabel namelab = new JLabel("Name:");
        JLabel berthlab = new JLabel("Berth Prefrence:");
        JTextField nametext = new JTextField();
        JComboBox<String> berthcom = new JComboBox<String>(berths);
        JLabel genderlab = new JLabel("Gender:");
        JRadioButton r1=new JRadioButton("Male");    
        JRadioButton r2=new JRadioButton("Female");
        JLabel doblab = new JLabel("Date of birth:");

        JComboBox<String> datebox = new JComboBox<String>(date);
        JComboBox<String> monthbox = new JComboBox<String>(month);
        JComboBox<String> yearbox = new JComboBox<String>(year);

        ButtonGroup bg=new ButtonGroup();    
        bg.add(r1);bg.add(r2);    
        frame.add(namelab);frame.add(nametext);frame.add(berthlab);frame.add(berthcom);
        frame.add(genderlab);frame.add(r1);frame.add(r2);frame.add(doblab);frame.add(datebox);
        frame.add(monthbox);frame.add(yearbox);

        namelab.setBounds(30,50,40,20);
        nametext.setBounds(80,50,100,20);
        doblab.setBounds(30,80,100,20);
        datebox.setBounds(140,80,50,20);
        monthbox.setBounds(200,80,100,20);
        yearbox.setBounds(310,80,50,20);
        berthlab.setBounds(30,110,100,20);
        berthcom.setBounds(140,110,100,20); 
        genderlab.setBounds(30,140,50,20);   
        r1.setBounds(90,140,100,20);    
        r2.setBounds(200,140,100,20);  
        
        JLabel l1 = new JLabel();
        frame.add(l1);
        l1.setBounds(200,210,200,30);

        JButton btn = new JButton("Submit");
        frame.add(btn);
        btn.setBounds(200,170,100,20);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = nametext.getText();
                String berthop = berthcom.getSelectedItem().toString();
                int sb = berthcom.getSelectedIndex()-1;
                char g;
                String date = datebox.getSelectedItem().toString();
                String year = yearbox.getSelectedItem().toString();
                String month = monthbox.getSelectedItem().toString();
                if(r1.isSelected()){
                    g = 'M';
                }
                else if(r2.isSelected()){
                    g = 'F';
                }
                else{
                    g = '0';
                }
                try{
                    int da = Integer.parseInt(date);
                    int ye = Integer.parseInt(year);
                    if(available[sb] > 0){
                        if(name.strip().length() == 0 || berthop == "select" || g == '0' || month == "select"){
                            l1.setText("Enter valid details");
                        }
                        else{
                            l1.setText(name + " booking confirmed");
                            available[sb]--;
                        }
                    }
                    else{
                        l1.setText("All "+berthop+" filled");
                    }
                }
                catch(Exception ex){
                    l1.setText("Enter valid details");
                }
                finally{
                    nametext.setText("");
                    berthcom.setSelectedIndex(0);
                    System.out.println("Available:Upper "+available[0]);
                    System.out.println("Available:Middle "+available[1]);
                    System.out.println("Available:Lower "+available[2]);
                    yearbox.setSelectedIndex(0);datebox.setSelectedIndex(0);
                    monthbox.setSelectedIndex(0);
                }
            }
        });
    }  
}
