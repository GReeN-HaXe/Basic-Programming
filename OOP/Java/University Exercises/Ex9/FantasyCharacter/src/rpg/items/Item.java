package rpg.items;

public abstract class Item {

    private String name;
    private int attackValue;
    private int defenseValue;


    public Item(String itemName, int attack, int defense) {
        this.name = itemName;
        this.attackValue = attack;
        this.defenseValue = defense;
    }

    public String getName() {
        return name;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public static Item[] getAllItems(){
        return new Item[] {new Sword(), new Wand(), new Armor(), null};
    }
}
