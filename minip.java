import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

class minip extends JFrame implements ActionListener {
    JFrame f1, f2;
    JLabel name, food, address, mobile, head;
    JTextField namef, foodf, addressf, mobilef;
    JButton submit;

    JLabel n, r, d, c;

    minip() {
        f1 = new JFrame("Order your food");
        f1.setSize(450, 450);
        f1.setLocationRelativeTo(null);
        f1.setLayout(null);
        f1.getContentPane().setBackground(Color.CYAN);

        head = new JLabel("Order your Food");
        head.setBounds(140, 50, 200, 25);
        head.setFont(new Font("SANS_SERIF", Font.BOLD, 22));
        head.setForeground(Color.BLACK);
        f1.add(head);

        name = new JLabel("Name:");
        name.setBounds(50, 110, 50, 20);
        f1.add(name);

        food = new JLabel("Enter food: ");
        food.setBounds(50, 150, 100, 20);
        f1.add(food);

        address = new JLabel("Address:");
        address.setBounds(50, 190, 80, 20);
        f1.add(address);

        mobile = new JLabel("Mobile no.:");
        mobile.setBounds(50, 230, 70, 20);
        f1.add(mobile);

        namef = new JTextField();
        namef.setBounds(100, 110, 100, 20);
        f1.add(namef);

        foodf = new JTextField();
        foodf.setBounds(150, 150, 100, 20);
        f1.add(foodf);

        addressf = new JTextField();
        addressf.setBounds(130, 190, 100, 20);
        f1.add(addressf);

        mobilef = new JTextField();
        mobilef.setBounds(130, 230, 100, 20);
        f1.add(mobilef);

        submit = new JButton("Place Order");
        submit.setBounds(50, 310, 120, 40);
        f1.add(submit);
        submit.addActionListener(this);

        f1.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String named = namef.getText();
            String foodd = foodf.getText();
            String addressd = addressf.getText();
            String mobiled = mobilef.getText();
            try {

                Connection conn = null;
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/food1", "root", "");

                System.out.println("Database is connected !");

                Statement stmt = conn.createStatement();
                String sql = "insert into food(name,food,address,mobile) values('" + named
                        + "','" + foodd + "','" + addressd + "','" + mobiled + "')";
                stmt.executeUpdate(sql);

                conn.close();
            } catch (Exception ex) {
                System.out.print("Do not connect to DB - Error:" + ex);
            }

            f2 = new JFrame("Order");
            f2.setSize(300, 300);
            f2.setLocationRelativeTo(null);
            f2.setLayout(null);

            n = new JLabel("Name: " + named);
            n.setBounds(50, 60, 150, 20);
            f2.add(n);

            r = new JLabel("Food : " + foodd);
            r.setBounds(50, 100, 150, 20);
            f2.add(r);

            d = new JLabel("Address: " + addressd);
            d.setBounds(50, 140, 150, 20);
            f2.add(d);

            c = new JLabel("Mobile no.: " + mobiled);
            c.setBounds(50, 180, 180, 20);
            f2.add(c);

            f1.setVisible(false);
            f2.setVisible(true);

        }
    }

    public static void main(String[] args) {
        minip obj = new minip();
    }
}