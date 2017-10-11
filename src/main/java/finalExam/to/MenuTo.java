package finalExam.to;

import finalExam.HasId;

import java.time.LocalDate;

public class MenuTo implements HasId{

    private Integer id;
    private String name;
    private  String address;
    private LocalDate date;
    private Double priceTotal;

    public MenuTo(){
    }

    public MenuTo(Integer id, String name, String address, LocalDate date, Double priceTotal) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.date = date;
        this.priceTotal = priceTotal;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    @Override
    public boolean isNew() {
        return id==null;
    }

    @Override
    public void setId(Integer id) {
        this.id =id;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", priceTotal=" + priceTotal +
                '}';
    }
}
