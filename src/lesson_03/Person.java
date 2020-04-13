package lesson_03;


public class Person {
    private String name;
    private String surname;
    private Long phone;
    private String email;

    public Person(String name, String surname, Long phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public Long getPhone() {
        return phone;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
