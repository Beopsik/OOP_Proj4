package Component;

public interface Component {
    String getName();
    void setName(String name);
    int getPrice();
    void setPrice(int price);
    int getAmount();
    void setAmount(int amount);
    String loadComponents();
    void addComponents();
    void modifyComponents();
    void deleteComponents();
    void displayComponents();
}

