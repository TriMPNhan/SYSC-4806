package pack;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AddressBookController {
    private ABRepository repository;


    @Autowired
    public AddressBookController(ABRepository repository){
        this.repository = repository;
    }

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("addressBook", new AddressBook());
        return "home";
    }

    @PostMapping("/AddressBook")
    public String createBook(@RequestParam(name = "name") String name, Model model){
        AddressBook book = new AddressBook(name);
        repository.save(book);
        return displayBook(name, model);
    }

    @GetMapping("/AddressBook")
    public  String displayBook(@RequestParam(name = "name") String name, Model model){
        AddressBook book = repository.findByName(name).get(0);
        model.addAttribute("addressBook", book);
        model.addAttribute("buddyInfo", new BuddyInfo());
        return "showBuddies";
    }

    @PostMapping("/Buddy")
    public String addBuddy(@RequestParam(name="addrName") String addrName, @ModelAttribute BuddyInfo buddyInfo, Model model) {
        AddressBook a = repository.findByName(addrName).get(0);
        a.addBuddy(buddyInfo);
        repository.save(a);
        return displayBook(addrName, model);

    }

    @DeleteMapping("/Buddy")
    public String removeBuddy(@RequestParam(name="addrName") String addrName, int i, Model model) {
        AddressBook a = repository.findByName(addrName).get(0);
        a.removeBuddy(i);
        repository.save(a);
        return displayBook(addrName,model);

    }
}