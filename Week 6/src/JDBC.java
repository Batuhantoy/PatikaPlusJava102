import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class DBConnector{
    private Connection connect=null;
    public Connection connectDB(){
        try {
            this.connect = DriverManager.getConnection("jdbc:mysql://localhost/employees?username=root&password=mysql");
        } catch (SQLException e) {

        }
        return this.connect;
    }
    public static Connection getConnectionInstance(){
        DBConnector db = new DBConnector();
        return db.connectDB();
    }
}
class Employee{
    private int id;
    private String name;
    private String position;
    private int salary;

    public Employee(int id, String name, String position, int salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }
}
public class JDBC {
    public static void getEmployeeList(){
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Statement st = DBConnector.getConnectionInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees");
            while (rs.next()){
                employees.add(new Employee(rs.getInt("id"),rs.getString("name"),
                        rs.getString("position"),rs.getInt("salary")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for(Employee employee : employees){
            System.out.println("ID: "+employee.getId());
            System.out.println("İsim Soyisim: "+employee.getName());
            System.out.println("Pozisyon: "+employee.getPosition());
            System.out.println("Maaş: "+employee.getSalary());
            System.out.println();
            System.out.println();
        }
    }
    public static void addEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.print("İsim Soyisim: ");
        String name = sc.nextLine();
        System.out.print("Pozisyon : ");
        String position = sc.nextLine();
        System.out.print("Maaş : ");
        int salary = sc.nextInt();

        try(Connection connection = DBConnector.getConnectionInstance()){
            // id veritabanında otomatik ekleniyor
            PreparedStatement st = connection.prepareStatement("INSERT INTO employees (name,position,salary) VALUES (?,?,?)");
            st.setString(1,name);
            st.setString(2,position);
            st.setInt(3,salary);
            st.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
