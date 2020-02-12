package pack;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Entity
public class AddressBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    @OneToMany(cascade=CascadeType.MERGE, fetch= FetchType.EAGER)
    private List<BuddyInfo> collection;

    @Autowired
    public AddressBook() {
        this.name = "";
        this.collection = new ArrayList<BuddyInfo>();
    }

    public AddressBook(String name){
        this.name = name;
        this.collection = new ArrayList<BuddyInfo>();
    }

    public List<BuddyInfo> getCollection(){
        return collection;
    }


    public void addBuddy(BuddyInfo Bud) {
        if(Bud != null) {
            collection.add(Bud);
        }
    }

    public void removeBuddy(String name) {
        int i = 0;
        int r = -1;

        Iterator<BuddyInfo> it = collection.iterator();
        while(it.hasNext()){
            if (it.next().getName().equals(name)){
                r = i;
            }
            else{
                i++;
            }
        }

        if (r != -1){
            collection.remove(r);
        }
    }

    public BuddyInfo getBuddy(int i){
        if(i >= 0 && i < collection.size()) {
            return collection.get(i);
        }
        return null;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < collection.size(); i++){
            result += collection.get(i).toString();
        }
        return result;
    }

    public List<BuddyInfo> getBuds(){
        return collection;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub.
        AddressBook book = new AddressBook();
        System.out.println("Address Book\n-----------------------------------");

        BuddyInfo Duncan = new BuddyInfo("Duncan", "1234 Potato Ave.", "911");
        BuddyInfo Vijay = new BuddyInfo("Vijay", "1375 Heron Rd.", "611");
        book.addBuddy(Duncan);
        book.addBuddy(Vijay);

        System.out.println(book);
    }
}

