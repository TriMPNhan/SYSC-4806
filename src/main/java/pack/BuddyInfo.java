package pack;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BuddyInfo {

    @Id
    private String name;
    private String addr;
    private String num;

    public  BuddyInfo(){
        name = "Default Bob";
        addr = "Default 123 Ave.";
        num = "111";
    }

    public BuddyInfo (String name, String addr, String num){
        this.name = name;
        this.addr = addr;
        this.num = num;
    }

    public String toString(){
        return name + " \n" + addr + " \n" + num + " \n\n";
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAddr() {
        return addr;
    }


    public void setAddr(String add) {
        this.addr = add;
    }


    public String getNum() {
        return num;
    }


    public void setNum(String num) {
        this.num = num;
    }
}
